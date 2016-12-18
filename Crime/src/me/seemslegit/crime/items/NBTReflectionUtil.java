package me.seemslegit.crime.items;

import java.lang.reflect.Method;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

public class NBTReflectionUtil {
	
	/**
	 * 
	 * @return {@link Class}
	 */
	private static Class<?> getCraftItemStack() {
		String version = org.bukkit.Bukkit.getServer().getClass().getPackage()
				.getName().replace(".", ",").split(",")[3];
		try {
			return Class.forName("org.bukkit.craftbukkit." + version
					+ ".inventory.CraftItemStack");
		} catch (Exception ex) {
			System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return {@link Object}
	 */
	private static Object getNewNBTTag() {
		String version = org.bukkit.Bukkit.getServer().getClass().getPackage()
				.getName().replace(".", ",").split(",")[3];
		try {
			Class<?> c = Class.forName("net.minecraft.server." + version
					+ ".NBTTagCompound");
			return c.newInstance();
		} catch (Exception ex) {
			System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param NBTTag {@link Object}
	 * @param NMSItem {@link Object}
	 * @return {@link Object}
	 */
	private static Object setNBTTag(Object NBTTag, Object NMSItem) {
		try {
			Method method = NMSItem.getClass().getMethod("setTag",
					new Class[] { NBTTag.getClass() });
			method.invoke(NMSItem, new Object[] { NBTTag });
			return NMSItem;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @return {@link Object}
	 */
	private static Object getNMSItemStack(ItemStack item) {
		Class<?> cis = getCraftItemStack();
		try {
			Method method = cis.getMethod("asNMSCopy",
					new Class[] { ItemStack.class });
			return method.invoke(cis, new Object[] { item });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link Object}
	 * @return {@link ItemStack}
	 */
	private static ItemStack getBukkitItemStack(Object item) {
		Class<?> cis = getCraftItemStack();
		try {
			Method method = cis.getMethod("asCraftMirror",
					new Class[] { item.getClass() });
			Object answer = method.invoke(cis, new Object[] { item });
			return (ItemStack) answer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param nmsitem {@link Object}
	 * @return {@link Object}
	 */
	private static Object getNBTTagCompound(Object nmsitem) {
		Class<?> c = nmsitem.getClass();
		try {
			Method method = c.getMethod("getTag", new Class[0]);
			return method.invoke(nmsitem, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @param text {@link String}
	 * @return {@link ItemStack}
	 */
	public static ItemStack setString(ItemStack item, String key, String text) {
		if (text == null) {
			return remove(item, key);
		}
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			nbttag = getNewNBTTag();
		}
		try {
			Method method = nbttag.getClass().getMethod("setString",
					new Class[] { String.class, String.class });
			method.invoke(nbttag, new Object[] { key, text });
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @return {@link String}
	 */
	public static String getString(ItemStack item, String key) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			return null;
		}
		try {
			Method method = nbttag.getClass().getMethod("getString",
					new Class[] { String.class });
			return (String) method.invoke(nbttag, new Object[] { key });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @param i {@link Integer}
	 * @return {@link ItemStack}
	 */
	public static ItemStack setInt(ItemStack item, String key, Integer i) {
		if (i == null) {
			return remove(item, key);
		}
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			nbttag = getNewNBTTag();
		}
		try {
			Method method = nbttag.getClass().getMethod("setInt",
					new Class[] { String.class, Integer.TYPE });
			method.invoke(nbttag, new Object[] { key, i });
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @return {@link Integer}
	 */
	public static Integer getInt(ItemStack item, String key) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			return null;
		}
		try {
			Method method = nbttag.getClass().getMethod("getInt",
					new Class[] { String.class });
			return (Integer) method.invoke(nbttag, new Object[] { key });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @param d {@link Double}
	 * @return {@link ItemStack}
	 */
	public static ItemStack setDouble(ItemStack item, String key, Double d) {
		if (d == null) {
			return remove(item, key);
		}
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			nbttag = getNewNBTTag();
		}
		try {
			Method method = nbttag.getClass().getMethod("setDouble",
					new Class[] { String.class, Double.TYPE });
			method.invoke(nbttag, new Object[] { key, d });
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @return {@link Double}
	 */
	public static Double getDouble(ItemStack item, String key) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			return null;
		}
		try {
			Method method = nbttag.getClass().getMethod("getDouble",
					new Class[] { String.class });
			return (Double) method.invoke(nbttag, new Object[] { key });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @param d {@link Boolean}
	 * @return {@link ItemStack}
	 */
	public static ItemStack setBoolean(ItemStack item, String key, Boolean d) {
		if (d == null) {
			return remove(item, key);
		}
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			nbttag = getNewNBTTag();
		}
		try {
			Method method = nbttag.getClass().getMethod("setBoolean",
					new Class[] { String.class, Boolean.TYPE });
			method.invoke(nbttag, new Object[] { key, d });
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @return {@link Boolean}
	 */
	public static Boolean getBoolean(ItemStack item, String key) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			return Boolean.valueOf(false);
		}
		try {
			Method method = nbttag.getClass().getMethod("getBoolean",
					new Class[] { String.class });
			return (Boolean) method.invoke(nbttag, new Object[] { key });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @return {@link ItemStack}
	 */
	public static ItemStack remove(ItemStack item, String key) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			nbttag = getNewNBTTag();
		}
		try {
			Method method = nbttag.getClass().getMethod("remove",
					new Class[] { String.class });
			method.invoke(nbttag, new Object[] { key });
			nmsitem = setNBTTag(nbttag, nmsitem);
			return getBukkitItemStack(nmsitem);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @param key {@link String}
	 * @return {@link Boolean}
	 */
	public static Boolean hasKey(ItemStack item, String key) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			return Boolean.valueOf(false);
		}
		try {
			Method method = nbttag.getClass().getMethod("hasKey",
					new Class[] { String.class });
			return (Boolean) method.invoke(nbttag, new Object[] { key });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param item {@link ItemStack}
	 * @return {@link Set}
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> getKeys(ItemStack item) {
		Object nmsitem = getNMSItemStack(item);
		if (nmsitem == null) {
			System.out.println("Got null! (Outdated Plugin?)");
			return null;
		}
		Object nbttag = getNBTTagCompound(nmsitem);
		if (nbttag == null) {
			nbttag = getNewNBTTag();
		}
		try {
			Method method = nbttag.getClass().getMethod("c", new Class[0]);
			return (Set<String>) method.invoke(nbttag, new Object[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
