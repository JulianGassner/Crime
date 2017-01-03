package me.seemslegit.crime.playerapi;

import java.io.File;

import me.seemslegit.crime.api.Config;

public class UserConfig extends Config{

	private UserBase u;
	private String cfg;
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @param cfg {@link String}
	 */
	public UserConfig(UserBase u, String cfg) {
		super(new File("Crime//DB//" + u.getUUID().toString(), cfg + ".yml"));
		this.u = u;
		this.cfg = cfg;
	}
	
	/**
	 * 
	 * @return {@link UserBase}
	 */
	public UserBase getUser() {
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
