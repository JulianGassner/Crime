package me.seemslegit.crime.items;

import java.util.Set;

import org.bukkit.inventory.ItemStack;

public class NBTItem {
	private ItemStack bukkitItem;

	/**
	 * 
	 * @param item {@link ItemStack}
	 */
	public NBTItem(ItemStack item) {
		this.bukkitItem = item.clone();
	}

	/**
	 * 
	 * @return {@link ItemStack}
	 */
	public ItemStack getItem() {
		return this.bukkitItem;
	}

	/**
	 * 
	 * @param key {@link String}
	 * @param value {@link String}
	 */
	public void setString(String key, String value) {
		this.bukkitItem = NBTReflectionUtil.setString(this.bukkitItem, key,
				value);
	}

	/**
	 * 
	 * @param key {@link String}
	 * @return {@link String}
	 */
	public String getString(String key) {
		return NBTReflectionUtil.getString(this.bukkitItem, key);
	}

	/**
	 * 
	 * @param key {@link String}
	 * @param value {@link Integer}
	 */
	public void setInteger(String key, int value) {
		this.bukkitItem = NBTReflectionUtil.setInt(this.bukkitItem, key,
				Integer.valueOf(value));
	}

	/**
	 * 
	 * @param key {@link String}
	 * @return {@link Integer}
	 */
	public Integer getInteger(String key) {
		return NBTReflectionUtil.getInt(this.bukkitItem, key);
	}

	/**
	 * 
	 * @param key {@link String}
	 * @param value {@link Double}
	 */
	public void setDouble(String key, double value) {
		this.bukkitItem = NBTReflectionUtil.setDouble(this.bukkitItem, key,
				Double.valueOf(value));
	}

	/**
	 * 
	 * @param key {@link String}
	 * @return {@link Double}
	 */
	public double getDouble(String key) {
		return NBTReflectionUtil.getDouble(this.bukkitItem, key).doubleValue();
	}

	/**
	 * 
	 * @param key {@link String}
	 * @param value {@link Boolean}
	 */
	public void setBoolean(String key, boolean value) {
		this.bukkitItem = NBTReflectionUtil.setBoolean(this.bukkitItem, key,
				Boolean.valueOf(value));
	}

	/**
	 * 
	 * @param key {@link String}
	 * @return {@link Boolean}
	 */
	public boolean getBoolean(String key) {
		return NBTReflectionUtil.getBoolean(this.bukkitItem, key)
				.booleanValue();
	}

	/**
	 * 
	 * @param key {@link String}
	 * @return {@link Boolean}
	 */
	public boolean hasKey(String key) {
		return NBTReflectionUtil.hasKey(this.bukkitItem, key).booleanValue();
	}

	/**
	 * 
	 * @param key {@link String}
	 */
	public void removeKey(String key) {
		this.bukkitItem = NBTReflectionUtil.remove(this.bukkitItem, key);
	}

	/**
	 * 
	 * @return {@link Set}
	 */
	public Set<String> getKeys() {
		return NBTReflectionUtil.getKeys(this.bukkitItem);
	}
}
