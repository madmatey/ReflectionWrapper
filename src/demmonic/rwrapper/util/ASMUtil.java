package demmonic.rwrapper.util;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
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
	public static void swapReferences(ClassNode c, String from, String to) {
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
	
	private ASMUtil() { }
	
}
