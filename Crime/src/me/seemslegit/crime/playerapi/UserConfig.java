package me.seemslegit.crime.playerapi;

import java.io.File;

import me.seemslegit.crime.api.Config;

public class UserConfig extends Config{

	private User u;
	private String cfg;
	
	/**
	 * 
	 * @param u {@link User}
	 * @param cfg {@link String}
	 */
	public UserConfig(User u, String cfg) {
		super(new File("DB//" + u.getUUID().toString(), cfg + ".yml"));
		this.u = u;
		this.cfg = cfg;
	}
	
	/**
	 * 
	 * @return {@link User}
	 */
	public User getUser() {
		return u;
	}
	
	
	/**
	 * 
	 * @return {@link String}
	 */
	public String getCFG() {
		return cfg;
	}
	
	
}
