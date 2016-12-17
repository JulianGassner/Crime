package me.seemslegit.crime.plugin;

import me.seemslegit.crime.managment.CrimeManager;
import me.seemslegit.crime.managment.ErrorManager;
import me.seemslegit.crime.managment.MoneyManager;
import me.seemslegit.crime.managment.PerformanceManager;
import me.seemslegit.crime.managment.PlayerManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	
	public static Main instance;
	
	private ErrorManager mng_error;
	private MoneyManager mng_money;
	private PlayerManager mng_player;
	private PerformanceManager mng_performance;
	private CrimeManager mng_crime;
	
	private void init() {
		mng_error = new ErrorManager();
		mng_money = new MoneyManager();
		mng_player = new PlayerManager();
		mng_performance = new PerformanceManager();
		mng_crime = new CrimeManager();
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
	
	/**
	 * 
	 * @return {@link PerformanceManager}
	 */
	public PerformanceManager getPerformanceManager() {
		return mng_performance;
	}
	
	/**
	 * 
	 * @return {@link CrimeManager}
	 */
	public CrimeManager getCrimeManager() {
		return mng_crime;
	}
	
}
