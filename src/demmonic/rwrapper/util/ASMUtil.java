package demmonic.rwrapper.util;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

import demmonic.rwrapper.container.asm.ClassNodeLoader;

/**
 * A set of objectweb ASM utilities
 * @author Demmonic
 *
 */
public final class ASMUtil {

	/**
	 * @param c
	 * 			The class node to modify references in
	 * @param from
	 * 			The reference owner to change
	 * @param to
	 * 			What to change the reference owner to
	 */
	public static void swapSuperReferences(ClassNode c, String from, String to) {
		if (c.superName.equals(from)) {
			c.superName = to;
		}
		
		for (MethodNode mn : c.methods) {
			for (AbstractInsnNode ain : mn.instructions.toArray()) {
				if (ain instanceof MethodInsnNode) {
					MethodInsnNode min = (MethodInsnNode) ain;
					if (min.name.equals("<init>")) {
						if (min.owner.equals(from)) {
							min.owner = to;
						}
						min.desc = min.desc.replace("L" + from + ";", "L" + to + ";");
					}
				}
			}
		}
	}
	
	/**
	 * @param loader
	 * 			The loader to modify references in
	 * @param from
	 * 			The reference owner to change
	 * @param to
	 * 			What to change the reference owner to
	 */
	public static void swapSuperReferences(ClassNodeLoader loader, String from, String to) {
		for (ClassNode cn : loader.getAll()) {
			swapSuperReferences(cn, from, to);
		}
	}
	
	/**
	 * @param c
	 * 			The class node to modify references in
	 * @param from
	 * 			The reference owner to change
	 * @param to
	 * 			What to change the reference owner to
	 */
	public static void swapReferences(ClassNode c, String from, String to) {
		if (c.superName.equals(from)) {
			c.superName = to;
		}
		
		for (MethodNode mn : c.methods) {
			for (AbstractInsnNode ain : mn.instructions.toArray()) {
				if (ain instanceof TypeInsnNode) {
					TypeInsnNode tin = (TypeInsnNode) ain;
					if (tin.desc.equals(from)) {
						tin.desc = to;
					}
				} else if (ain instanceof MethodInsnNode) {
					MethodInsnNode min = (MethodInsnNode) ain;
					if (min.owner.equals(from)) {
						min.owner = to;
					}
					min.desc = min.desc.replace("L" + from + ";", "L" + to + ";");
				} else if (ain instanceof FieldInsnNode) {
					FieldInsnNode fin = (FieldInsnNode) ain;
					if (fin.owner.equals(from)) {
						fin.owner = to;
					}
					fin.desc = fin.desc.replace("L" + from + ";", "L" + to + ";");
				}
			}
			
			mn.desc = mn.desc.replace("L" + from + ";", "L" + to + ";");
			
			if (mn.localVariables != null) {
				for (LocalVariableNode n : mn.localVariables) {
					if (n.desc.equals("L" + from + ";")) {
						n.desc = "L" + to + ";";
					}
				}
			}
		}
		
		for (FieldNode fn : c.fields) {
			fn.desc = fn.desc.replace("L" + from + ";", "L" + to + ";");
		}
	}
	
	/**
	 * @param loader
	 * 			The loader to modify references in
	 * @param from
	 * 			The reference owner to change
	 * @param to
	 * 			What to change the reference owner to
	 */
	public static void swapReferences(ClassNodeLoader loader, String from, String to) {
		for (ClassNode cn : loader.getAll()) {
			swapReferences(cn, from, to);
		}
	}
	
	/**
	 * @param cn
	 * 			The class node to replace references in
	 * @param methodOwner
	 * 			The method owner to search for
	 * @param methodDesc
	 * 			The method description to search for
	 * @param from
	 * 			The method name to replace
	 * @param to
	 * 			What to replace the old method name with
	 */
	public static void swapMethodReferences(ClassNode cn, String methodOwner, String methodDesc, String from, String to) {
		for (MethodNode mn : cn.methods) {
			for (AbstractInsnNode ain : mn.instructions.toArray()) {
				if (ain instanceof MethodInsnNode) {
					MethodInsnNode min = (MethodInsnNode) ain;
					
					if (min.owner.equals(methodOwner)) {
						if (min.desc.equals(methodDesc)) {
							if (min.name.equals(from)) {
								min.name = to;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * @param cn
	 * 			The class node to replace references in
	 * @param methodOwner
	 * 			The method owner to search for
	 * @param from
	 * 			The method name to replace
	 * @param to
	 * 			What to replace the old method name with
	 */
	public static void swapMethodReferences(ClassNode cn, String methodOwner, String from, String to) {
		for (MethodNode mn : cn.methods) {
			for (AbstractInsnNode ain : mn.instructions.toArray()) {
				if (ain instanceof MethodInsnNode) {
					MethodInsnNode min = (MethodInsnNode) ain;
					
					if (min.owner.equals(methodOwner) || cn.superName.equals(methodOwner)) {
						if (min.name.equals(from)) {
							System.out.println("lol " + min.name);
							min.name = to;
						}
					}
				}
			}
		}
	}
	
	/**
	 * @param cn
	 * 			The class loader to replace references in
	 * @param methodOwner
	 * 			The method owner to search for
	 * @param methodDesc
	 * 			The method description to search for
	 * @param from
	 * 			The method name to replace
	 * @param to
	 * 			What to replace the old method name with
	 */
	public static void swapMethodReferences(ClassNodeLoader loader, String methodOwner, String methodDesc, String from, String to) {
		for (ClassNode cn : loader.getAll()) {
			swapMethodReferences(cn, methodOwner, methodDesc, from, to);
		}
	}
	
	/**
	 * @param cn
	 * 			The class loader to replace references in
	 * @param methodOwner
	 * 			The method owner to search for
	 * @param from
	 * 			The method name to replace
	 * @param to
	 * 			What to replace the old method name with
	 */
	public static void swapMethodReferences(ClassNodeLoader loader, String methodOwner, String from, String to) {
		for (ClassNode cn : loader.getAll()) {
			swapMethodReferences(cn, methodOwner, from, to);
		}
	}
	
	/**
	 * Secures the provided class loader
	 * 
	 * @param loader
	 * 			The loader to secure
	 */
	public static void secure(ClassNodeLoader loader) {
		ASMUtil.swapReferences(loader, "java/lang/System", "demmonic/rwrapper/asm/layer/SystemLayer");
		ASMUtil.swapReferences(loader, "java/net/NetworkInterface", "demmonic/rwrapper/asm/layer/NetworkInterfaceLayer");
		ASMUtil.swapReferences(loader, "java/lang/Runtime", "demmonic/rwrapper/asm/layer/RuntimeLayer");
		ASMUtil.swapReferences(loader, "java/lang/Process", "demmonic/rwrapper/asm/layer/ProcessLayer");
		
		ASMUtil.swapSuperReferences(loader, "java/lang/ClassLoader", "demmonic/rwrapper/asm/layer/ClassLoaderLayer");
		ASMUtil.swapMethodReferences(loader, "demmonic/rwrapper/asm/layer/ClassLoaderLayer", "defineClass", "ourDefineClass");
	}
	
	/**
	 * Secures the provided class node
	 * 
	 * @param cn
	 * 			The class node to secure
	 */
	public static void secure(ClassNode cn) {
		ASMUtil.swapReferences(cn, "java/lang/System", "demmonic/rwrapper/asm/layer/SystemLayer");
		ASMUtil.swapReferences(cn, "java/net/NetworkInterface", "demmonic/rwrapper/asm/layer/NetworkInterfaceLayer");
		ASMUtil.swapReferences(cn, "java/lang/Runtime", "demmonic/rwrapper/asm/layer/RuntimeLayer");
		ASMUtil.swapReferences(cn, "java/lang/Process", "demmonic/rwrapper/asm/layer/ProcessLayer");
		
		ASMUtil.swapSuperReferences(cn, "java/lang/ClassLoader", "demmonic/rwrapper/asm/layer/ClassLoaderLayer");
		ASMUtil.swapMethodReferences(cn, "demmonic/rwrapper/asm/layer/ClassLoaderLayer", "defineClass", "ourDefineClass");
	}
	
	private ASMUtil() { }
	
}
