package me.seemslegit.crime.cop;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.listener.P_Cop_Listener;
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
			if(u.hasCrime() || !u.isOnline()) {
				return false;
			}else{
				u.cacheInventory(u.getInventory());
				CopItems.giveCopItems(u);
				b = true;
			}
		}else{
			u.setInventory(u.loadCachedInventory());
			b = false;
		}
		
		u.getStats().set("cop", b);
		return true;
	}
	public boolean isCuffed(UserBase u){
		return u.getStats().getBoolean("cuffed", false);
	}
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void cuff(UserBase u){
		if(u.isOnline() == true){
			u.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*100000, 2));
			if(!(isCuffed(u))){
				u.getStats().set("cuffed", true);
				//u.getStats().set("jail", true);
			}
		}
	}
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void uncuff(UserBase u){
		if(!isCuffed(u) == false){
			u.getStats().set("cuffed", false);
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
				u.addCrime(5 * item.getAmount());
			}
			
		}
		
		u.setInventory(inv);
		
	}
	
}
