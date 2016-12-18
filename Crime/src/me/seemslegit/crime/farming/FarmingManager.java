package me.seemslegit.crime.farming;

import org.bukkit.Bukkit;

import me.seemslegit.crime.plugin.Main;

public class FarmingManager {

	
	public FarmingManager() {
		init();
	}
	
	private void init() {
		Bukkit.getPluginManager().registerEvents(new Drugs(), Main.instance);
	}
	
	
}
