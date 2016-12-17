package me.seemslegit.crime.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemAPI {
	
	public static ItemStack createStack(Material m, int Amount){
		ItemStack s = new ItemStack(m, Amount);
		ItemMeta meta = s.getItemMeta();
		return null;
		
	}

}
