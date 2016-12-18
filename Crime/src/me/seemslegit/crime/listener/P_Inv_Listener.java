package me.seemslegit.crime.listener;

import me.seemslegit.crime.playerapi.User;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class P_Inv_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerQuitEvent}
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		User u = new User(p);
		
		u.cacheInventory(p.getInventory());
		
		p.getInventory().clear();
		
	}
	
	/**
	 * 
	 * @param e {@link PlayerJoinEvent}
	 */
	@EventHandler(priority = EventPriority.LOW)
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		User u = new User(p);
		
		if(u.isInJail()) return;
		
		Inventory inv = u.loadCachedInventory();
		
		if(inv == null) {
			giveDefaultInv(p);
			return;
		}
		
		u.setInventory(inv);
		
	}
	
	private void giveDefaultInv(Player p) {
		
	}
	
}
