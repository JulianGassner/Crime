package me.seemslegit.crime.regions;

import java.io.File;
import java.util.UUID;

import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Region {

	private String id;
	private Config cfg;
	private UUID worlduid;
	
	/**
	 * 
	 * @param id {@link String}
	 * @param worlduid {@link UUID}
	 */
	public Region(String id, UUID worlduid) {
		this.id = id;
		this.worlduid = worlduid;
		setupConfig();
	}
	
	public RegionType getType() {
		
		try{
			
			String str = cfg.getString("type");
			
			if(str != null) {
				RegionType rt = RegionType.valueOf(str);
				
				if(rt != null) return rt;
			}
			
		}catch(Exception e) {
			Main.instance.getErrorManager().registerError(e);
		}
		
		return RegionType.NONE;
	}
	
	/**
	 * 
	 * @param rt {@link RegionType}
	 */
	public void setType(RegionType rt) {
		cfg.set("type", rt.toString());
	}
	
	private void reloadRegionPool() {
		
		World w = Bukkit.getWorld(getWorldUid());
		
		if(w == null) return;
		
		Main.instance.getRegionManager().getWorldRegionPool(w).load();
		
	}
	
	/**
	 * 
	 * @param w {@link World}
	 * @return {@link File}
	 */
	public static File worldToDirectory(World w) {
		return worldToDirectory(w.getUID());
	}
	
	/**
	 * 
	 * @param u {@link UUID}
	 * @return {@link File}
	 */
	public static File worldToDirectory(UUID u) {
		return new File("Crime//Regions//" + u.toString());
	}
	
	public void delete() {
		cfg.getFile().delete();
		reloadRegionPool();
	}
	
	private void setupConfig() {
		cfg = new Config(new File(worldToDirectory(getWorldUid()), getID() + ".yml"));
	}
	
	/**
	 * 
	 * @return {@link UUID}
	 */
	public UUID getWorldUid() {
		return worlduid;
	}
	
	/**
	 * 
	 * @param msg {@link String}
	 */
	public void setFarewell(String msg) {
		cfg.set("farewell", msg);
		reloadRegionPool();
	}
	
	/**
	 * 
	 * @param msg {@link String}
	 */
	public void setGreeting(String msg) {
		cfg.set("greeting", msg);
		reloadRegionPool();
	}
	
	/**
	 * 
	 * @return {@link String}
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLoc1() {
		return cfg.getLocation("loc1");
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLoc2() {
		return cfg.getLocation("loc2");
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 */
	public void setLoc1(Location loc) {
		cfg.setLocation("loc1", loc);
		reloadRegionPool();
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 */
	public void setLoc2(Location loc) {
		cfg.setLocation("loc2", loc);
		reloadRegionPool();
	}
	
	/**
	 * 
	 * @param loc {@link Location}
	 * @return {@link Boolean}
	 */
	public boolean isIn(Location loc) {
		
		Location loc1 = getLoc1();
		Location loc2 = getLoc2();
		
		if(loc == null) return false;
		
		if(loc1 == null || loc2 == null) return false;
		
		if(loc1.getWorld().getUID() != loc2.getWorld().getUID()) return false;
		
		if(loc.getWorld().getUID() != loc.getWorld().getUID()) return false;
		
		double x_min = Math.min(loc1.getBlockX(), loc2.getBlockX());
		double x_max = Math.max(loc1.getBlockX(), loc2.getBlockX());
		
		double y_min = Math.min(loc1.getBlockY(), loc2.getBlockY());
		double y_max = Math.max(loc1.getBlockY(), loc2.getBlockY());
		
		double z_min = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		double z_max = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		
		double x = loc.getBlockX();
		double y = loc.getBlockY();
		double z = loc.getBlockZ();
		
		if(x >= x_min && x <= x_max) {
			
			if(y >= y_min && y <= y_max) {
				
				if(z >= z_min && z <= z_max) return true;
				
			}
			
		}
		
		return false;
	}

	/**
	 * 
	 * @return {@link String}
	 */
	public String getFarewell() {
		return cfg.getString("farewell", null);
	}

	public String getGreeting() {
		return cfg.getString("greeting", null);
	}
	
}
