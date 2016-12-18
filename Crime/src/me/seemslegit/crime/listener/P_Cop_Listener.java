package me.seemslegit.crime.listener;

import me.seemslegit.crime.playerapi.User;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;


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
	}
	
}
