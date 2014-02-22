package demmonic;

import java.applet.Applet;
import java.io.IOException;
import java.util.jar.JarInputStream;

import demmonic.container.reflect.ReflectionClass;
import demmonic.ui.AppletUI;
import demmonic.ui.CommandUI;
import demmonic.user.Server;
import demmonic.util.IOUtil;

/**
 * 
 * @author Demmonic
 *
 */
public class Loader {

	/**
	 * The loaded client's class loader
	 */
	private static ByteClassLoader loader;
	
	/**
	 * The loaded server
	 */
	private static Server loadedServer;
	
	/**
	 * @param server
	 * 			The server to load
	 */
	public Loader(Server server) {
		CommandUI.initialize();
		loadedServer = server;
		
		try {
			loader = IOUtil.parseJar(new JarInputStream(loadedServer.getClient()));
			loadedServer.set(loader);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * @param mainClass
	 */
	public void start() {
		for (Command c : loadedServer.getCommands()) {
			CommandHandler.add(c);
		}

		Applet applet = (Applet) loadedServer.getClientInstance();
		applet.setStub(loadedServer.getStub());
		
		AppletUI.start(applet);
	}
	
	/**
	 * @param name
	 * 			The name of the class
	 * @return The class matching the provided name
	 */
	public static ReflectionClass getClass(String name) {
		return loadedServer.get(name);
	}
	
	/**
	 * @return The global client instance
	 */
	public static Object getClientInstance() {
		return loadedServer.getClientInstance();
	}
	
}
