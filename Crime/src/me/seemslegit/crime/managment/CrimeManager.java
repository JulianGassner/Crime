package me.seemslegit.crime.managment;

import me.seemslegit.crime.playerapi.UserBase;

public class CrimeManager {

	public static final long MAX_CRIME = 1*60*60*24;
	
	private void init() {
		
	}
	
	public CrimeManager() {
		init();
	}

	/**
	 * 
	 * @param u {@link UserBase}
	 * @param crime {@link Long}
	 */
	public void setCrime(UserBase u, long crime) {
		if(crime < 0) crime = 0;
		if(crime > MAX_CRIME) crime = MAX_CRIME;
		u.getStats().set("crime", System.currentTimeMillis() + (crime * 1000));
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Long}
	 */
	public long getCrime(UserBase u) {
		long l =  u.getStats().getLong("crime", 0);
		if(l != 0) {
			l -= System.currentTimeMillis();
			l /= 1000;
			if(l < 0) l = 0;
			if(l > MAX_CRIME) l = MAX_CRIME;
		}
		return l;
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Boolean}
	 */
	public boolean hasCrime(UserBase u) {
		return getCrime(u) > 0;
	}
	
}
