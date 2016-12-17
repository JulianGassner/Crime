package me.seemslegit.crime.plugin;

import me.seemslegit.crime.managment.ErrorManager;
import me.seemslegit.crime.managment.MoneyManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Main instance;
	
	private ErrorManager mng_error;
	private MoneyManager mng_money;
	
	private void init() {
		mng_error = new ErrorManager();
		mng_money = new MoneyManager();
		
	}
	
	public void onEnable() {
		instance = this;
		
		init();
	}
	
	public ErrorManager getErrorManager() {
		return mng_error;
	}
	
	public MoneyManager getMoneyManager() {
		return mng_money;
	}
	
}
