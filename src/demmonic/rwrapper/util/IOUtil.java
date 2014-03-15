package demmonic.rwrapper.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import demmonic.rwrapper.ClassNodeLoader;

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
	public static ClassNodeLoader parseJar(File file) {
		ClassNodeLoader loader = new ClassNodeLoader();
		
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
					ClassReader cr = new ClassReader(byteStore.toByteArray());
					ClassNode cn = new ClassNode();
					cr.accept(cn, 0);
					loader.addClass(cn);
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
	public static ClassNodeLoader parseJar(JarInputStream in) {
		ClassNodeLoader loader = new ClassNodeLoader();
		
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
					ClassReader cr = new ClassReader(byteStore.toByteArray());
					ClassNode cn = new ClassNode();
					cr.accept(cn, 0);
					loader.addClass(cn);
				} else {
					loader.addResource(e.getName(), byteStore.toByteArray());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loader;
	}
	
	/**
	 * @param loader
	 * 			The class loader to save
	 * @param path
	 * 			The path to save the jar at
	 */
	public static void save(ClassNodeLoader loader, String path) {
		try {
			JarOutputStream out = new JarOutputStream(new FileOutputStream(path));
			for (ClassNode cn : loader.getAll()) {
				ClassWriter cw = new ClassWriter(0);
				cn.accept(cw);
				
				out.putNextEntry(new ZipEntry(cn.name + ".class"));
				out.write(cw.toByteArray());
				out.closeEntry();
			}
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
