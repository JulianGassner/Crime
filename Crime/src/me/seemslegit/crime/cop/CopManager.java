package me.seemslegit.crime.cop;

import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.mojang.authlib.yggdrasil.response.User;

public class CopManager {

	public CopManager() {
		init();
	}
	
	private void init() {
		Main.registerCommandonBukkit("cop");
		Bukkit.getPluginCommand("cop").setExecutor(new CopCMD());
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
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void cuff(UserBase u){
		
	}
	
	/**
	 * 
	 * @param u {@link User}
	 */
	public void uncuff(UserBase u){
		
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
			
			if(!CrimeItem.isIllegal(item)) {
				inv.setItem(i, null);
			}
			
		}
		
		u.setInventory(inv);
		
	}
	
}
