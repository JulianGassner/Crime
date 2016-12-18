package me.seemslegit.crime.cop;

import me.seemslegit.crime.api.ItemAPI;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;


public class CopItems implements Listener{
	
	ItemStack copsword = new ItemAPI().material(Material.IRON_SWORD).displayName("§cSword of Law").build();
	ItemStack handcuffs = new ItemAPI().material(Material.LEASH).displayName("§cHandcuffs").build();
	ItemStack shears = new ItemAPI().material(Material.SHEARS).displayName("§aUncuff").build();
	ItemStack tortch = new ItemAPI().material(Material.REDSTONE_TORCH_ON).displayName("§cClear illlegal items").build();
	ItemStack tnt = new ItemAPI().material(Material.TNT).displayName("§cC4").build();
	//todo - Weapons
	
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
		
		p.getInventory().clear();
		p.getInventory().setItem(0, copsword);
		p.getInventory().setItem(1, handcuffs);
		p.getInventory().setItem(2, shears);
		p.getInventory().setItem(3, tortch);
		p.getInventory().setItem(4, tnt);
		
	}
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e){
		if(!(e.getRightClicked() instanceof Player)) return;
		
		Player t = (Player) e.getRightClicked();
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getType().equals(handcuffs.getType())){
			Main.instance.getCopManager().cuff(new User(t));
		}else if(p.getItemInHand().getType().equals(copsword.getType())){
			
		}else if(p.getItemInHand().getType() == shears.getType()) {
			Main.instance.getCopManager().uncuff(new User(t));
		}
	}
	
	

}
