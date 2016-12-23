package me.seemslegit.crime.managment;

import java.io.File;

import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.jail.Jail;
import me.seemslegit.crime.jail.JailListener;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class JailManager {
	
	private Config cfg_jail;
	private Jail jail;
	
	private void init() {
		cfg_jail = new Config(new File("Crime//Config", "jail.yml"));
		jail = new Jail(this);
		
		Bukkit.getPluginManager().registerEvents(new JailListener(), Main.instance);
	}
	
	public JailManager() {
		init();
	}
	
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLocation() {
		return cfg_jail.getLocation("loc");
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Jail getJail() {
		return jail;
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 */
	public void setLocation(Location loc) {
		cfg_jail.setLocation("loc", loc);
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Boolean}
	 */
	public boolean isInJail(UserBase u) {
		return u.getStats().getBoolean("jail", false);
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 */
	public void sendToJail(UserBase u) {
		if(u.getJailTime() == -1 && !u.hasCrime()) {
			Main.instance.getCopManager().uncuff(u);
			return;
		}
		u.getStats().set("jail", true);
		getJail().initPlayer(u);
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 */
	public void removeFromJail(UserBase u) {
		Main.instance.getCopManager().uncuff(u);
		u.teleport(Main.instance.getSpawn());
	}
	
	
}
