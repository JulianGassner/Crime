package me.seemslegit.crime.regions;

import java.util.ArrayList;

import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class RegionManager {

	private ArrayList<WorldRegionPool> regions = new ArrayList<WorldRegionPool>();
	
	public void init() {
		Bukkit.getPluginManager().registerEvents(new RegionListener(), Main.instance);
	}
	
	public RegionManager() {

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
