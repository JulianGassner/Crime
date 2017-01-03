package me.seemslegit.crime.listener;

import io.netty.channel.Channel;
import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.shops.ShopManager;
import me.seemslegit.crime.shops.api.CustomChannelHandler;
import me.seemslegit.crime.shops.api.Shop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		e.setJoinMessage(Messages.p_join.replace("%1%", p.getDisplayName()));
		
		final Player t = p;
		final Location old = t.getLocation().clone();			
		final boolean flight = t.getAllowFlight();
		final boolean flying = t.isFlying();
		
		t.setAllowFlight(true);
		
		t.teleport(t.getLocation().add(0, 20000, 0));
		t.setFlying(true);
		
		ShopManager sm = Main.instance.getShopManager();
		
		for(Shop s : sm.shops) {
			s.spawnEntity(p);
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			
			public void run() {
				
				t.teleport(old);
				t.setFlying(flying);
				t.setAllowFlight(flight);
				
			}
		}, 20);
		
		Channel c = ((CraftPlayer)p).getHandle().playerConnection.networkManager.channel;
		
		c.pipeline().addAfter("decoder", "PacketInjector", new CustomChannelHandler(p));
		
	}
	
}
