package me.seemslegit.crime.items;

import org.bukkit.inventory.ItemStack;

public class CrimeItem {

	private String name;
	private ItemStack i;
	
	public CrimeItem(String name) {
		this(name, null);
	}
	
	public CrimeItem(String name, ItemStack i) {
		this.name = name;
		this.i = i;
	}
	
	public ItemStack getItem() {
		NBTItem item = new NBTItem(i);
		item.setString("crime", getName());
		return item.getItem();
	}
	
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
