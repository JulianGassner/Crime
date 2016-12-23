package me.seemslegit.crime.regions;

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
