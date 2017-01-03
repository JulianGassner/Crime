package me.seemslegit.crime.api;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CachedBlock {

	Material mat;
	byte b;
	Location loc;
	public long ssio;
	
	/**
	 * 
	 * @param b {@link Block}
	 */
	@SuppressWarnings("deprecation")
	public CachedBlock(Block b) {
		
		mat = b.getType();
		this.b = b.getData();
		loc = b.getLocation();
		ssio = System.currentTimeMillis() + 1000 * 60;
		
	}
	
	/**
	 * 
	 * @return {@link Material}
	 */
	public Material getMat() {
		return mat;
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLoc() {
		return loc;
	}
	
	/**
	 * 
	 * @return {@link Byte}
	 */
	public byte getData() {
		return b;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return arg0.equals(loc);
	}
	
}
