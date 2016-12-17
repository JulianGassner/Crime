package me.seemslegit.crime.api;

import java.util.Random;



public class Code {

	private String code = "";
	private String raw = "";
	private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private Random rnd = new Random();

	/**
	 * 
	 * @param length
	 *            {@link Integer}
	 */
	public Code(int length) {
		code = randomString(length);
	}

	/**
	 * 
	 * @return {@link String}
	 */
	public String getRaw() {
		return raw;
	}

	/**
	 * 
	 * @param length
	 *            {@link Integer}
	 * @param prefix
	 *            {@link String}
	 */
	public Code(int length, String prefix) {
		raw = randomString(length);
		code = prefix + raw;
	}

	/**
	 * 
	 * @param len
	 *            {@link Integer}
	 * @return {@link String}
	 */
	private String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	/**
	 * 
	 * @return {@link String}
	 */
	public String getCode() {
		return code;
	}
	


}
