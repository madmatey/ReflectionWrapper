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
	
	private HashMap<String, byte[]> classNodes = new HashMap<String, byte[]>();
	private HashMap<String, byte[]> resources = new HashMap<String, byte[]>();
	
	/**
	 * Adds the provided class node to the class loader
	 * @param name
	 * 			The class name
	 * @param contents
	 * 			The contents of the class (or data)
	 */
	public void addClass(String name, byte[] contents) {
		classNodes.put(name, contents);
	}
	
	/**
	 * Adds the provided resource to the class loader
	 * @param resourceName
	 * 			The name of the resource
	 * @param contents
	 * 			The contents of the resource (or data)
	 */
	public void addResource(String resourceName, byte[] contents) {
		resources.put(resourceName, contents);
	}
	
	/**
	 * @param name
	 * 			The name of the class
	 * @return If this class loader contains the provided class node
	 */
	public boolean contains(String name) {
		return (classNodes.get(name) != null);
	}
	
	/**
	 * @return All class nodes in this loader
	 */
	public Collection<byte[]> getAll() {
		return classNodes.values();
	}
	
	/**
	 * Clears out all class nodes
	 */
	public void clear() {
		classNodes.clear();
	}
	
	/**
	 * @return All classes in this loader
	 */
	public Collection<Class<?>> getAllClasses() {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (String s : classNodes.keySet()) {
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
		return classNodes.get(name);
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
	public Class<?> findClass(String className) throws ClassNotFoundException {
		if (classNodes.containsKey(className.replace(".", "/"))) {
			return defineClass(className, classNodes.get(className), 0, classNodes.get(className).length, getDomain());
		} else {
			return super.loadClass(className);
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
