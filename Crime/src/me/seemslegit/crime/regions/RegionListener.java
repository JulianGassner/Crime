package me.seemslegit.crime.regions;

import java.util.ArrayList;
import java.util.HashMap;

import me.seemslegit.crime.plugin.Main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegionListener implements Listener{

	private HashMap<String, Region[]> last = new HashMap<String, Region[]>();
	
	public RegionListener() {
		
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		Location loc1 = e.getFrom();
		Location loc2 = e.getTo();
		
		Location b_loc1 = loc1.getBlock().getLocation();
		Location b_loc2 = loc2.getBlock().getLocation();
		
		if(b_loc1.equals(b_loc2)) return;
		
		String uuid = p.getUniqueId().toString();
		
		Region[] in = Main.instance.getRegionManager().getRegions(loc2);
		
		ArrayList<Region> newr = new ArrayList<Region>();
		for(Region r : in) newr.add(r);
		
		if(last.containsKey(uuid)) {
			
			Region[] lastr = last.get(uuid);
			
			for(Region r : lastr) {
				if(newr.contains(r)) {
					newr.remove(r);
				}else{
					if(r.getFarewell() != null) p.sendMessage(r.getFarewell());
				}
			}
			
		}
		
		for(Region r : newr) {
			if(r.getGreeting() != null) p.sendMessage(r.getGreeting());
		}
		
		
		if(e.isCancelled()) return;
		if(last.containsKey(uuid)) {
			last.remove(uuid);
		}
		last.put(uuid, in);
	}
	
}
