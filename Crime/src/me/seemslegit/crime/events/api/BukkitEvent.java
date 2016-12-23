package me.seemslegit.crime.events.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BukkitEvent extends Event{

	static HandlerList hl = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return hl;
	}

	public static HandlerList getHandlerList() {
		return hl;
	}
	

}
