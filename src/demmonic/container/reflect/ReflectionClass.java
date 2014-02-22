package demmonic.container.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Demmonic
 *
 */
public class ReflectionClass {

	private Class<?> clazz;
	private Object cached;
	
	private Set<ReflectionField> fields = new HashSet<ReflectionField>();
	private Set<ReflectionMethod> methods = new HashSet<ReflectionMethod>();
	
	/**
	 * @param c
	 */
	public ReflectionClass(Class<?> c) {
		this.clazz = c;
		
		for (Method m : c.getDeclaredMethods()) {
			addMethod(new ReflectionMethod(m));
		}
		
		for (Field f : c.getDeclaredFields()) {
			addField(new ReflectionField(f));
		}
	}
	
	/**
	 * @return This class's super class
	 */
	public ReflectionClass getSuper() {
		return new ReflectionClass(clazz.getSuperclass());
	}
	
	/**
	 * @return This class's name
	 */
	public String getName() {
		return clazz.getName();
	}
	
	/**
	 * @return An instance of this class
	 */
	public Object getInstance() {
		try {
			return cached == null ? (cached = clazz.newInstance()) : cached;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param f
	 */
	public void addField(ReflectionField f) {
		fields.add(f);
	}
	
	/**
	 * @param name
	 * @return The field with the provided name
	 */
	public ReflectionField getField(String name, String type) {
		for (ReflectionField f : fields) {
			if (f.getName().equals(name)) {
				if (f.getTypeName().equals(type)) {
					return f;
				} else {
					System.out.println(f.getTypeName());
				}
			}
		}
		
		return null;
	}
	
	/**
	 * @return All reflection fields beloning to this class
	 */
	public Collection<ReflectionField> getFields() {
		return fields;
	}
	
	/**
	 * @param m
	 */
	public void addMethod(ReflectionMethod m) {
		methods.add(m);
	}
	
	/**
	 * @param name
	 * @return The method with the provided name
	 */
	public ReflectionMethod getMethod(String name, Object... parameters) {
		BASE : for (ReflectionMethod m : methods) {
			if (m.getName().equals(name)) {
				if (parameters.length != m.getParameters().length) {
					continue;
				} else {
					for (int i = 0; i < parameters.length; i++) {
						if (!parameters[i].equals(m.getParameters()[i])) {
							continue BASE;
						}
					}
				}
				return m;
			}
		}
		
		return null;
	}
}
