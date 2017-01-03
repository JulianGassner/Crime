package me.seemslegit.crime.playerapi;

import java.util.UUID;

import me.seemslegit.crime.api.CachedInventory;
import me.seemslegit.crime.decodings.Base64;
import me.seemslegit.crime.managment.MoneyManager;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
	public void setCachedLocation(Location loc) {
		getStats().setLocation("loc", loc);
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getCachedLocation() {
		Location loc = getStats().getLocation("loc");
		if(loc == null) loc = Main.instance.getSpawn();
		return loc;
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLocation() {
		Player p = getPlayer();
		
		if(p == null) return getCachedLocation();
		return p.getLocation();
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 */
	public void teleport(Location loc) {
		if(loc == null) return;
		Player p = getPlayer();
		if(p == null) {
			setCachedLocation(loc);
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
		//Bukkit.broadcastMessage(getName() + " > JT: " + jt);
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
	 * @return {@link ItemStack}
	 */
	public ItemStack[] loadCachedArmor() {
		
		try{
			
			String str = getStats().getString("armor");
			
			if(str == null) return null;
			
			Object[] obj = Base64.ArrayFromBase64(str);
			
			if(obj == null) return null;
			
			ItemStack[] armor = new ItemStack[obj.length];
			
			int b = 0;
			for(Object o : obj) {
				armor[b] = (ItemStack) o;
				b++;
			}
			
			return armor;
		}catch(Exception e) {
			Main.instance.getErrorManager().registerError(e);
		}
		
		return null;
	}
	
	public ItemStack[] getArmor() {
		Player p = getPlayer();
		if(p == null) {
			return loadCachedArmor();
		}else{
			return p.getInventory().getArmorContents();
		}
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
	 * @param armor {@link ItemStack}
	 */
	public void cacheInventory(Inventory inv, ItemStack[] armor) {
		if(armor != null) {
			getStats().set("armor", Base64.ArraytoBase64(armor));
		}
		new CachedInventory(getStats()).saveInventory(inv, "inv");
	}
	
	/**
	 * 
	 * @param inv {@link Inventory}
	 */
	public void setInventory(Inventory inv, ItemStack[] armor) {
		Player p = getPlayer();
		if(p == null) {
			cacheInventory(inv, armor);
		}else{
			p.getInventory().setContents(inv.getContents());
			p.getInventory().setArmorContents(armor);
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
