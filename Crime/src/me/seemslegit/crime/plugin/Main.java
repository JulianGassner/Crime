package me.seemslegit.crime.plugin;

import java.io.File;
import java.lang.reflect.Constructor;

import me.seemslegit.crime.CrimeThread;
import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.commands.CMD_config;
import me.seemslegit.crime.cop.CopManager;
import me.seemslegit.crime.managment.CrimeManager;
import me.seemslegit.crime.managment.ErrorManager;
import me.seemslegit.crime.managment.ItemManager;
import me.seemslegit.crime.managment.JailManager;
import me.seemslegit.crime.managment.MoneyManager;
import me.seemslegit.crime.managment.PerformanceManager;
import me.seemslegit.crime.managment.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static final String admin_permission = "crime.admin";
	
	public static Main instance;
	
	public Config sys_cfg = new Config(new File("Crime//DB", "system.yml"));
	private ErrorManager mng_error;
	private MoneyManager mng_money;
	private PlayerManager mng_player;
	private PerformanceManager mng_performance;
	private CrimeManager mng_crime;
	private JailManager mng_jail;
	private ItemManager mng_item;
	private CopManager mng_cop;
	private Thread crimethread;
	private boolean started = false;
	
	private void initCommands() {
		
		registerCommandonBukkit("config");
		Bukkit.getPluginCommand("config").setExecutor(new CMD_config());
		
	}
	
	public void startCrimeThread() {
		crimethread = new Thread(new CrimeThread());
		crimethread.setPriority(Thread.MIN_PRIORITY);
		crimethread.start();
	}
	
	private void init() {
		mng_error = new ErrorManager();
		mng_money = new MoneyManager();
		mng_player = new PlayerManager();
		mng_performance = new PerformanceManager();
		mng_crime = new CrimeManager();
		mng_jail = new JailManager();
		mng_item = new ItemManager();
		mng_cop = new CopManager();
		
		mng_item.init();
		
		initCommands();
		startCrimeThread();
	}
	
	public void onEnable() {
		for(Player p : Bukkit.getOnlinePlayers()) p.kickPlayer("§8> §cServer will be back soon...");
		instance = this;
		
		init();
		started = true;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isStarted() {
		return started;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		for(Player p : Bukkit.getOnlinePlayers()) p.kickPlayer("§8> §cServer will be back soon...");
		Bukkit.getScheduler().cancelAllTasks();
		if(crimethread != null) crimethread.stop();
		started = false;
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
	
	/**
	 * 
	 * @return {@link JailManager}
	 */
	public JailManager getJailManager() {
		return mng_jail;
	}
	
	/**
	 * 
	 * @return {@link ItemManager}
	 */
	public ItemManager getItemManager() {
		return mng_item;
	}
	
	/**
	 * 
	 * @return {@link CopManager}
	 */
	public CopManager getCopManager() {
		return mng_cop;
	}
	
	/**
	 * 
	 * @param s {@link String}
	 */
	public static void registerCommandonBukkit(String s) {
		if(Bukkit.getPluginCommand(s) != null) return;
		try{
			Class<?> c = PluginCommand.class;
			Constructor<?> constructor = c.getDeclaredConstructor(String.class,org.bukkit.plugin.Plugin.class);
			constructor.setAccessible(true);
			PluginCommand pl = (PluginCommand)constructor.newInstance(s,Main.instance);
			((CraftServer) Bukkit.getServer()).getCommandMap().register("Crime", pl);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
