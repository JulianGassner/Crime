package me.seemslegit.crime.playerapi;

import java.util.UUID;

import org.bukkit.Bukkit;

public abstract class UserBase {

	private UUID u;
	
	/**
	 * 
	 * @param u {@link UUID}
	 */
	UserBase(UUID u) {
		this.u = u;
	}
	
	/**
	 * 
	 * @return {@link UUID}
	 */
	public UUID getUUID() {
		return u;
	}
	
	/**
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		return Bukkit.getOfflinePlayer(getUUID()).getName();
	}
	
	/**
	 * 
	 * @return {@link UserConfig}
	 */
	public UserConfig getStats() {
		return getConfig("playerStats");
	}
	
	/**
	 * 
	 * @param name {@link String}
	 * @return {@link UserConfig}
	 */
	public UserConfig getConfig(String name) {
		return new UserConfig(this, name);
	}
	
}
