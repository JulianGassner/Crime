package me.seemslegit.crime.cop;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.listener.P_Cop_Listener;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;



public class CopManager {
	
	//public ArrayList<String> iscuffedlist = new ArrayList<String>();

	public CopManager() {
		init();
	}
	
	private void init() {
		Main.registerCommandonBukkit("cop");
		Bukkit.getPluginCommand("cop").setExecutor(new CopCMD());
		
		Bukkit.getPluginManager().registerEvents(new P_Cop_Listener(), Main.instance);
		Bukkit.getPluginManager().registerEvents(new CopItems(), Main.instance);
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 * @param b {@link User}
	 */
	public void printInfos(Player p, User b) {
		User a = new User(p);
		p.sendMessage("§e-------------------------------");
		p.sendMessage("§eStats of Player "+b.getName());
		if(a.isCop()){
			p.sendMessage("§eMoney: "+b.getCoins());
			p.sendMessage("§eCrime: §c"+b.getCrime());
		}
		if(b.hasCrime()){
			p.sendMessage("§eHe is being §csearched§e !");
		}else{
			if(b.isInJail()){
				p.sendMessage("§eJail-Time: "+b.getJailTime());
				p.sendMessage("§3He is a §1Prisoner");
			}else p.sendMessage("§eHe is a §cinnocent.");
		}
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Boolean}
	 */
	public boolean isCop(UserBase u) {
		return u.getStats().getBoolean("cop", false);
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Boolean}
	 */
	public boolean switchCop(UserBase u){
		boolean b = isCop(u);
		if(!b) {
			if(u.hasCrime() || !u.isOnline() || u.getJailTime() != -1) {
				return false;
			}else{
				u.cacheInventory(u.getInventory(), u.getArmor());
				CopItems.giveCopItems(u);
				b = true;
			}
		}else{
			u.setInventory(u.loadCachedInventory(), u.loadCachedArmor());
			b = false;
		}
		
		u.getStats().set("cop", b);
		return true;
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Boolean}
	 */
	public boolean isCuffed(UserBase u){
		return u.getStats().getBoolean("cuffed", false);
	}
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void rawCuff(UserBase u, boolean b) {
		u.getStats().set("cuffed", b);
	}
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void cuff(UserBase u){
		if(u.isOnline()){
			u.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*100000, 2));
			u.getPlayer().sendMessage("§8> §cYou've been cuffed!");
		}
		if(!isCuffed(u)){
			rawCuff(u, true);
			u.getStats().set("jail", true);
			/**if(u.getJailTime() != -1) return;
			final UserBase uu = u;
			new Thread(new Runnable() {
				
				public void run() {
					try{
						Thread.sleep(1000*60*5);
					}catch(Exception e) {
						Main.instance.getErrorManager().registerError(e);
					}
					if(uu.getJailTime() == -1 && uu.isInJail()) {
						Main.instance.getJailManager().sendToJail(uu);
					}
				}
			}).start();**/
		}
	}
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void uncuff(UserBase u){
		if(isCuffed(u)){
			if(u.isOnline()) {
				if(u.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
					u.getPlayer().removePotionEffect(PotionEffectType.SLOW);
				}
				u.getPlayer().sendMessage("§8> §aYou've been uncuffed!");
			}
			rawCuff(u, false);
			u.resetCrime();
			u.getStats().set("jail", false);
			u.resetJailTime();
		}
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 */
	public void removeIllegalItems(UserBase u) {
		
		Inventory inv = u.getInventory();
		
		for(int i = 0;i<inv.getSize();i++) {
			
			ItemStack item = inv.getItem(i);
			
			if(item == null || item.getType() == Material.AIR) continue;
			
			if(CrimeItem.isIllegal(item)) {
				inv.setItem(i, null);
				if(!u.isCop()) u.addCrime(5 * item.getAmount());
			}
			
		}
		
		u.setInventory(inv, u.getArmor());
		
	}
	
}
