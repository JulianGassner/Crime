package me.seemslegit.crime.farming;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

public class Drugs implements Listener{
	
	@EventHandler
	public void onGrow(BlockGrowEvent e){
		int size = 1;
		Location loc = e.getBlock().getLocation();
		loc.add(0, -1, 0);
		while(loc.getBlock().getType() == e.getNewState().getType()) {
			size ++;
			loc.add(0, -1, 0);
			Bukkit.broadcastMessage(""+size + " - " + e.getNewState().getType().toString());
		}
		
		
	}

}
