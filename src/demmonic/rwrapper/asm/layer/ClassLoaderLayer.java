package demmonic.rwrapper.asm.layer;

import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import demmonic.rwrapper.util.ASMUtil;

/**
 * 
 * @author Demmonic
 *
 * We can't let any newly defined classes escape our iron grip >:)
 */
public class ClassLoaderLayer extends ClassLoader {

	/**
	 * @param b
	 * 			The class to secure
	 * @return Secured class
	 */
	private byte[] secure(byte[] b) {
		ClassReader cr = new ClassReader(b);
		ClassNode cn = new ClassNode();
		cr.accept(cn, 0);
		
		ASMUtil.secure(cn);
		
		ClassWriter cw = new ClassWriter(0);
		cn.accept(cw);
		
		return cw.toByteArray();
	}
	
	//DUPLICATE METHODS FOR MIMICING THE SYSTEM CLASS IN THE RT LIBRARY
	protected Class<?> ourDefineClass(String name, byte[] b, int off, int len) {
		b = secure(b);
		
		return super.defineClass(name, b, off, b.length);
	}
	
	protected Class<?> ourDefineClass(String name, byte[] b, int off, int len, ProtectionDomain protectionDomain) {
		b = secure(b);
		
		return super.defineClass(name, b, off, b.length, protectionDomain);
	}
	
}
