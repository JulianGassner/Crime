package me.seemslegit.crime.cop;

import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;

import com.mojang.authlib.yggdrasil.response.User;

public class CopManager {

	public CopManager() {
		init();
	}
	
	private void init() {
		Main.registerCommandonBukkit("cop");
		Bukkit.getPluginCommand("cop").setExecutor(new CopCMD());
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
	/**
	 * 
	 * @param u {@link User}
	 */
	
	 
	public static void cuff(UserBase u){
		
	}
	
	
	
}
