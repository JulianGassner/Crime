package me.seemslegit.crime.managment;

import me.seemslegit.crime.playerapi.UserBase;

public class MoneyManager {

	public static final float MAX_COINS = 1 * 1000000000; 
	
	public static final float START_COINS = 100;
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @return {@link Float}
	 */
	public float getCoins(UserBase u) {
		return Float.parseFloat(u.getStats().getString("coins", START_COINS + ""));
	}
	
	/**
	 * 
	 * @param u {@link UserBase}
	 * @param coins {@link Float}
	 */
	public void setCoins(UserBase u, float coins) {
		if(coins < 0) coins = 0;
		if(coins > MAX_COINS) coins = MAX_COINS;
		u.getStats().set("coins", coins);
	}
	
}
