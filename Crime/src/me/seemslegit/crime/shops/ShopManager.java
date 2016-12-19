package me.seemslegit.crime.shops;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.seemslegit.crime.plugin.Main;

public class ShopManager {
	File file = new File("plugins/Crime", "crime.yml");
	@SuppressWarnings("static-access")
	FileConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
	public ArrayList<String> Shopnames = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	public ArrayList<String> tobeloaded = (ArrayList<String>) cfg.get("names");
	
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
	
	public void createShop(Location p1, Location p2, String name){
		
		
		cfg.set(name+".pos1", p1);
		cfg.set(name+".pos2", p2);
		
		Shopnames.add("names."+name);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
		
	}
	
	public void saveShopstoCfg(){
		cfg.set("names", Shopnames);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			System.out.println("Error while saving shops");
			e.printStackTrace();
		}
	}

}
