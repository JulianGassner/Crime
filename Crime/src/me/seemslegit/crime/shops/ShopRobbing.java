package me.seemslegit.crime.shops;

import java.util.ArrayList;
import java.util.HashMap;

import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;
import me.seemslegit.crime.regions.RegionType;
import me.seemslegit.crime.shops.api.Shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ShopRobbing implements Listener{
	
	private HashMap<Shop, Long> nextrob = new HashMap<Shop, Long>();
	private HashMap<Shop, Integer> rob = new HashMap<Shop, Integer>();
	private HashMap<Shop, Long> lastrob = new HashMap<Shop, Long>();
	private ArrayList<Player> lel = new ArrayList<Player>();
	
	private void addRob(Player p, Shop s) {
		
		if(!canBeRobbed(s)) return;
		
		
		int howoften = 0;
		if(rob.containsKey(s)) {
			howoften = rob.get(s);
			rob.remove(s);
		}
		
		howoften++;
		
		p.getInventory().addItem(Main.instance.getItemManager().getItem("coin"));
		User u = new User(p);
		u.addCrime(41);
		p.sendMessage("§eYou have "+u.getCrime()+" Crime left...");
		
		int max = 8;
		
		if(lastrob.containsKey(s)) {
			
			long last = lastrob.get(s);
			
			last += 1000 * 60 * 8;
			
			last -= System.currentTimeMillis();
			
			last = Long.parseLong((last + "").replace("-", ""));
			
			last /= 1000*60;
			
			max += last;
			
		}
		
		if(max > 16) max = 16;
		
		if(howoften >= max) {
			nextrob.put(s, System.currentTimeMillis() + 1000 * 60 * 8);
			if(lastrob.containsKey(s)) lastrob.remove(s);
			lastrob.put(s, System.currentTimeMillis());
			Bukkit.broadcastMessage("§c"+u.getName()+" successfully robbed "+s.getName()+".");
		}else{
			rob.put(s, howoften);
		}
		
	}
	
	/**
	 * 
	 * @param s {@link Shop}
	 * @return {@link Boolean}
	 */
	private boolean canBeRobbed(Shop s) {
		
		if(nextrob.containsKey(s)) {
			
			long stamp = nextrob.get(s);
			
			if(System.currentTimeMillis() < stamp) return false; 
			nextrob.remove(s);
			
		}
		
		return true;
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e){
		if(!e.isSneaking()) return;
		Player p = e.getPlayer();
		
		User u = new User(p);
		
		if(u.isCop() || u.getJailTime() != -1) return;
		
		if(lel.contains(p)) return;
		lel.add(p);
	
		if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
			lel.remove(p);
			return;
		}
		
		//if(!CrimeItem.isWeapon(p.getItemInHand())) return;
		
		Region[] regs = Main.instance.getRegionManager().getRegions(p.getLocation());
		
		boolean b = false;
		
		for(Region r : regs) {
			
			if(r.getType() != RegionType.SHOP) continue;
			
			Shop s = Main.instance.getShopManager().getShop(r);
			if(s == null) continue;
			
			//IST NE WAFFE 
			if(!canBeRobbed(s)) {
				long l = nextrob.get(s);
				l -= System.currentTimeMillis();
				l /= 1000;
				p.sendMessage("§cShop allready robbed (" + l + "sec to wait)!");
				continue;
			}
			
			final Player t = p;
			final Shop ss = s;
			final Region rr = r;
			Thread tr = new Thread(new Runnable() {
				
				public void run() {
					try{
						Bukkit.broadcastMessage("§eA Shop is being robbed by '"+t.getName()+"'!");
						while(rr.isIn(t.getLocation())) {
							
							addRob(t, ss);
							
							Thread.sleep(1000*10);
							
						}
						if(canBeRobbed(ss)) {
							Bukkit.broadcastMessage("§c" + t.getName() + " run away!");
							int ll = rob.get(ss);
							if(ll > 8) ll = 8;
							nextrob.put(ss, System.currentTimeMillis() + 1000 * 60 * ll);
							if(lastrob.containsKey(ss)) lastrob.remove(ss);
							lastrob.put(ss, System.currentTimeMillis());
							rob.remove(ss);
						}
					}catch(Exception e) {
						Main.instance.getErrorManager().registerError(e);
					}
					if(lel.contains(t)) lel.remove(t);
				}
			});
			tr.setPriority(Thread.MIN_PRIORITY);
			tr.start();
			b = true;
			break;
			
		}
		if(b) return;
		lel.remove(p);
		
	}

}
