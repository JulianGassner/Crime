package me.seemslegit.crime.regions;

import java.util.ArrayList;
import java.util.HashMap;

import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class RegionManager {

	private HashMap<String, Location> pos1 = new HashMap<String, Location>();
	private HashMap<String, Location> pos2 = new HashMap<String, Location>();
	
	private ArrayList<WorldRegionPool> regions = new ArrayList<WorldRegionPool>();
	
	public void init() {
		Bukkit.getPluginManager().registerEvents(new RegionListener(), Main.instance);
		
		Main.registerCommandonBukkit("region");
		Bukkit.getPluginCommand("region").setExecutor(new RegionCommand());
	}
	
	public RegionManager() {

	}
	
	/**
	 * 
	 * @param fullid {@link String}
	 * @return {@link Region}
	 */
	public Region getRegion(String fullid) {
		
		for(WorldRegionPool wrp : regions) {
			
			for(Region r : wrp.getRegions()) {
				if(r.getFullID().equalsIgnoreCase(fullid)) return r;
			}
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param w {@link World}
	 * @param id {@link String}
	 * @return {@link Region}
	 */
	public Region getRegion(World w, String id) {
		WorldRegionPool wrp = getWorldRegionPool(w);
		
		for(Region r : wrp.getRegions()) {
			if(r.getID().equalsIgnoreCase(id)) {
				return r;
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @param p {@link Player}
	 * @return {@link Boolean}
	 */
	public boolean hasBothPositions(Player p) {
		return getPos1(p) != null && getPos2(p) != null;
 	}
	
	/**
	 * 
	 * @param p {@link Player}
	 * @return {@link Location}
	 */
	public Location getPos1(Player p) {
		return pos1.get(p.getUniqueId().toString());
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 * @return {@link Location}
	 */
	public Location getPos2(Player p) {
		return pos2.get(p.getUniqueId().toString());
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 * @param loc {@link Location}
	 */
	public void setPos2(Player p, Location loc) {
		String uuid = p.getUniqueId().toString();
		if(pos2.containsKey(uuid)) pos2.remove(uuid);
		pos2.put(uuid, loc);
		p.sendMessage("§dPosition 2 set.");
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 * @param loc {@link Location}
	 */
	public void setPos1(Player p, Location loc) {
		String uuid = p.getUniqueId().toString();
		if(pos1.containsKey(uuid)) pos1.remove(uuid);
		pos1.put(uuid, loc);
		p.sendMessage("§dPosition 1 set.");
	}
	
	/**
	 * 
	 * @param w {@link World}
	 * @return {@link WorldRegionPool}
	 */
	public WorldRegionPool getWorldRegionPool(World w) {
		for(WorldRegionPool wrp : regions) {
			
			if(wrp.getWorld().getUID() == w.getUID()) return wrp;
			
		}
		
		WorldRegionPool wrp = new WorldRegionPool(w);
		
		wrp.load();
		
		regions.add(wrp);
		
		return wrp;
	}
	
	/**
	 * 
	 * @return {@link Region}
	 */
	public Region[] getEmptyRegionArray() {
		return new Region[0];
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 * @return {@link Region}
	 */
	public Region[] getRegions(Location loc) {
		if(loc == null) return getEmptyRegionArray();
		
		Region[] regs = getRegions(loc.getWorld());
		if(regs == null || regs.length == 0) return getEmptyRegionArray();
		ArrayList<Region> in = new ArrayList<Region>();
		
		for(Region r : regs) {
			if(r == null) continue;
			if(!r.isIn(loc)) continue;
			in.add(r);
		}
		
		Region[] returnstatement = new Region[in.size()];
		
		for(int i = 0;i<in.size();i++) {
			returnstatement[i] = in.get(i);
		}
		
		return returnstatement;
	}
	
	/**
	 * 
	 * @param w {@link World}
	 * @return {@link Region}
	 */
	public Region[] getRegions(World w) {
		
		WorldRegionPool wrp = getWorldRegionPool(w);
		
		if(wrp != null) return wrp.getRegions();
		
		return getEmptyRegionArray();
	}
	
}
