package demmonic.rwrapper;

import java.applet.Applet;
import java.io.IOException;
import java.util.jar.JarInputStream;

import demmonic.rwrapper.container.asm.ClassNodeLoader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.ui.AppletUI;
import demmonic.rwrapper.user.Server;
import demmonic.rwrapper.user.command.Command;
import demmonic.rwrapper.user.command.CommandHandler;
import demmonic.rwrapper.util.ASMUtil;
import demmonic.rwrapper.util.IOUtil;

/**
 * 
 * @author Demmonic
 *
 */
public final class Loader {

	/**
	 * The loaded client's class loader
	 */
	private static ClassNodeLoader loader;
	
	/**
	 * The loaded server
	 */
	private static Server loadedServer;
	
	/**
	 * @param server
	 * 			The server to load
	 */
	public Loader(Server server) {
		loadedServer = server;
		
		try {
			loader = IOUtil.parseJar(new JarInputStream(loadedServer.getClient()));
			loadedServer.set(loader);
			
			secure();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Starts the loader
	 */
	public void start() {
		for (Command c : loadedServer.getCommands()) {
			CommandHandler.add(c);
		}

		Applet applet = (Applet) loadedServer.getClientInstance();
		applet.setStub(loadedServer.getStub());
		
		AppletUI.getInstance().start(applet);
	}
	
	/**
	 * Secures the class loader
	 */
	private void secure() {
		ASMUtil.swapReferences(loader, "java/lang/System", "demmonic/rwrapper/asm/layer/SystemLayer");
		ASMUtil.swapReferences(loader, "java/net/NetworkInterface", "demmonic/rwrapper/asm/layer/NetworkInterfaceLayer");
		ASMUtil.swapReferences(loader, "java/lang/Runtime", "demmonic/rwrapper/asm/layer/RuntimeLayer");
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
