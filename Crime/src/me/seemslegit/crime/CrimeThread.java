package me.seemslegit.crime;

import me.seemslegit.crime.plugin.Main;

public class CrimeThread implements Runnable{

	private static boolean running = false;
	
	public void run() {
		if(running) return;
		running = true;
		try{
		
			while(true) {
				Thread.sleep(1000l);
				org.spigotmc.AsyncCatcher.enabled = false;
				
				
				
			}
		
		}catch(Exception e) {
			running = false;
			Main.instance.startCrimeThread();
		}
		running = false;
		
	}
	
}
