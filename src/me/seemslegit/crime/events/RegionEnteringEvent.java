package me.seemslegit.crime.events;

import me.seemslegit.crime.regions.Region;

import org.bukkit.entity.Player;


public class RegionEnteringEvent extends RegionEvent{

	private Player p;
	
	/**
	 * 
	 * @param r {@link Region}
	 * @param p {@link Player}
	 */
	public RegionEnteringEvent(Region r, Player p) {
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
