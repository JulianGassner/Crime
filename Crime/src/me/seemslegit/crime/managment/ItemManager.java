package me.seemslegit.crime.managment;

import java.util.ArrayList;

import me.seemslegit.crime.api.ItemAPI;
import me.seemslegit.crime.items.CrimeItem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

	private ArrayList<CrimeItem> items = new ArrayList<CrimeItem>();

	private void init() {

		items.add(new CrimeItem("bread", new ItemAPI().material(Material.BREAD)
				.displayName("§3Bread")
				.lore("§7Lore")
				.build()));

	}

	public ItemManager() {
		init();
	}

	public ItemStack getItem(String name) {
		int index = items.indexOf(new CrimeItem(name));

		if (index == -1)
			return null;

		return items.get(index).getItem();
	}

}
