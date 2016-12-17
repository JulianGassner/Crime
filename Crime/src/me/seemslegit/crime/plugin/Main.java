package me.seemslegit.crime.plugin;

import me.seemslegit.crime.managment.ErrorManager;
import me.seemslegit.crime.managment.MoneyManager;
import me.seemslegit.crime.managment.PlayerManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Main instance;
	
	private ErrorManager mng_error;
	private MoneyManager mng_money;
	private PlayerManager mng_player;
	
	private void init() {
		mng_error = new ErrorManager();
		mng_money = new MoneyManager();
		mng_player = new PlayerManager();
	}
	
	public void onEnable() {
		instance = this;
		
		init();
	}
	
	/**
	 * 
	 * @return {@link ErrorManager}
	 */
	public ErrorManager getErrorManager() {
		return mng_error;
	}
	
	/**
	 * 
	 * @return {@link MoneyManager}
	 */
	public MoneyManager getMoneyManager() {
		return mng_money;
	}
	
	/**
	 * 
	 * @return {@link PlayerManager}
	 */
	public PlayerManager getPlayerManager() {
		return mng_player;
	}
	
}
