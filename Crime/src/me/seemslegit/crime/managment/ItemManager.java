package me.seemslegit.crime.managment;

import java.util.ArrayList;

import me.seemslegit.crime.api.ItemAPI;
import me.seemslegit.crime.items.CrimeItem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

	private ArrayList<CrimeItem> items = new ArrayList<CrimeItem>();

	private void init() {

		CrimeItem bread = new CrimeItem("bread", new ItemAPI()
				.material(Material.BREAD).displayName("§eBread").lore("§7Lore")
				.amout(2).build());

		CrimeItem copsword = new CrimeItem("copsword", new ItemAPI()
				.material(Material.IRON_SWORD).displayName("§cSword of Law")
				.build());
		copsword.setIllegal(true);

		CrimeItem handcuffs = new CrimeItem("handcuffs", new ItemAPI()
				.material(Material.LEASH).displayName("§cHandcuffs").build());
		handcuffs.setIllegal(true);

		CrimeItem uncuff = new CrimeItem("uncuff", new ItemAPI()
				.material(Material.SHEARS).displayName("§aUncuff").build());
		uncuff.setIllegal(true);

		CrimeItem c4 = new CrimeItem("c4", new ItemAPI().material(Material.TNT)
				.displayName("§cC4").build());
		c4.setIllegal(true);
		
		CrimeItem cit = new CrimeItem("cit", new ItemAPI().material(Material.REDSTONE_TORCH_ON)
			.displayName("§cClear illlegal items").build());
		cit.setIllegal(true);
		
		items.add(cit);
		items.add(c4);
		items.add(uncuff);
		items.add(bread);
		items.add(copsword);
		items.add(handcuffs);
	}

	public ItemManager() {
		init();
	}

	/**
	 * 
	 * @param name {@link String}
	 * @return {@link ItemStack}
	 */
	public ItemStack getItem(String name) {
		int index = items.indexOf(new CrimeItem(name));

		if (index == -1)
			return null;

		return items.get(index).getItem();
	}

}
