package demmonic.rwrapper.ui.node;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Represents a instance node in a jtree
 * @author Demmonic
 *
 */
public final class InstanceTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = -7595118109311706911L;

	/**
	 * This node's name
	 */
	private String name;
	
	/**
	 * The instance
	 */
	private Object instance;
	
	/**
	 * @param displayName
	 * 			Node display name
	 * @param instance
	 * 			Instance to store
	 */
	public InstanceTreeNode(String name, String displayName, Object instance) {
		super(displayName);
		
		this.name = name;
		this.instance = instance;
	}
	
	/**
	 * @param other
	 * 			The other node to copy
	 */
	public InstanceTreeNode(InstanceTreeNode other) {
		super(other);
		
		this.instance = other.getInstance();
	}
	
	/**
	 * @return This node's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return This node's instance
	 */
	public Object getInstance() {
		return instance;
	}
	
	/**
	 * @param instance
	 * 			This node's new instance
	 */
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	
	/**
	 * @param name
	 * 			The name to search for
	 * @return This node's child matching the provided name
	 */
	public InstanceTreeNode getChild(String name) {
		for (int i = 0; i < getChildCount(); i++) {
			if (getChildAt(i) instanceof InstanceTreeNode) {
				InstanceTreeNode n = (InstanceTreeNode) getChildAt(i);
				if (n.getName().equals(name)) {
					return n;
				}
			}
		}
		
		return null;
	}
	
}
