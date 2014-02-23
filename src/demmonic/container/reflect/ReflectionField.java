package demmonic.container.reflect;

import java.lang.reflect.Field;

/**
 * 
 * @author Demmonic
 *
 */
public class ReflectionField {

	private ReflectionClass owner;
	private Field field;
	private Class<?> type;
	private ReflectionClass typeCache;
	
	/**
	 * @param owner
	 * 			This field's owner
	 * @param field
	 * 			The field to store
	 */
	public ReflectionField(ReflectionClass owner, Field field) {
		this.owner = owner;
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
			typeCache = new ReflectionClass(type, getValue());
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
	 * @return This field's value
	 */
	public Object getValue() {
		if (!field.isAccessible())
			field.setAccessible(true);
		
		try {
			return field.get(owner.getInstance());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param newValue
	 * 			The value to store in this field
	 */
	public void setValue(Object newValue) {
		if (!field.isAccessible())
			field.setAccessible(true);
		
		try {
			field.set(owner.getInstance(), newValue);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
