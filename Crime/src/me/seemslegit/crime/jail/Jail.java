package me.seemslegit.crime.jail;

import me.seemslegit.crime.managment.JailManager;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Location;

public final class Jail {

	
	
	/**
	 * 
	 * @param u {@link UserBase}
	 */
	public void initPlayer(UserBase u) {
		
		Main.instance.getCopManager().removeIllegalItems(u);
		
	}
 	
	
	
	
	
	
	
	
	
	
	
	
	private JailManager jm;
	
	/**
	 * 
	 * @param jm {@link JailManager}
	 */
	public Jail(JailManager jm) {
		this.jm = jm;
	}
	
	/**
	 * 
	 * @return {@link JailManager}
	 */
	public JailManager getJailManager() {
		return jm;
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLocation() {
		return jm.getLocation();
	}
}
