package me.seemslegit.crime.events;

import me.seemslegit.crime.events.api.BukkitEvent;
import me.seemslegit.crime.regions.Region;

public abstract class RegionEvent extends BukkitEvent{

	private Region r;
	private boolean cancelled = false;
	
	/**
	 * 
	 * @param r {@link Region}
	 */
	public RegionEvent(Region r) {
		this.r = r;
	}
	
	/**
	 * 
	 * @return {@link Region}
	 */
	public Region getRegion() {
		return r;
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isCancelled() {
		return cancelled;
	}
	
	/**
	 * 
	 * @param b {@link Boolean}
	 */
	public void setCancelled(boolean b) {
		this.cancelled = b;
	}
	
}
