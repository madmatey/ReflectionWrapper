package demmonic.rwrapper.ui;

import java.awt.Dimension;
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
import demmonic.rwrapper.ui.node.InstanceTreeNode;

/**
 * 
 * @author Demmonic
 *
 */
public class InstanceTreeUI extends JFrame {

	private static final long serialVersionUID = -4113136498519536627L;

	private JPanel mainPanel;
	
	private JScrollPane treeScrollPane;
	
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;
	
	private JPopupMenu nodeMenu;
	private JMenuItem loadMenuItem;
	private JMenuItem reloadMenuItem;
	
	public InstanceTreeUI() {
		mainPanel = new JPanel();
		
		root = new DefaultMutableTreeNode("root");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.addMouseListener(new NodeListener());
		treeScrollPane = new JScrollPane(tree);
		treeScrollPane.setBounds(0, 0, 400, 400);
		
		loadMenuItem = new JMenuItem("Load");
		loadMenuItem.addActionListener(new LoadListener());
		reloadMenuItem = new JMenuItem("Reload");
		reloadMenuItem.addActionListener(new ReloadListener());
		
		nodeMenu = new JPopupMenu("Node options");
		nodeMenu.add(loadMenuItem);
		nodeMenu.add(reloadMenuItem);
		
		mainPanel.setLayout(null);
		mainPanel.add(treeScrollPane);
		mainPanel.setPreferredSize(new Dimension(400, 400));
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(mainPanel);
		this.setTitle("Instance explorer");
		this.pack();
	}
	
	/**
	 * Resets the tree and opens it
	 */
	public void open() {
		tree.setModel(treeModel = new DefaultTreeModel(root = new DefaultMutableTreeNode("root")));
		root.add(new InstanceTreeNode("client", "client", Loader.getClientInstance()));
		this.setVisible(true);
	}
	
	/**
	 * Represents the tree's right click handler
	 * @author Demmonic
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
	 * Represents the node menu's load listener
	 * @author Demmonic
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
				}
			}
		}
		
	}
	
	/**
	 * Represents the node menu's reload listener
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
