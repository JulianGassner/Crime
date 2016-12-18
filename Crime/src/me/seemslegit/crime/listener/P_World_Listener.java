package me.seemslegit.crime.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class P_World_Listener implements Listener{
 
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.isCancelled()) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.isCancelled()) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
	}
	
}
