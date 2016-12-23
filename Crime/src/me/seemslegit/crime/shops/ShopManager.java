package me.seemslegit.crime.shops;

import java.io.File;
import java.util.ArrayList;

import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;
import me.seemslegit.crime.shops.api.Shop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ShopManager {
	private File file = new File("Crime//Config", "shops.yml");
	private Config cfg = new Config(file);
	
	public ArrayList<Shop> shops = new ArrayList<Shop>();
	
	/**
	 * 
	 * @return {@link Config}
	 */
	public Config getConfig() {
		return cfg;
	}
	
	/**
	 * 
	 * @param id {@link String}
	 * @return {@link Shop}
	 */
	public Shop getShop(String id) {
		
		for(Shop s : shops) {
			if(s.getID().equalsIgnoreCase(id)) return s;
		}
		
		return null;
	}
	
	public void onInteract(Player p, Shop s) {
		
		if(!(s.getRegion().isIn(p.getLocation()))) return;
		
		p.sendMessage("test");
		
	}
	
	/**
	 * 
	 * @param r {@link Region}
	 * @return {@link Shop}
	 */
	public Shop getShop(Region r) {
		
		for(Shop s : shops) {
			if(s.getRegion().equals(r)) return s;
		}
		
		return null;
	}
	
	public ShopManager() {
		
	}
	
	public void load() {
		//for(Shop s : shops) s.despawnEntity();
		shops.clear();
		File f = new File("Crime//Shops");
		
		if(!f.exists()) return;
		if(!f.isDirectory()) return;
		
		for(File ll : f.listFiles()) {
			
			if(ll.isDirectory()) continue;
			if(!ll.getName().endsWith(".yml")) continue;
			
			String id = ll.getName().substring(0, ll.getName().length() - 4);
			
			Config c = new Config(ll);
			
			Location loc = c.getLocation("loc");
			
			String rid = c.getString("region");
			
			if(loc == null || rid == null || id == null) continue;
			
			Region r = Main.instance.getRegionManager().getRegion(rid);
			
			if(r == null) continue;
			
			Shop s = new Shop(id, loc, r);
			System.out.println(id);
			s.spawnEntity();
			shops.add(s);
		}
	}
	
	public void init() {
		Main.registerCommandonBukkit("shop");
		Bukkit.getPluginCommand("shop").setExecutor(new ShopCommand());
		
		Bukkit.getPluginManager().registerEvents(new ShopRobbing(), Main.instance);
		
		load();
	}

}
