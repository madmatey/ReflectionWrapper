package demmonic.rwrapper.asm.layer;

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
		/**
		 * fuck memory leaks and fuck da police
		 */
		out = new PrintStream(new ByteArrayOutputStream());
	}

	public static String getProperty(String name) {
		if (name.equals("user.home")) {
			return System.getProperty(name);
		} else if (name.equals("file.separator")) {
			return System.getProperty(name);
		}
		
		return "";
	}
	
	public static String getenv(String name) {
		return "";
	}

	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	public static void gc() {
		System.gc();
	}

	public static void arraycopy(Object src, int srcOff, Object dest, int destOff, int length) {
		System.arraycopy(src, srcOff, dest, destOff, length);
	}
	
}
