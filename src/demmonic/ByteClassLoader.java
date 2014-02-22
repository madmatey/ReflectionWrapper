package demmonic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * 
 * @author Demmonic
 *
 */
public class ByteClassLoader extends ClassLoader {
	
	private HashMap<String, byte[]> classes = new HashMap<String, byte[]>();
	private HashMap<String, byte[]> resources = new HashMap<String, byte[]>();
	
	/**
	 * Adds the provided class node to the class loader
	 * @param name
	 * 			The class name
	 * @param contents
	 * 			The contents of the class (or data)
	 */
	public void addClass(String name, byte[] contents) {
		classes.put(name, contents);
	}
	
	/**
	 * Adds the provided resource to the class loader
	 * @param bame
	 * 			The name of the resource
	 * @param contents
	 * 			The contents of the resource (or data)
	 */
	public void addResource(String bame, byte[] contents) {
		resources.put(bame, contents);
	}
	
	/**
	 * @param name
	 * 			The name of the class
	 * @return If this class loader contains the provided class node
	 */
	public boolean contains(String name) {
		return (classes.get(name) != null);
	}
	
	/**
	 * @return All class nodes in this loader
	 */
	public Collection<byte[]> getAll() {
		return classes.values();
	}
	
	/**
	 * Clears out all class nodes
	 */
	public void clear() {
		classes.clear();
	}
	
	/**
	 * @return All classes in this loader
	 */
	public Collection<Class<?>> getAllClasses() {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (String s : this.classes.keySet()) {
			try {
				classes.add(loadClass(s));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return classes;
	}
	
	/**
	 * @param name
	 * 			The name of the class
	 * @return The class node with the provided name
	 */
	public byte[] get(String name) {
		return classes.get(name);
	}
	
	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		return findClass(className);
	}
	
	@Override
	public InputStream getResourceAsStream(String name) {
		if (resources.get(name) == null)
			return null;
		
		return new ByteArrayInputStream(resources.get(name));
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		if (classes.containsKey(name)) {
			return defineClass(name, classes.get(name), 0, classes.get(name).length, getDomain());
		} else {
			return super.loadClass(name);
		}
	}
	
	private ProtectionDomain getDomain() {
		CodeSource code = new CodeSource(null, (Certificate[]) null);
		return new ProtectionDomain(code, getPermissions());
	}

	private Permissions getPermissions() {
		Permissions permissions = new Permissions();
		permissions.add(new AllPermission());
		return permissions;
	}
	
}
