package me.seemslegit.crime.listener;

import me.seemslegit.crime.Messages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class P_Join_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerJoinEvent}
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		e.setJoinMessage(Messages.p_join.replace("%1%", p.getDisplayName()));
		
	}
	
}
