package me.seemslegit.crime.regions;

import java.util.ArrayList;
import java.util.HashMap;

import me.seemslegit.crime.events.RegionEnteringEvent;
import me.seemslegit.crime.events.RegionLeavingEvent;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
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

	private synchronized boolean handleMove(Player p, Location loc1, Location loc2) {
		Location b_loc1 = loc1.getBlock().getLocation();
		Location b_loc2 = loc2.getBlock().getLocation();
		
		if(b_loc1.equals(b_loc2)) return false;
		
		boolean b = false;
		
		String uuid = p.getUniqueId().toString();
		
		Region[] in = Main.instance.getRegionManager().getRegions(loc2);
		
		ArrayList<Region> newr = new ArrayList<Region>();
		for(Region r : in) newr.add(r);
		
		ArrayList<String> messages = new ArrayList<String>();
		
		if(last.containsKey(uuid)) {
			
			Region[] lastr = last.get(uuid);
			
			for(Region r : lastr) {
				if(newr.contains(r)) {
					newr.remove(r);
				}else{
					RegionLeavingEvent rle = new RegionLeavingEvent(r, p);
					Bukkit.getPluginManager().callEvent(rle);
					
					if(rle.isCancelled()) {
						b = true;
						break;
					}
					
					if(r.getFarewell() != null) messages.add(r.getFarewell());
				}
			}
			
		}
		
		for(Region r : newr) {
			RegionEnteringEvent ree = new RegionEnteringEvent(r, p);
			Bukkit.getPluginManager().callEvent(ree);
			
			if(ree.isCancelled()) {
				b = true;
				break;
			}
			
			if(r.getGreeting() != null) messages.add(r.getGreeting());
		}
		
		if(!b) {
			for(String s : messages) {
				p.sendMessage(s);
			}
			
			if(last.containsKey(uuid)) {
				last.remove(uuid);
			}
			last.put(uuid, in);
		}
		return b;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		Location loc1 = e.getFrom();
		Location loc2 = e.getTo();
		
		boolean b = handleMove(p, loc1, loc2);
		if(b) p.teleport(loc1);
	}
	
}
