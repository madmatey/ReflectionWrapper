package demmonic.rwrapper;

import java.applet.Applet;
import java.io.IOException;
import java.util.jar.JarInputStream;

import demmonic.rwrapper.container.asm.ClassNodeLoader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.ui.applet.AppletUI;
import demmonic.rwrapper.user.ClientLoader;
import demmonic.rwrapper.user.command.Command;
import demmonic.rwrapper.user.command.CommandHandler;
import demmonic.rwrapper.util.ASMUtil;

/**
 * 
 * @author Demmonic
 *
 */
public final class Loader {

	/**
	 * The loaded client's class loader
	 */
	private static ClassNodeLoader loader = new ClassNodeLoader();
	
	/**
	 * The loaded server
	 */
	private static ClientLoader loaded;
	
	/**
	 * @param server
	 * 			The server to load
	 */
	public Loader(ClientLoader server) {
		loaded = server;
		
		try {
			loader.add(new JarInputStream(loaded.getClient()));
			
			loaded.set(loader);
			ASMUtil.secure(loader);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Starts the loader
	 */
	public void start() {
		for (Command c : loaded.getCommands()) {
			CommandHandler.add(c);
		}

		Applet applet = (Applet) loaded.getClientInstance();
		applet.setStub(loaded.getStub());
		
		AppletUI.getInstance().start(applet, loaded.getSize());
	}
	
	/**
	 * @param name
	 * 			The name of the class
	 * @return The class matching the provided name
	 */
	public static ReflectionClass getClass(String name) {
		return loaded.get(name);
	}
	
	/**
	 * @return The global client instance
	 */
	public static Object getClientInstance() {
		return loaded.getClientInstance();
	}
	
}
