package me.seemslegit.crime.playerapi;

import java.util.UUID;

import me.seemslegit.crime.api.CachedInventory;
import me.seemslegit.crime.managment.MoneyManager;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
	 * @param loc {@link Location}
	 */
	public void teleport(Location loc) {
		if(loc == null) return;
		Player p = getPlayer();
		if(p == null) {
			
		}else{
			p.teleport(loc);
		}
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isCop() {
		return Main.instance.getCopManager().isCop(this);
	}
	
	public void resetJailTime() {
		setJailTime(-1);
	}
	
	/**
	 * 
	 * @param jt {@link Long}
	 */
	public void setJailTime(long jt) {
		if(jt > 60*30) jt = 60*30;
		getStats().set("jailtime", jt);
	}
	
	/**
	 * 
	 * @return {@link Long}
	 */
	public long getJailTime() {
		long jt = getStats().getLong("jailtime", -1);
		if(jt > 60*30) jt = 60*30;
		return jt;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isInJail() {
		return Main.instance.getJailManager().isInJail(this);
	}
	
	/**
	 * 
	 * @return {@link Player}
	 */
	public Player getPlayer() {
		return Bukkit.getPlayer(getUUID());
	}
	
	/**
	 * 
	 * @return {@link Inventory}
	 */
	public Inventory loadCachedInventory() {
		return new CachedInventory(getStats()).loadInventory("inv");
	}
	
	/**
	 * 
	 * @return {@link Inventory}
	 */
	public Inventory getInventory() {
		Player p = getPlayer();
		if(p == null) {
			return loadCachedInventory();
		}else{
			/**Inventory inv_p = p.getInventory();
			
			Inventory inv = Bukkit.createInventory(null, inv_p.getSize());
			
			inv.setContents(inv_p.getContents());
			
			return inv;**/
			return p.getInventory();
		}
	}
	
	/**
	 * 
	 * @param inv {@link Inventory}
	 */
	public void cacheInventory(Inventory inv) {
		new CachedInventory(getStats()).saveInventory(inv, "inv");
	}
	
	/**
	 * 
	 * @param inv {@link Inventory}
	 */
	public void setInventory(Inventory inv) {
		Player p = getPlayer();
		if(p == null) {
			cacheInventory(inv);
		}else{
			p.getInventory().setContents(inv.getContents());
			p.updateInventory();
		}
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isOnline() {
		return getPlayer() != null;
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
