package me.seemslegit.crime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

public class CrimeThread implements Runnable{

	private static boolean running = false;
	
	public void run() {
		if(running) return;
		running = true;
		try{
		
			while(true) {
				Thread.sleep(1000l);
				for(Player p : Bukkit.getOnlinePlayers()){
					UserBase u = new User(p);
					Main.instance.getCrimeManager().updateCrimeBoard(u);
				}
				org.spigotmc.AsyncCatcher.enabled = false;
				
				
				
			}
		
		}catch(Exception e) {
			running = false;
			Main.instance.startCrimeThread();
		}
		running = false;
		
	}
	
}
