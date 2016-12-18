package me.seemslegit.crime.api;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerCache {

	Player p;
	
	//DATA
	Location loc;
	ItemStack[] content;
	ItemStack[] armor;
	int level;
	float exp;
	int fireticks;
	int foodlvl;
	double health;
	float saturation;
	float exhaustion;
	float fall_distance;
	boolean allow_fly;
	boolean flying;
	GameMode gm;
	int no_dmg_ticks;
	Collection<PotionEffect> potions;
	
	/**
	 * 
	 * @param p {@link Player}
	 */
	public PlayerCache(Player p) {
		this.p = p;
		store(p);
	}
	
	/**
	 * 
	 * @param p {@link Player}
	 */
	private void store(Player p) {
		this.loc = p.getLocation();
		this.content = p.getInventory().getContents();
		this.armor = p.getInventory().getArmorContents();
		this.level = p.getLevel();
		this.exp = p.getExp();
		this.fireticks = p.getFireTicks();
		this.foodlvl = p.getFoodLevel();
		this.health = ((CraftPlayer)p).getHealth();
		this.saturation = p.getSaturation();
		this.exhaustion = p.getExhaustion();
		this.fall_distance = p.getFallDistance();
		this.allow_fly = p.getAllowFlight();
		this.gm = p.getGameMode();
		this.no_dmg_ticks = p.getNoDamageTicks();
		this.potions = p.getActivePotionEffects();
	}
	
	/**
	 * 
	 * @return {@link Player}
	 */
	public PlayerCache clearPlayer() {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setLevel(0);
		p.setExp(0f);
		p.setFireTicks(0);
		p.setFoodLevel(20);
		p.setHealth(20d);
		p.setSaturation(0);
		p.setExhaustion(0);
		p.setFallDistance(0);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.setGameMode(GameMode.SURVIVAL);
		p.setNoDamageTicks(0);
		for(PotionEffect pe : potions) {
			p.removePotionEffect(pe.getType());
		}
		p.updateInventory();
		return this;
	}

	public void restore() {
		restore(false);
	}
	
	/**
	 * 
	 * @param tp {@link Boolean}
	 */
	public void restore(boolean tp) {
		clearPlayer();
		if(tp) p.teleport(loc);
		p.getInventory().setContents(content);
		p.getInventory().setArmorContents(armor);
		p.setLevel(level);
		p.setExp(exp);
		p.setFireTicks(fireticks);
		p.setFoodLevel(foodlvl);
		p.setHealth(health);
		p.setSaturation(saturation);
		p.setExhaustion(exhaustion);
		p.setFallDistance(fall_distance);
		p.setAllowFlight(allow_fly);
		p.setFlying(flying);
		p.setGameMode(gm);
		p.setNoDamageTicks(no_dmg_ticks);
		for(PotionEffect pe : potions) {
			p.addPotionEffect(pe);
		}
		p.updateInventory();
	}
	
}
