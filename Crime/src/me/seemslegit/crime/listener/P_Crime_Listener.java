package me.seemslegit.crime.listener;

import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.managment.CrimeManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class P_Crime_Listener implements Listener{

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		Player p = e.getPlayer();
		
		User u = new User(p);
		
		if(u.hasCrime()) {
			Main.instance.getJailManager().sendToJail(u);
			return;
		}
		
	}
	
	/**
	 * 
	 * @param e {@link PlayerDeathEvent}
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void onPDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		e.setKeepInventory(true);
		
		for(int i = 0;i<p.getInventory().getSize();i++) {
			ItemStack item = p.getInventory().getItem(i);
			if(item == null || item.getType() == Material.AIR) continue;
			if(CrimeItem.shouldDrop(item)) {
				p.getWorld().dropItem(p.getLocation(), item);
				p.getInventory().setItem(i, null);
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param e {@link EntityDeathEvent}
	 */
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			
			Player p = (Player) e.getEntity();
			
			User u = new User(p);
			
			checkDeath(u, p.getKiller());
		}else if(e.getEntity() instanceof Zombie) {
			
			
			
		}
	}
	
	/**
	 * 
	 * @param u {@link User}
	 * @param killer {@link Entity}
	 */
	private void checkDeath(User u, Entity killer) {
		
		if(u.hasCrime()) {
			
			
			
		}else{
			
			if(killer instanceof Player) {
				
				Player p = (Player) killer;
				User t = new User(p);
				
				t.addCrime(CrimeManager.CRIME_PER_KILL);
				
			}
			
		}
		
	}
	
}
