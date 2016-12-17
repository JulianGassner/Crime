package me.seemslegit.crime.plugin;

import me.seemslegit.crime.managment.ErrorManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Main instance;
	
	private ErrorManager mng_error;
	
	private void init() {
		mng_error = new ErrorManager();
		
	}
	
	public void onEnable() {
		instance = this;
		
		init();
	}
	
	public ErrorManager getErrorManager() {
		return mng_error;
	}
	
}
