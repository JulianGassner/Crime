package me.seemslegit.crime.events;

import me.seemslegit.crime.regions.Region;

import org.bukkit.entity.Player;

public class RegionLeavingEvent extends RegionEvent{

	private Player p;
	
	/**
	 * 
	 * @param r {@link Region}
	 * @param p {@link Player}
	 */
	public RegionLeavingEvent(Region r, Player p) {
		super(r);
		this.p = p;
	}
	
	/**
	 * 
	 * @return {@link Player}
	 */
	public Player getPlayer() {
		return p;
	}
	
}
