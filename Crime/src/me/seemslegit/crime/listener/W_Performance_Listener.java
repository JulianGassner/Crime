package me.seemslegit.crime.listener;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;

import me.seemslegit.crime.api.CachedBlock;

public class W_Performance_Listener implements Listener {

	private static ArrayList<CachedBlock> block = new ArrayList<CachedBlock>();
	
	public W_Performance_Listener() {
		
		Thread d = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try{
						Thread.sleep(1000);
						ArrayList<CachedBlock> cache = new ArrayList<CachedBlock>();
	
						for(CachedBlock cb : block) {
							
							if(cb.ssio < System.currentTimeMillis()) {
								restoreBlock(cb);
							}else{
								cache.add(cb);
							}
							
						}
						
						block = cache;
					}catch(Exception e) {
						
					}
					
				}
			}
		});
		d.start();
		
	}
	
	public static void restoreAll() {
		
		for(CachedBlock cb : block) {
			
			restoreBlock(cb);
			
		}
		
	}
	
	/**
	 * 
	 * @param cb {@link CachedBlock}
	 */
	@SuppressWarnings("deprecation")
	private static void restoreBlock(CachedBlock cb) {
		Location loc = cb.getLoc();
		
		loc.getBlock().setType(cb.getMat());
		loc.getBlock().setData(cb.getData());
	}
	
	/**
	 * 
	 * @param e {@link EntityExplodeEvent}
	 */
	@EventHandler
	public void onAllahuAkba(EntityExplodeEvent e) {
		for(Block b : e.blockList()) {
			
			Location loc = b.getLocation();
			
			if(block.contains(loc)) continue;
			
			block.add(new CachedBlock(b));
			b.setType(Material.AIR);
		}
		e.blockList().clear();
	}
	
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
		Material mat = e.getBlock().getLocation().add(0, -1, 0).getBlock().getType();
		Material matl = e.getChangedType();
		if(mat == Material.AIR && matl == Material.SUGAR_CANE_BLOCK) return;
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
