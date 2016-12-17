package me.seemslegit.crime.listener;

import me.seemslegit.crime.managment.CrimeManager;
import me.seemslegit.crime.playerapi.User;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class P_Crime_Listener implements Listener{

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
