package demmonic.container.reflect;

import java.lang.reflect.Field;

/**
 * 
 * @author Demmonic
 *
 */
public class ReflectionField {

	private Field field;
	private Class<?> type;
	private ReflectionClass typeCache;
	
	/**
	 * @param field
	 */
	public ReflectionField(Field field) {
		this.field = field;
		this.type = field.getType();
	}
	
	/**
	 * @return This field's name
	 */
	public String getName() {
		return field.getName();
	}
	
	/**
	 * @return This field's type
	 */
	public ReflectionClass getType() {
		if (typeCache == null) {
			typeCache = new ReflectionClass(type);
		}
		return typeCache;
	}
	
	/**
	 * @return This field's type name
	 */
	public String getTypeName() {
		return type.getName();
	}
	
	/**
	 * @param o
	 * @return The value in this field
	 */
	public Object getValue(Object instance) {
		if (!field.isAccessible())
			field.setAccessible(true);
		
		try {
			return field.get(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param instance
	 * @param newValue
	 */
	public void setValue(Object instance, Object newValue) {
		if (!field.isAccessible())
			field.setAccessible(true);
		
		try {
			field.set(instance, newValue);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
