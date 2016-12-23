package me.seemslegit.crime.listener;

import me.seemslegit.crime.events.RegionLeavingEvent;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.RegionType;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;


public class P_Cop_Listener implements Listener{

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLeaveR(RegionLeavingEvent e) {
		if(e.isCancelled()) return;
		
		if(e.getRegion().getType() != RegionType.JAIL) return;
		
		if(!new User(e.getPlayer()).isInJail()) return;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			User t = new User(p);
			if(t.isCop()) p.sendMessage("§c" + e.getPlayer().getName() + " left jail!");
		}
		
	}
	
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
