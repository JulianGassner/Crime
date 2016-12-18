package me.seemslegit.crime.api;

import me.seemslegit.crime.decodings.Base64;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.inventory.Inventory;

public class CachedInventory {

	private Config c;
	
	/**
	 * 
	 * @param c {@link Config}
	 */
	public CachedInventory(Config c) {
		this.c = c;
	}
	
	/**
	 * 
	 * @param path {@link String}
	 * @return {@link Inventory}
	 */
	public Inventory loadInventory(String path) {
		String cfg_str = getConfig().getString(path, null);
		if(cfg_str == null) return null;
		
		try{
			
			Inventory inv = Base64.fromBase64(cfg_str);
			
			return inv;
			
		}catch(Exception e) {
			Main.instance.getErrorManager().registerError(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return {@link Config}
	 */
	public Config getConfig() {
		return c;
	}
	
	/**
	 * 
	 * @param inv {@link Inventory}
	 * @param path {@link String}
	 */
	public void saveInventory(Inventory inv, String path) {
		getConfig().set(path, Base64.toBase64(inv));
	}
	
}
