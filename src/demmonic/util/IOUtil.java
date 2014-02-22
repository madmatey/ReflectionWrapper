package demmonic.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

import demmonic.ByteClassLoader;

/**
 * In/out utilities
 * @author Demmonic
 *
 */
public class IOUtil {

	/**
	 * @param file
	 * 			The JAR file to parse
	 * @return parsed classes in a byte class loader
	 */
	public static ByteClassLoader parseJar(File file) {
		ByteClassLoader loader = new ByteClassLoader();
		
		try {
			JarFile jf = new JarFile(file);
			Enumeration<JarEntry> entries = jf.entries();
			while (entries.hasMoreElements()) {
				JarEntry je = entries.nextElement();
				InputStream in = jf.getInputStream(je);
				
				byte[] buffer = new byte[(int)je.getSize()];
				int length;
				ByteArrayOutputStream byteStore = new ByteArrayOutputStream();
				while ((length = in.read(buffer)) != -1) {
					byteStore.write(buffer, 0, length);
				}
				
				if (je.getName().endsWith(".class")) {
					loader.addClass(je.getName().replace(".class", ""), byteStore.toByteArray());
				} else {
					loader.addResource(je.getName(), byteStore.toByteArray());
				}
			}
			jf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loader;
	}
	
	/**
	 * @param in
	 * 			The input stream to parse
	 * @return Class loader parsed from provided jar
	 */
	public static ByteClassLoader parseJar(JarInputStream in) {
		ByteClassLoader loader = new ByteClassLoader();
		
		try {
			ZipEntry e;
			while ((e = in.getNextEntry()) != null) {
				ByteArrayOutputStream byteStore = new ByteArrayOutputStream();

				byte buffer[] = new byte[4096];
				int length;
				while ((length = in.read(buffer)) != -1) {
					byteStore.write(buffer, 0, length);
				}
				
				if (e.getName().endsWith(".class")) {
					loader.addClass(e.getName().replace(".class", "").replace("/", "."), byteStore.toByteArray());
				} else {
					loader.addResource(e.getName(), byteStore.toByteArray());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loader;
	}
	
}
