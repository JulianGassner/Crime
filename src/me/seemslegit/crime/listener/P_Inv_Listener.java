package me.seemslegit.crime.listener;

import me.seemslegit.crime.api.PlayerCache;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class P_Inv_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerQuitEvent}
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		User u = new User(p);
		
		if(u.isCop()) return;

		u.setCachedLocation(p.getLocation());
		u.cacheInventory(p.getInventory(), p.getInventory().getArmorContents());
		
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
		
		if(u.isCop()) return;
		
		Inventory inv = u.loadCachedInventory();
		ItemStack[] armor = u.loadCachedArmor();
		
		if(inv == null) {
			giveDefaultInv(p);
			return;
		}
		
		u.setInventory(inv, armor);
		Location loc = u.getCachedLocation();
		if(loc == null) return;
		u.teleport(loc);
		
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 */
	private void giveDefaultInv(Player p) {
		new PlayerCache(p).clearPlayer();
		
		p.getInventory().addItem(Main.instance.getItemManager().getItem("bread"));
		
		p.updateInventory();
		
		if(Main.instance.getSpawn() == null) return;
		p.teleport(Main.instance.getSpawn());
	}
	
}
