package me.seemslegit.crime.cop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.seemslegit.crime.api.ItemAPI;

public class CopItems {
	/**
	 * 
	 * @param p {@link Player}
	 */
	public static void giveCopItems(Player p){
		ItemStack copsword = new ItemAPI().material(Material.IRON_SWORD).displayName("§cSword of Law").build();
		ItemStack handcuffs = new ItemAPI().material(Material.LEASH).displayName("§cHandcuffs").build();
		ItemStack shears = new ItemAPI().material(Material.SHEARS).displayName("§aUncuff").build();
		ItemStack tortch = new ItemAPI().material(Material.REDSTONE_TORCH_ON).displayName("§cClear illlegal items").build();
		ItemStack tnt = new ItemAPI().material(Material.TNT).displayName("§cC4").build();
		//todo - Weapons
		
		p.getInventory().clear();
		p.getInventory().setItem(0, copsword);
		p.getInventory().setItem(1, handcuffs);
		p.getInventory().setItem(2, shears);
		p.getInventory().setItem(3, tortch);
		p.getInventory().setItem(4, tnt);
		
	}

}
