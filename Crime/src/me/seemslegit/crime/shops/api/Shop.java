package me.seemslegit.crime.shops.api;

import java.io.File;
import java.util.UUID;

import me.seemslegit.crime.api.Config;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

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
	
	public void spawnEntity() {
		
		for(Player p : Bukkit.getOnlinePlayers()) spawnEntity(p);
		
	}
	
	public void spawnEntity(Player p) {
		if(getEntity() == null) createEntity();
		
		PacketPlayOutPlayerInfo spawn1 = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, getEntity());
		PacketPlayOutNamedEntitySpawn spawn2 = new PacketPlayOutNamedEntitySpawn(getEntity());
		byte b = (byte) ((getLocation().getYaw() * 256.0F) / 360.0F);
		PacketPlayOutEntityHeadRotation spawn3 = new PacketPlayOutEntityHeadRotation(getEntity(), b);
		
		final PacketPlayOutPlayerInfo removetablist = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, getEntity());
		
		final PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		
		con.sendPacket(spawn1);con.sendPacket(spawn2);con.sendPacket(spawn3);
		
		new Thread(new Runnable() {
			
			public void run() {
				try{
					Thread.sleep(2500);
					con.sendPacket(removetablist);
				}catch(Exception e) {}
			}
		}).start();
	}
	
	public void createEntity() {
		
		Location loc = getLocation();
		
		WorldServer w = ((CraftWorld)loc.getWorld()).getHandle();
		MinecraftServer ms = ((CraftServer)Bukkit.getServer()).getServer();
		
		String name = getName();
		
		name = name.replace(" ", "_");
		
		name = ChatColor.stripColor(name);
		
		if(name.length() > 16) {
			name = name.substring(0, 16);
		}
		
		UUID u = UUID.fromString("6699f273-3482-4cf7-aa79-cee007bc6a0b");
		
		GameProfile gp = new GameProfile(u, name);
		
		EntityPlayer ep = new CustomPlayer(
				ms, 
				w, 
				gp, 
				new PlayerInteractManager(w));
		
		ep.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		
		this.e = ep;
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
