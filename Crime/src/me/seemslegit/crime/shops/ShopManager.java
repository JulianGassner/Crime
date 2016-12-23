package me.seemslegit.crime.shops;

import java.io.File;

import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ShopManager {
	private File file = new File("plugins//Crime", "shops.yml");
	private Config cfg = new Config(file);
	
	
	public ShopManager() {
		init();
	}
	
	private void init() {
		//for(String s : tobeloaded){
			//Shopnames.add(s);
		//}
		Main.registerCommandonBukkit("shop");
		Bukkit.getPluginCommand("shop").setExecutor(new ShopCommands());
	}
	
	/**
	 * 
	 * @param p1 {@link Location}
	 * @param p2 {@link Location}
	 * @param name {@link String}
	 */
	public void createShop(Location p1, Location p2, String name){
		name = "shop-" + name;
		
		Region r = new Region(name, p1.getWorld().getUID());
		r.setLoc1(p1);
		r.setLoc2(p2);
		
		r.setGreeting("§eEntering shop");
		r.setFarewell("§eLeaving shop");
		
		cfg.set("Regions." + name,  p1.getWorld().getUID().toString());
	}
}
