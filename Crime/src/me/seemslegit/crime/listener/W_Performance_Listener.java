package me.seemslegit.crime.listener;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class W_Performance_Listener implements Listener {

	/**
	 * 
	 * @param e {@link BlockBurnEvent}
	 */
	@EventHandler
	public void onBurn(BlockBurnEvent e) {
		e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link BlockPhysicsEvent}
	 */
	@EventHandler
	public void onBlockPhysik(BlockPhysicsEvent e) {
		e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link PlayerInteractEvent}
	 */
	@EventHandler
	public void onPhysik(PlayerInteractEvent e) {
		if(e.getAction() == Action.PHYSICAL) {
			Block b = e.getClickedBlock();
			switch(b.getType()) {
			case STONE_PLATE:
			case WOOD_PLATE:
			case IRON_PLATE:
			case GOLD_PLATE:
			default:
				e.setCancelled(true);
			}
		}
	}
	
	/**
	 * 
	 * @param e {@link BlockSpreadEvent}
	 */
	@EventHandler
	public void onSpread(BlockSpreadEvent e) {
		e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link PortalCreateEvent}
	 */
	@EventHandler
	public void onPortal(PortalCreateEvent e) {
		e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link LeavesDecayEvent}
	 */
	@EventHandler
	public void onDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
	
}
