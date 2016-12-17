package me.seemslegit.crime.cop;

import me.seemslegit.crime.playerapi.User;

import org.bukkit.entity.Player;

public class CopManager {

	public CopManager() {
		init();
	}
	
	private void init() {
		
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 * @return {@link Boolean}
	 */
	public static boolean isCop(User u) {
		if(u.hasCrime()) return false;
		return u.getStats().getBoolean("cop", false);
	}
	
	/**
	 * 
	 * @param u {@link User}
	 * @return {@link String}
	 */
	public static String switchCop(User u){
		boolean b = u.getStats().getBoolean("cop", false);
		if(!b) {
			if(u.hasCrime()) {
				return "not successful";
			}else{
				b = true;
			}
		}else{
			b = false;
		}
		
		u.getStats().set("cop", b);
		return "successful";
	}
	
	
}
