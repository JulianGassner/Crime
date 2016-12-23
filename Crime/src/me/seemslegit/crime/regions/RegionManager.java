package me.seemslegit.crime.regions;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;

public class RegionManager {

	private ArrayList<WorldRegionPool> regions = new ArrayList<WorldRegionPool>();
	
	public RegionManager() {
		
		
		
	}
	
	public Region[] getEmptyRegionArray() {
		return new Region[0];
	}
	
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
	
	public Region[] getRegions(World w) {
		
		for(WorldRegionPool wrp : regions) {
			
			if(wrp.getWorld().getUID() == w.getUID()) return wrp.getRegions();
			
		}
		
		return getEmptyRegionArray();
	}
	
}
