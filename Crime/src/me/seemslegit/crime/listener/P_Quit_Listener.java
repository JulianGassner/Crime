package me.seemslegit.crime.listener;

import me.seemslegit.crime.Messages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class P_Quit_Listener implements Listener {

	/**
	 * 
	 * @param e
	 *            {@link PlayerQuitEvent}
	 */
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {

		Player p = e.getPlayer();

		e.setQuitMessage(Messages.p_join.replace("%1%", p.getDisplayName()));

	}

}
