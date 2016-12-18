package me.seemslegit.crime.listener;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class P_Join_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerLoginEvent}
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent e) {
		if(!Main.instance.isStarted()) {
			e.disallow(Result.KICK_OTHER, "§8> §cServer is still starting...");
		}
	}
	
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
