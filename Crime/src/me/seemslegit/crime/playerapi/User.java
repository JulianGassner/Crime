package me.seemslegit.crime.playerapi;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class User extends UserBase {

	/**
	 * Plugin defined player
	 * @param u {@link UUID}
	 */
	public User(UUID u) {
		super(u);
	}
	
	/**
	 * 
	 * @param name {@link String}
	 */
	@SuppressWarnings("deprecation")
	public User(String name) {
		this(Bukkit.getOfflinePlayer(name));
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 */
	public User(Player p) {
		this(p.getUniqueId());
	}
	
	/**
	 * 
	 * @param op {@link OfflinePlayer}
	 */
	public User(OfflinePlayer op) {
		this(op.getUniqueId());
	}
	
}
