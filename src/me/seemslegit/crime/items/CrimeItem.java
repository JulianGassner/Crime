package me.seemslegit.crime.items;

import org.bukkit.inventory.ItemStack;

import me.seemslegit.crime.plugin.Main;

public class CrimeItem {

	private String name;
	private ItemStack i;
	private boolean illegal = false;
	private boolean onlycop = false;
	private boolean drop = false;
	private boolean unbreakable = true;
	private boolean weapon = false;
	
	public CrimeItem(String name) {
		this(name, null);
	}
	
	public CrimeItem(String name, ItemStack i) {
		this.name = name;
		this.i = i;
	}
	
	/**
	 * 
	 * @return {@link CrimeItem}
	 */
	public CrimeItem register() {
		if(i != null && !Main.instance.getItemManager().items.contains(this)) {
			Main.instance.getItemManager().items.add(this);
		}
		return this;
	}
	
	/**
	 * 
	 * @param b {@link Boolean}
	 * @return {@link CrimeItem}
	 */
	public CrimeItem setWeapon(boolean b) {
		this.weapon = b;
		return this;
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
	 * @param b {@link Boolean}
	 * @return {@link CrimeItem}
	 */
	public CrimeItem setDroppable(boolean b) {
		this.drop = b;
		return this;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isWeapon() {
		return weapon;
	}
	
	/**
	 * 
	 * @param b {@link Boolean}
	 * @return {@link CrimeItem}
	 */
	public CrimeItem setUnbreakable(boolean b) {
		this.unbreakable = b;
		return this;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isUnbreakable() {
		return unbreakable;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean shouldDrop() {
		return drop;
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
	 * @param b {@link Boolean}
	 * @return {@link CrimeItem}
	 */
	public CrimeItem setOnlyCop(boolean b) {
		this.onlycop = b;
		return this;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isOnlyCop() {
		return onlycop;
	}
	
	/**
	 * 
	 * @return {@link ItemStack}
	 */
	public ItemStack getItem() {
		NBTItem item = new NBTItem(i);
		item.setString("crime", getName());
		item.setBoolean("cillegal", isIllegal());
		item.setBoolean("coc", isOnlyCop());
		item.setBoolean("cdrop", shouldDrop());
		item.setBoolean("cweapon", isWeapon());
		item.setInteger("Unbreakable", (isUnbreakable() ? 1 : 0));
		return item.getItem();
	}
	
	/**
	 * 
	 * @param i {@link ItemStack}
	 * @return {@link Boolean}
	 */
	public static boolean isOnlyCop(ItemStack i) {
		NBTItem item = new NBTItem(i);
		if(item.hasKey("coc")) return item.getBoolean("coc");
		return false;
	}
	
	/**
	 * 
	 * @param i {@link ItemStack}
	 * @return {@link Boolean}
	 */
	public static boolean isWeapon(ItemStack i) {
		NBTItem item = new NBTItem(i);
		if(item.hasKey("cweapon")) return item.getBoolean("cweapon");
		return false;
	}
	
	/**
	 * 
	 * @param i {@link ItemStack}
	 * @return {@link Boolean}
	 */
	public static boolean shouldDrop(ItemStack i) {
		NBTItem item = new NBTItem(i);
		if(item.hasKey("cdrop")) return item.getBoolean("cdrop");
		return false;
	}
	
	/**
	 * 
	 * @param i {@link ItemStack}
	 * @return {@link Boolean}
	 */
	public static boolean isIllegal(ItemStack i) {
		NBTItem item = new NBTItem(i);
		if(item.hasKey("cillegal")) return item.getBoolean("cillegal");
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
