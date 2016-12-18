package me.seemslegit.crime.farming;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

public class Plants implements Listener{
	
	@EventHandler
	public void onGrow(BlockGrowEvent e) {
		int size = 1;
		if (e.getBlock().getType().equals(Material.SUGAR_CANE_BLOCK)) {
			Location loc = e.getBlock().getLocation();
			loc.add(0, -1, 0);
			while (loc.getBlock().getType() == e.getNewState().getType() && size < 3) {
				size++;
				loc.add(0, -1, 0);
			}
			if(size > 2) e.setCancelled(true);
		}else if(e.getBlock().getType() == Material.SEEDS) {
			
		}else e.setCancelled(true);

	}

}
