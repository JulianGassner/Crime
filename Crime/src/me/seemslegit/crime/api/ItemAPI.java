package me.seemslegit.crime.api;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemAPI {
	
	public static ItemStack createStack(Material m, int Amount, String displayname, String itemlorelore){
		ItemStack s = new ItemStack(m, Amount);
		ItemMeta meta = s.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(itemlorelore);
		meta.setLore(lore);
		s.setItemMeta(meta);
		return s;
		
	}

}
