package me.seemslegit.crime.listener;

import java.util.ArrayList;

import me.seemslegit.crime.plugin.Main;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class P_World_Listener implements Listener{
 
	/**
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<Material> allowBreak() {
		
		ArrayList<Material> allow = new ArrayList<Material>();
		
		allow.add(Material.CROPS);
		allow.add(Material.SUGAR_CANE_BLOCK);
		
		return allow;
	}
	
	/**
	 * 
	 * @param e {@link BlockPlaceEvent}
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.isCancelled()) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link BlockBreakEvent}
	 */
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.isCancelled()) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		if(allowBreak().contains(e.getBlock().getType())) {
			if(e.getBlock().getType() == Material.CROPS) {
				e.setCancelled(true);
				if(e.getBlock().getData() != 7) return;
				e.getBlock().setType(Material.CROPS);
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), Main.instance.getItemManager().getItem("wheat"));
			}else if(e.getBlock().getType() == Material.SUGAR_CANE_BLOCK) {
				
				/*Material mat = e.getBlock().getLocation().add(0, -1, 0).getBlock().getType();
				
				if(mat == Material.SUGAR_CANE_BLOCK) {
					
				}else e.setCancelled(true);*/
				
			}
			return;
		}
		e.setCancelled(true);
	}
	
}
