package demmonic.rwrapper.user;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import demmonic.rwrapper.container.asm.ClassNodeLoader;

/**
 * 
 * @author Demmonic
 *
 */
public class ClientLoaderManager {

	private static ClassNodeLoader loader = new ClassNodeLoader();
	private static Set<ClientLoader> loaders = new HashSet<ClientLoader>();
	
	static {
		load(new File("./loaders/"));
		
		for (Class<?> c : loader.getAllClasses()) {
			if (c.getSuperclass().equals(ClientLoader.class)) {
				try {
					loaders.add((ClientLoader) c.newInstance());
				} catch (Exception e) {
					System.out.println("error instancing loader " + c.getName());
				}
			}
		}
	}
	
	/**
	 * @return The names of all loaded classes
	 */
	public static Set<String> getLoadedNames() {
		Set<String> names = new HashSet<String>();
		for (ClientLoader l : loaders) {
			names.add(l.getClass().getName());
		}
		return names;
	}
	
	/**
	 * @param name
	 * 			The class name to search for
	 * @return The client loader with the provided class name
	 */
	public static ClientLoader forName(String name) {
		for (ClientLoader l : loaders) {
			if (l.getClass().getName().equals(name)) {
				return l;
			}
		}
		
		return null;
	}
	
	/**
	 * @param folder
	 */
	private static void load(File folder) {
		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				load(f);
			} else {
				loader.add(f);
			}
		}
	}
	
}
