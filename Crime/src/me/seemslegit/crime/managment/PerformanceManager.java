package me.seemslegit.crime.managment;

import me.seemslegit.crime.listener.W_Performance_Listener;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;

public class PerformanceManager {

	public void init() {
		Bukkit.getPluginManager().registerEvents(new W_Performance_Listener(), Main.instance);
	}
	
	public PerformanceManager() {
		init();
	}
	
}
