package demmonic.rwrapper.user;

import java.applet.AppletStub;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import demmonic.rwrapper.container.asm.ClassNodeLoader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public abstract class Server {

	private ClassNodeLoader loader;
	private HashMap<String, ReflectionClass> classes = new HashMap<String, ReflectionClass>();
	
	private Object clientInstance;
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	/**
	 * @param loader
	 * 			This server's new class loader
	 */
	public final void set(ClassNodeLoader loader) {
		this.loader = loader;
	}
	
	/**
	 * @param c
	 * 			The command to add
	 */
	public final void add(Command c) {
		commands.add(c);
	}
	
	/**
	 * @param name
	 * 			The class to search for
	 * @return The class matching the provided name
	 */
	public final ReflectionClass get(String name) {
		if (classes.get(name) == null) {
			try {
				ReflectionClass c = new ReflectionClass(loader.loadClass(name), isLoaded() && getMainClass().getName().equals(name) ? getClientInstance() : null);
				classes.put(c.getName(), c);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return classes.get(name);
	}
	
	/**
	 * @return All of this server's commands
	 */
	public final Collection<Command> getCommands() {
		return commands;
	}
	
	/**
	 * @return If this server has been loaded
	 */
	public final boolean isLoaded() {
		return clientInstance != null;
	}
	
	/**
	 * @return This server's client instance
	 */
	public final Object getClientInstance() {
		if (clientInstance == null) {
			clientInstance = getMainClass().getInstance();
		}
		
		return clientInstance;
	}
	
	/**
	 * @return This server's client in an input stream
	 */
	public abstract InputStream getClient();
	
	/**
	 * @return This server's main class
	 */
	public abstract ReflectionClass getMainClass();
	
	/**
	 * @return This server's applet stub
	 */
	public AppletStub getStub() {
		return null;
	}
	
}
