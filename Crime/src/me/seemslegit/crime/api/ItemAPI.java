package me.seemslegit.crime.api;

import java.util.ArrayList;

import me.seemslegit.crime.decodings.Base64;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class ItemAPI {
	
	private Material mat = Material.AIR;
	private short data = 0;
	private int amout = 1;
	private String display;
	private String[] lore;
	private String skullowner;
	private Color leathercolor;
	
	public ItemAPI() {
		
	}
	
	/**
	 * 
	 * @param c {@link Color}
	 * @return {@link ItemAPI}
	 */
	public ItemAPI color(Color c) {
		this.leathercolor = c;
		return this;
	}
	
	/**
	 * 
	 * @param lore {@link String}[]
	 * @return {@link ItemAPI}
	 */
	public ItemAPI lore(String... lore) {
		this.lore = lore;
		return this;
	}
	
	/**
	 * 
	 * @param owner {@link String}
	 * @return {@link ItemAPI}
	 */
	public ItemAPI skullOwner(String owner) {
		this.skullowner = owner;
		return this;
	}
	
	/**
	 * 
	 * @param display {@link String}
	 * @return {@link ItemAPI}
	 */
	public ItemAPI displayName(String display) {
		this.display = display;
		return this;
	}
	
	/**
	 * 
	 * @param s {@link Short}
	 * @return {@link ItemAPI}
	 */
	public ItemAPI data(short s) {
		this.data = s;
		return this;
	}
	
	/**
	 * 
	 * @param m {@link Material}
	 * @return {@link ItemAPI}
	 */
	public ItemAPI material(Material m) {
		this.mat = m;
		return this;
	}
	
	/**
	 * 
	 * @param a {@link Integer}
	 * @return {@link ItemAPI}
	 */
	public ItemAPI amout(int a) {
		this.amout = a;
		return this;
	}
	
	/**
	 * 
	 * @return {@link ItemStack}
	 */
	public ItemStack build() {
		ItemStack i = new ItemStack(mat, amout, data);
		ItemMeta im = i.getItemMeta();
		if(display != null) im.setDisplayName(display);
		if(lore != null) {
			ArrayList<String> lore = new ArrayList<String>();
			for(String s : this.lore) lore.add(s);
			im.setLore(lore);
		}
		if(skullowner != null) {
			SkullMeta sm = (SkullMeta) im;
			sm.setOwner(skullowner);
		}
		if(leathercolor != null) {
			LeatherArmorMeta lrm = (LeatherArmorMeta) im;
			lrm.setColor(leathercolor);
		}
		
		i.setItemMeta(im);
		return i;
	}

	@Override
	public String toString() {
		return Base64.toBase64(build());
	}
	
}
