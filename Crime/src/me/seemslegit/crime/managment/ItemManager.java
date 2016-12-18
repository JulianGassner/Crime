package me.seemslegit.crime.managment;

import java.util.ArrayList;

import me.seemslegit.crime.api.ItemAPI;
import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.items.ItemFunctions;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

	public ArrayList<CrimeItem> items = new ArrayList<CrimeItem>();

	public void init() {
		Bukkit.getPluginManager().registerEvents(new ItemFunctions(), Main.instance);
		
		CrimeItem drugs = new CrimeItem("drugs", new ItemAPI().material(Material.SUGAR).displayName("§cDrugs").build());
		drugs.setUnbreakable(false);
		drugs.setIllegal(true);
		drugs.register();
		
		CrimeItem rawdrugs = new CrimeItem("rawdrugs", new ItemAPI().material(Material.SUGAR_CANE).displayName("§cRaw Drugs").build());
		rawdrugs.setUnbreakable(false);
		rawdrugs.setIllegal(true);
		rawdrugs.register();
		
		CrimeItem wheat = new CrimeItem("wheat", new ItemAPI()
				.material(Material.WHEAT).displayName("§eWheat").lore("§7Food")
				.amout(1).build());
		wheat.setUnbreakable(false);
		wheat.register();

		CrimeItem bread = new CrimeItem("bread", new ItemAPI()
				.material(Material.BREAD).displayName("§eBread").lore("§7Food")
				.amout(2).build());
		bread.register();

		CrimeItem copsword = new CrimeItem("copsword", new ItemAPI()
				.material(Material.IRON_SWORD).lore("§3Cop Tool")
				.displayName("§cSword of Law").build());
		copsword.setIllegal(true);
		copsword.register();

		CrimeItem handcuffs = new CrimeItem("handcuffs", new ItemAPI()
				.material(Material.LEASH).lore("§3Cop Tool")
				.displayName("§cHandcuffs").build());
		handcuffs.setIllegal(true);
		handcuffs.register();

		CrimeItem uncuff = new CrimeItem("uncuff", new ItemAPI()
				.material(Material.SHEARS).lore("§3Cop Tool")
				.displayName("§aUncuff").build());
		uncuff.setIllegal(true);
		uncuff.register();

		CrimeItem c4 = new CrimeItem("c4", new ItemAPI().material(Material.TNT)
				.displayName("§cC4").lore("§cExplosive").build());
		c4.setIllegal(true);
		c4.register();

		CrimeItem cit = new CrimeItem("cit", new ItemAPI()
				.material(Material.REDSTONE_TORCH_ON)
				.displayName("§cClear illlegal items").lore("§3Cop Tool")
				.build());
		cit.setIllegal(true);
		cit.setOnlyCop(true);
		cit.register();

		CrimeItem book = new CrimeItem("book", new ItemAPI().lore("§7Tool")
				.material(Material.BOOK).displayName("§aCitizen Index").build());
		book.register();

		/*
		 * items.add(cit); items.add(c4); items.add(uncuff); items.add(bread);
		 * items.add(copsword); items.add(handcuffs);
		 */
	}

	public ItemManager() {
		// init();
	}

	/**
	 * 
	 * @param name
	 *            {@link String}
	 * @return {@link ItemStack}
	 */
	public ItemStack getItem(String name) {
		int index = items.indexOf(new CrimeItem(name));

		if (index == -1)
			return null;

		return items.get(index).getItem();
	}

}
