package me.seemslegit.crime.regions;

import java.io.File;
import java.util.ArrayList;

import me.seemslegit.crime.plugin.Main;

import org.bukkit.World;

public class WorldRegionPool {

	private World w;
	private Region[] regions = Main.instance.getRegionManager().getEmptyRegionArray();
	
	/**
	 * 
	 * @param w {@link World}
	 */
	public WorldRegionPool(World w) {
		this.w = w;
	}
	
	public void load() {
		File f = Region.worldToDirectory(getWorld());
		
		ArrayList<Region> regions = new ArrayList<Region>();
		
		for(File ll : f.listFiles()) {
			
			if(ll.isDirectory()) continue;
		
			if(!ll.getName().endsWith(".yml")) continue;
			
			String id = ll.getName().substring(0, ll.getName().length() - (1 + 4));
			
			regions.add(new Region(id, getWorld().getUID()));
			
		}
		
		Region[] finalreturn = new Region[regions.size()];
		
		for(int i = 0;i<regions.size();i++) finalreturn[i] = regions.get(i);
		
		this.regions = finalreturn;
	}
	
	/**
	 * 
	 * @return {@link World}
	 */
	public World getWorld() {
		return w;
	}
	
	/**
	 * 
	 * @return {@link Region}
	 */
	public Region[] getRegions() {
		return regions;
	}
	
}
