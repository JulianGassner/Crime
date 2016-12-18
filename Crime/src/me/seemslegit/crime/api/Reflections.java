package me.seemslegit.crime.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import me.seemslegit.crime.plugin.Main;

public class Reflections {

	/**
	 * 
	 * @param clazz
	 * @param args
	 * @param initargs
	 * @return
	 * @throws Exception
	 */
	public static Object invokeConstructor(Class<?> clazz, Class<?>[] args,
			Object... initargs) throws Exception {
		return getConstructor(clazz, args).newInstance(initargs);
	}
	
	/**
	 * 
	 * @param clazz
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Constructor<?> getConstructor(Class<?> clazz,
			Class<?>... args) throws Exception {
		Constructor<?> c = clazz.getConstructor(args);
		c.setAccessible(true);
		return c;
	}
	
	/**
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 */
	private static Field getField(Class<?> clazz, String fieldName)
	        throws NoSuchFieldException {
	    try {
	      return clazz.getDeclaredField(fieldName);
	    } catch (NoSuchFieldException e) {
	      Class<?> superClass = clazz.getSuperclass();
	      if (superClass == null) {
	        throw e;
	      } else {
	        return getField(superClass, fieldName);
	      }
	    }
	}
	
	/**
	 * 
	 * @param o
	 * @param field
	 * @return
	 */
	public static Object getValue(Object o, String field) {
		return getValue(o, field, null);
	}
	
	/**
	 * 
	 * @param o
	 * @param field
	 * @param def
	 * @return
	 */
	public static Object getValue(Object o, String field, Object def) {
		try{
			Field f = getField(o.getClass(), field);
			f.setAccessible(true);
			return f.get(o);
		}catch(Exception e) {
			Main.instance.getErrorManager().registerError(e);
		}
		return def;
	}
	
	/**
	 * 
	 * @param o
	 * @param field
	 * @param value
	 */
	public static void setValue(Object o, String field, Object value) {
		try {
			Field f = getField(o.getClass(), field);
			f.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
		    modifiersField.setAccessible(true);
		    modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			f.set(o, value);
		} catch (Exception e1) {
			Main.instance.getErrorManager().registerError(e1);
		}
	}
	
}
