package me.seemslegit.crime.jail;

import me.seemslegit.crime.managment.JailManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class Jail_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerJoinEvent}
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		User u = new User(p);
		
		if(!getJailManager().isInJail(u)) return;
		
		getJailManager().getJail().initPlayer(u);
		
	}
	
	
	/**
	 * 
	 * @return {@link JailManager}
	 */
	private JailManager getJailManager() {
		return Main.instance.getJailManager();
	}
	
}
