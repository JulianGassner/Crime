package me.seemslegit.crime.listener;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.managment.CrimeManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class P_Crime_Listener implements Listener{

	/**
	 * 
	 * @param e {@link PlayerRespawnEvent}
	 */
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		Player p = e.getPlayer();
		
		User u = new User(p);
		
		if(u.hasCrime()) {
			e.setRespawnLocation(Main.instance.getJailManager().getLocation());
			Main.instance.getJailManager().sendToJail(u);
			return;
		}
		if(Main.instance.getSpawn() == null) return;
		e.setRespawnLocation(Main.instance.getSpawn());
		
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
		
		if(!(killer instanceof Player)) return;
		
		Player p = (Player) killer;
		User t = new User(p);
		
		
		
		if(u.getCrime() >= 1000) {
			Bukkit.broadcastMessage(Messages.prefix+"§a"+p.getName()+"§e got the bounty of §c"+Bukkit.getOfflinePlayer(u.getUUID()).getName());
		}else if(t.isCop()){
			return;
		}else{
			t.addCrime(CrimeManager.CRIME_PER_KILL);
			killer.sendMessage("§eYou have "+t.getCrime()+" Crime left...");
		}
		
	}
	
	/**
	 * 
	 * @param e {@link EntityDamageByEntityEvent}
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onHit(EntityDamageByEntityEvent e){
		if(e.isCancelled()) return;
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			Player t = (Player) e.getDamager();
			Player p = (Player) e.getEntity();
			UserBase tu = new User(t);
			User pu = new User(p);
			
			if(tu.isCop()) return;
			if(pu.hasCrime()) return;
			
			tu.addCrime(CrimeManager.CRIME_PER_HIT);
		}
	}
	
}
