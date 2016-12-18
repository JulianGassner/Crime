package me.seemslegit.crime.listener;

import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;


public class P_Cop_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerDropItemEvent}
	 */
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		User u = new User(p);
		if(u.isCop()) e.setCancelled(true);
		if(u.isInJail()) e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link PlayerPickupItemEvent}
	 */
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		User u = new User(p);
		if(u.isCop()) e.setCancelled(true);
		if(u.isInJail()) e.setCancelled(true);

		if(!e.isCancelled()) {
			if(e.getItem().getItemStack().getType() == Material.SUGAR_CANE) {
				int amout = e.getItem().getItemStack().getAmount();
				
				ItemStack drugs = Main.instance.getItemManager().getItem("rawdrugs");
				drugs.setAmount(amout);
				
				p.getInventory().addItem(drugs);
				
				e.setCancelled(true);
				e.getItem().remove();
			}
		}
	}
	
}
