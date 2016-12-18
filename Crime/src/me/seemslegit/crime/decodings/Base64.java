package me.seemslegit.crime.decodings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import me.seemslegit.crime.exceptions.DecodingException;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class Base64 {

	/**
	 * 
	 * @param inventory {@link Inventory}
	 * @return {@link String}
	 */
	public static String toBase64(Inventory inventory) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			dataOutput.writeInt(inventory.getSize());
			for (int i = 0; i < inventory.getSize(); i++) {
				dataOutput.writeObject(inventory.getItem(i));
			}
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			throw new DecodingException();
		}
	}

	/**
	 * 
	 * @param data {@link String}
	 * @return {@link Inventory}
	 */
	public static Inventory fromBase64(String data) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, (ItemStack) dataInput.readObject());
			}
			dataInput.close();
			return inventory;
		} catch (Exception e) {
			throw new DecodingException();
		}
	}
	
	/**
	 * 
	 * @param o {@link Object}
	 * @return {@link String}
	 */
	public static String toBase64(Object o) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(
					outputStream);
			dataOutput.writeObject(o);
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			Main.instance.getErrorManager().registerError(e);
			throw new DecodingException();
		}
	}
	
	/**
	 * 
	 * @param data {@link String}
	 * @return {@link Object}[]
	 * @throws DecodingException
	 */
	public static Object[] ArrayFromBase64(String data)
			throws DecodingException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(
					inputStream);
			Object[] items = new Object[dataInput.readInt()];
			for (int i = 0; i < items.length; i++) {
				items[i] = (dataInput.readObject());
			}
			dataInput.close();
			return items;
		} catch (Exception e) {
			throw new DecodingException();
		}
	}
	
	/**
	 * 
	 * @param data {@link String}
	 * @return {@link Object}
	 */
	public static Object FromBase64(String data) {
		try{
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(
					inputStream);
			Object o = dataInput.readObject();
			dataInput.close();
			return o;
		}catch(Exception e){
			Main.instance.getErrorManager().registerError(e);
			throw new DecodingException();
		}
	}
	
	/**
	 * 
	 * @param items {@link Object}[]
	 * @return {@link String}
	 * @throws IllegalStateException
	 */
	public static String ArraytoBase64(Object[] items)
			throws DecodingException {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(
					outputStream);

			dataOutput.writeInt(items.length);
			for (int i = 0; i < items.length; i++) {
				dataOutput.writeObject(items[i]);
			}
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			Main.instance.getErrorManager().registerError(e);
			throw new DecodingException();
		}
	}
	
}
