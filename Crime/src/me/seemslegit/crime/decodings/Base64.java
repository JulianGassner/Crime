package me.seemslegit.crime.decodings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import me.seemslegit.crime.exceptions.DecodingException;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class Base64 {

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
		}4
	}
	
	/**
	 * 
	 * @param items {@link Object}[]
	 * @return {@link String}
	 * @throws IllegalStateException
	 */
	public static String ArraytoBase64(Object[] items)
			throws IllegalStateException {
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
