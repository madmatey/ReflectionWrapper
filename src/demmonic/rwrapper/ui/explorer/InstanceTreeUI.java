package demmonic.rwrapper.ui.explorer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.container.reflect.ReflectionField;

/**
 * 
 * @author Demmonic
 *
 */
public final class InstanceTreeUI extends JFrame {

	private static final long serialVersionUID = -4113136498519536627L;

	private JPanel mainPanel;
	
	private JScrollPane treeScrollPane;
	
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	
	private JPopupMenu nodeMenu;
	private JMenuItem loadMenuItem;
	private JMenuItem reloadMenuItem;
	
	private InstanceTreeUI() {
		this.mainPanel = new JPanel();
		
		this.root = new DefaultMutableTreeNode("root");
		this.treeModel = new DefaultTreeModel(root);
		this.tree = new JTree(treeModel);
		this.tree.addMouseListener(new NodeListener());
		this.treeScrollPane = new JScrollPane(tree);
		this.treeScrollPane.setBounds(0, 0, 400, 400);
		
		this.loadMenuItem = new JMenuItem("Load");
		this.loadMenuItem.addActionListener(new LoadListener());
		this.reloadMenuItem = new JMenuItem("Reload");
		this.reloadMenuItem.addActionListener(new ReloadListener());
		
		this.nodeMenu = new JPopupMenu("Node options");
		this.nodeMenu.add(loadMenuItem);
		this.nodeMenu.add(reloadMenuItem);
		
		this.mainPanel.setLayout(new GridLayout());
		this.mainPanel.add(treeScrollPane);
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.setTitle("Reflection Wrapper - Instance Explorer");
		this.pack();
	}
	
	/**
	 * Resets the tree and opens it
	 */
	public void open() {
		this.tree.setModel(treeModel = new DefaultTreeModel(root = new DefaultMutableTreeNode("root")));
		this.root.add(new InstanceTreeNode("client", "client", Loader.getClientInstance()));
		this.setVisible(true);
	}

	/**
	 * Used to handle node mouse actions
	 * @author Demmonic
	 *
	 */
	private class NodeListener extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				TreePath p = tree.getPathForLocation(e.getX(), e.getY());
				if (p != null && p.getPath()[p.getPath().length - 1] instanceof InstanceTreeNode) {
					tree.getSelectionModel().setSelectionPath(p);
					nodeMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
		
	}

	/**
	 * Used to handle node loading
	 * @author Demmonic
	 *
	 */
	private class LoadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TreePath p = tree.getSelectionPath();
			if (p != null) {
				Object o = p.getPath()[p.getPath().length - 1];
				if (o instanceof InstanceTreeNode) {
					InstanceTreeNode n = (InstanceTreeNode) o;
					if (n.getChildCount() > 0) return;
					
					Object instance = n.getInstance();
					ReflectionClass c = new ReflectionClass(instance.getClass(), instance);
					
					for (ReflectionField f : c.getFields()) {
						Object value = f.getValue();
						if (value != null) {
							n.add(new InstanceTreeNode(f.getName(), f.getName() + " - " + value, value));
						}
					}
					
					ReflectionClass parent = c.getSuper();
					
					while (!parent.getName().equalsIgnoreCase("java.lang.Object")) {
						for (ReflectionField f : parent.getFields()) {
							Object value = f.getValue();
							if (value != null) {
								n.add(new InstanceTreeNode(f.getName(), f.getName() + " - " + value, value));
							}
						}
						parent = parent.getSuper();
					}
				}
			}
		}
		
	}

	/**
	 * Used to handle node reloading
	 * @author Demmonic
	 *
	 */
	private class ReloadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TreePath p = tree.getSelectionPath();
			if (p != null) {
				Object o = p.getPath()[p.getPath().length - 1];
				if (o instanceof InstanceTreeNode) {
					InstanceTreeNode n = (InstanceTreeNode) o;
					
					Object instance = n.getInstance();
					ReflectionClass c = new ReflectionClass(instance.getClass(), instance);
					
					for (ReflectionField f : c.getFields()) {
						Object value = f.getValue();
						InstanceTreeNode present = n.getChild(f.getName());
						if (present != null) {
							present.setUserObject(f.getName() + " - " + value);
							present.setInstance(value);
							treeModel.nodeChanged(present);
						}
					}
					
					treeModel.nodeChanged(n);
				}
			}
		}
		
	}
	
	private static InstanceTreeUI treeInterface;
	
	/**
	 * @return static instance
	 */
	public static InstanceTreeUI getInstance() {
		return (treeInterface == null ? (treeInterface = new InstanceTreeUI()) : treeInterface);
	}
	
}
