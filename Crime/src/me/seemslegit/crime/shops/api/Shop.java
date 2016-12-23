package me.seemslegit.crime.shops.api;

import java.io.File;
import java.util.UUID;

import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.mojang.authlib.GameProfile;

public class Shop {
	
	private String id;
	private Location loc;
	private Region r;
	
	private EntityPlayer e = null;
	
	private Config cfg;
	
	/**
	 * 
	 * @param id {@link String}
	 * @param loc {@link Location}
	 * @param r {@link Region}
	 */
	public Shop(String id, Location loc, Region r) {
		this.id = id;
		
		setupConfig();
		
		setLocation(loc);
		setRegion(r);
	}
	
	public void delete() {
		despawnEntity();
		cfg.getFile().delete();
		
		Main.instance.getShopManager().load();
	}
	
	/**
	 * 
	 * @param r {@link Region}
	 */
	public void setRegion(Region r) {
		cfg.set("region", r.getFullID());
		this.r = r;
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 */
	public void setLocation(Location loc) {
		cfg.setLocation("loc", loc);
		this.loc = loc;
	}
	
	private void setupConfig() {
		
		File f = new File("Crime//Shops", getID() + ".yml");
		
		this.cfg = new Config(f);
		
	}
	
	/**
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		return cfg.getString("name","Shop");
	}
	
	/**
	 * 
	 * @param name {@link String}
	 */
	public void setName(String name) {
		cfg.set("name", name);
	}
	
	public void despawnEntity() {

		if(getEntity() == null) return;
		
		World w = ((CraftWorld)getLocation().getWorld()).getHandle();
		
		w.removeEntity(getEntity());
		
	}
	
	public void spawnEntity() {
		
		WorldServer w = ((CraftWorld)getLocation().getWorld()).getHandle();
		MinecraftServer ms = ((CraftServer)Bukkit.getServer()).getServer();
		
		GameProfile gp = new GameProfile(UUID.randomUUID(), getName());
		
		EntityPlayer ep = new CustomPlayer(
				ms, 
				w, 
				gp, 
				new PlayerInteractManager(w));
		
		w.addEntity(ep);
		
		ep.getBukkitEntity().teleport(getLocation());
	}
	
	/**
	 * 
	 * @return {@link EntityPlayer}
	 */
	public EntityPlayer getEntity() {
		return e;
	}
	
	/**
	 * 
	 * @return {@link Region}
	 */
	public Region getRegion() {
		return r;
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLocation() {
		return loc;
	}
	
	/**
	 * 
	 * @return {@link String}
	 */
	public String getID() {
		return id;
	}
	
}
