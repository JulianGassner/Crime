package me.seemslegit.crime.managment;

import me.seemslegit.crime.listener.P_Inv_Listener;
import me.seemslegit.crime.listener.P_Join_Listener;
import me.seemslegit.crime.listener.P_Quit_Listener;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;

public class PlayerManager {

	private void init_Listener() {
		Bukkit.getPluginManager().registerEvents(new P_Join_Listener(), Main.instance);
		Bukkit.getPluginManager().registerEvents(new P_Quit_Listener(), Main.instance);
		Bukkit.getPluginManager().registerEvents(new P_Inv_Listener(), Main.instance);
	}
	
	private void init() {
		init_Listener();
	}
	
	public PlayerManager() {
		init();
	}
	
}
