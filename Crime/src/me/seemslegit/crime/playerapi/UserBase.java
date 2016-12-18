package me.seemslegit.crime.playerapi;

import java.util.UUID;

import me.seemslegit.crime.managment.MoneyManager;
import me.seemslegit.crime.plugin.Main;

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
	
	/**
	 * 
	 * @return {@link Float}
	 */
	public float getCoins() {
		return Main.instance.getMoneyManager().getCoins(this);
	}
	
	/**
	 * 
	 * @param c {@link Float}
	 */
	public void setCoins(float c) {
		Main.instance.getMoneyManager().setCoins(this, c);
	}
	
	/**
	 * 
	 * @param c {@link Float}
	 */
	public void addCoins(float c) {
		setCoins(getCoins() + c);
	}
	
	/**
	 * 
	 * @param c {@link Float}
	 */
	public void removeCoins(float c) {
		addCoins(-c);
	}
	
	public void resetCoins() {
		setCoins(MoneyManager.START_COINS);
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean hasCrime() {
		return Main.instance.getCrimeManager().hasCrime(this);
	}
	
	/**
	 * 
	 * @return {@link Long}
	 */
	public long getCrime() {
		return Main.instance.getCrimeManager().getCrime(this);
	}
	
	/**
	 * 
	 * @param c {@link Long}
	 */
	public void setCrime(long c) {
		Main.instance.getCrimeManager().setCrime(this, c);
	}
	
	/**
	 * 
	 * @param c {@link Long}
	 */
	public void addCrime(long c) {
		setCrime(getCrime() + c);
	}
	
	/**
	 * 
	 * @param c {@link Long}
	 */
	public void removeCrime(long c) {
		addCrime(-c);
	}
	
	public void resetCrime() {
		Main.instance.getCrimeManager().clearCrime(this);
	}
	
}
