package me.seemslegit.crime.jail;

import me.seemslegit.crime.cop.CopManager;
import me.seemslegit.crime.managment.JailManager;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Jail {

	
	
	/**
	 * 
	 * @param u {@link UserBase}
	 */
	public void initPlayer(UserBase u) {
		
		if(u.hasCrime()) {
			long crime = u.getCrime();
			
			long jt = crime;
			
			u.setJailTime(u.getJailTime() + jt);
			u.resetCrime();
		}
		
		CopManager cm = Main.instance.getCopManager();
		
		cm.rawCuff(u, true);
		
		Main.instance.getCopManager().removeIllegalItems(u);
		
		Player p = u.getPlayer();
		
		if(p == null) return;
		
		if(!p.hasPotionEffect(PotionEffectType.SLOW)) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*100000, 2));
		}
		
		Location loc = getLocation();
		if(loc != null) p.teleport(loc);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private JailManager jm;
	
	/**
	 * 
	 * @param jm {@link JailManager}
	 */
	public Jail(JailManager jm) {
		this.jm = jm;
	}
	
	/**
	 * 
	 * @return {@link JailManager}
	 */
	public JailManager getJailManager() {
		return jm;
	}
	
	/**
	 * 
	 * @return {@link Location}
	 */
	public Location getLocation() {
		return jm.getLocation();
	}
}
