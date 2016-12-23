package me.seemslegit.crime.shops;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;
import me.seemslegit.crime.regions.RegionType;
import me.seemslegit.crime.shops.api.Shop;

public class ShopRobbing implements Listener{
	
	private HashMap<Shop, Long> nextrob = new HashMap<Shop, Long>();
	private HashMap<Shop, Integer> rob = new HashMap<Shop, Integer>();
	private HashMap<Shop, Long> lastrob = new HashMap<Shop, Long>();
	
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
		Player p = e.getPlayer();
		
		if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) return;
		
		if(!CrimeItem.isWeapon(p.getItemInHand())) return;
		
		Region[] regs = Main.instance.getRegionManager().getRegions(p.getLocation());
		
		for(Region r : regs) {
			
			if(r.getType() != RegionType.SHOP) continue;
			
			Shop s = Main.instance.getShopManager().getShop(r);
			if(s == null) continue;
			
			//IST NE WAFFE 
			
			if(!canBeRobbed(s)) continue;
			
			final Player t = p;
			final Shop ss = s;
			final Region rr = r;
			
			Thread tr = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						Bukkit.broadcastMessage("§cShop is being robbed ("+ss.getName()+")!");
						while(t.isSneaking() && rr.isIn(t.getLocation())) {
							
							Thread.sleep(1000*10);
							
							addRob(t, ss);
							
						}
						if(canBeRobbed(ss)) {
							Bukkit.broadcastMessage("§c" + t.getName() + " run away!");
						}
					}catch(Exception e) {
						Main.instance.getErrorManager().registerError(e);
					}
				}
			});
			tr.setPriority(Thread.MIN_PRIORITY);
			tr.start();
			break;
			
		}
		
		
	}

}
