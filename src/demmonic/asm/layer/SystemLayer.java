package demmonic.asm.layer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Used to provide a layer of communication between the RT System class, and the client
 * @author Demmonic
 *
 */
public class SystemLayer {

	public static PrintStream out;
	
	static {
		out = new PrintStream(new ByteArrayOutputStream());
	}
	
	/**
	 * @param name
	 * 			The name of the property
	 * @return Nothing, unless it's looking for a place to store the cache
	 */
	public static String getProperty(String name) {
		if (name.equals("user.home")) {
			return System.getProperty(name);
		} else if (name.equals("file.separator")) {
			return System.getProperty(name);
		}
		
		return "";
	}
	
	/**
	 * @param name
	 * 			The name of the environment variable
	 * @return Nothing, there's never a legitimate reason a RSPS would need to use this
	 */
	public static String getenv(String name) {
		return "";
	}
	
	/**
	 * @return Current system time in milliseconds
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Requests for the garbage collector to run it's cycle
	 */
	public static void gc() {
		System.gc();
	}
	
	/**
	 * Copies a section of an array from one array to another
	 * 
	 * @param src
	 * @param srcOff
	 * @param dest
	 * @param destOff
	 * @param length
	 */
	public static void arraycopy(Object src, int srcOff, Object dest, int destOff, int length) {
		System.arraycopy(src, srcOff, dest, destOff, length);
	}
	
}
