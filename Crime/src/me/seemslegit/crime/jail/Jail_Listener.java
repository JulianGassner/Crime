package me.seemslegit.crime.jail;

import me.seemslegit.crime.managment.JailManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
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
	 * @param e {@link EntityDamageByEntityEvent}
	 */
	@EventHandler
	public void onDMGDeal(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;
		
		Player p = (Player) e.getDamager();
		
		User u = new User(p);
		if(u.isInJail()) e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link EntityDamageEvent}
	 */
	@EventHandler
	public void onDMG(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		User u = new User(p);
		if(u.isInJail()) e.setCancelled(true);
	}
	
	/**
	 * 
	 * @param e {@link FoodLevelChangeEvent}
	 */
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		
		if(!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		User u = new User(p);
		
		if(!u.isInJail() && !u.isCop()) return;
		
		if(e.getFoodLevel() < 20) {
			e.setFoodLevel(20);
		}
		
	}
	
	/**
	 * 
	 * @return {@link JailManager}
	 */
	private JailManager getJailManager() {
		return Main.instance.getJailManager();
	}
	
}
