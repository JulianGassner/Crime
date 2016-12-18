package me.seemslegit.crime.items;

import org.bukkit.inventory.ItemStack;

public class CrimeItem {

	private String name;
	private ItemStack i;
	private boolean illegal = false;
	
	public CrimeItem(String name) {
		this(name, null);
	}
	
	public CrimeItem(String name, ItemStack i) {
		this.name = name;
		this.i = i;
	}
	
	/**
	 * 
	 * @param b {@link Boolean}
	 * @return {@link CrimeItem}
	 */
	public CrimeItem setIllegal(boolean b) {
		this.illegal = b;
		return this;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isIllegal() {
		return illegal;
	}
	
	/**
	 * 
	 * @return {@link ItemStack}
	 */
	public ItemStack getItem() {
		NBTItem item = new NBTItem(i);
		item.setString("crime", getName());
		item.setBoolean("cillegal", illegal);
		return item.getItem();
	}
	
	/**
	 * 
	 * @param i {@link ItemStack}
	 * @return {@link Boolean}
	 */
	public static boolean isIllegal(ItemStack i) {
		NBTItem item = new NBTItem(i);
		if(item.hasKey("crime")) return item.getBoolean("cillegal");
		return false;
	}
	
	/**
	 * 
	 * @param i {@link ItemStack}
	 * @return {@link String}
	 */
	public static String getCrimeName(ItemStack i) {
		NBTItem item = new NBTItem(i);
		if(item.hasKey("crime")) return item.getString("crime");
		return null;
	}
	
	/**
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object arg0) {
		return getName().equalsIgnoreCase(arg0.toString());
	}
	
}
