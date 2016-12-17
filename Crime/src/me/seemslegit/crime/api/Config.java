package me.seemslegit.crime.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config extends YamlConfiguration{

	private File cfg;
	
	/**
	 * 
	 * @param directory {@link String}
	 * @param filename {@link String}
	 */
	public Config(String directory, String filename) {
		super();
		cfg = new File(directory, filename);
		start();
	}
	
	/**
	 * 
	 * @param path {@link String}
	 */
	public Config(String path) {
		super();
		cfg = new File(path);
		start();
	}

	/**
	 * 
	 * @param f {@link File}
	 */
	public Config(File f) {
		super();
		if(f.isDirectory()) {
			cfg = new File(f, "error.config.yml");
		}else{
			cfg = f;
		}
		start();
	}
	
	private void start() {
		createFile();
		try {
			load(cfg);
		} catch (Exception e){
			return;
		}
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	private boolean createFile() {
		if(cfg.exists()) {
			if(cfg.isDirectory()) {
				cfg.delete();
			}else{
				return true;
			}
		}
		
		try {
			cfg.createNewFile();
		} catch (IOException e) {
			return false;
		}
		set("Config.Created", new Date().toString());
		return true;
	}
	
	@Override
	public void addDefault(String path, Object value) {
		super.addDefault(path, value);
	}
	
	/**
	 * 
	 * @return {@link File}
	 */
	public File getFile() {
		return cfg;
	}
	
	public void reload() {
		start();
	}
	
	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean save() {
		try {
			super.save(cfg);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<String> getStringList(String path) {
		List<String> l = super.getStringList(path);
		ArrayList<String> lol = new ArrayList<String>();
		for(String o : l) {
			lol.add(o);
		}
		return lol;
	}
	
	@Override
	public ArrayList<Object> getList(String path) {
		List<?> l = super.getList(path);
		ArrayList<Object> lol = new ArrayList<Object>();
		for(Object o : l) {
			lol.add(o);
		}
		return lol;
	}
	
	@Override
	public void set(String path, Object value) {
		super.set(path, value);
		super.set("Config.LastEdit", new Date().toString());
		save();
	}
	
	/**
	 * 
	 * @param keys {@link String}[]
	 * @return {@link Object}[]
	 */
	public Object[] getKeys(String... keys) {
		Object[] o = new Object[keys.length];
		int i = 0;
		for(String s : keys) {
			o[i] = get(s, null);
			i ++;
		}
		return o;
	}
	
	/**
	 * 
	 * @param keys {@link String}[]
	 * @return {@link Boolean}
	 */
	public boolean keysExists(String... keys) {
		for(String s : keys) {
			if(!contains(s)) return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param path {@link String}
	 * @return {@link Location}
	 */
	public Location getLocation(String path) {
		String[] keys = new String[] {
		/*0*/	path + ".world", 
		/*1*/	path + ".x", 
		/*2*/	path + ".y", 
		/*3*/	path + ".z", 
		/*4*/	path + ".view.pitch",
		/*5*/	path + ".view.yaw"
		};
		if(!keysExists(keys)) return null;
		Object[] o = getKeys(keys);
		String world = (String) o[0];
		double x = (Double) o[1];
		double y = (Double) o[2];
		double z = (Double) o[3];
		float pitch = Float.parseFloat(((Double) o[4]).toString());
		float yaw = Float.parseFloat(((Double) o[5]).toString());
		
		World w = Bukkit.getWorld(world);
		if(w == null) return null;
		
		Location loc = new Location(w, x, y, z);
		loc.setPitch(pitch);
		loc.setYaw(yaw);
		
		return loc;
	}
	
	/**
	 * 
	 * @param path {@link String}
	 * @param loc {@link Location}
	 */
	public void setLocation(String path, Location loc) {
		Validate.notNull(loc);
		set(path + ".world", loc.getWorld().getName());
		set(path + ".x", loc.getX());
		set(path + ".y", loc.getY());
		set(path + ".z", loc.getZ());
		set(path + ".view.pitch", loc.getPitch());
		set(path + ".view.yaw", loc.getYaw());
	}
	
}
