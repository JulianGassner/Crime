package me.seemslegit.crime.cop;

import me.seemslegit.crime.playerapi.UserBase;

public class CopManager {

	public CopManager() {
		init();
	}
	
	private void init() {
		
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Boolean}
	 */
	public static boolean isCop(UserBase u) {
		return u.getStats().getBoolean("cop", false);
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link String}
	 */
	public static String switchCop(UserBase u){
		boolean b = isCop(u);
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
