package demmonic.rwrapper.ui;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import demmonic.rwrapper.user.ClientLoaderManager;

/**
 * 
 * @author Demmonic
 *
 */
public class LoaderSelectorUI extends JFrame {

	private static final long serialVersionUID = 8157152798208028679L;
	
	private JPanel mainPanel;
	private JScrollPane selectionScrollPane;
	private JPanel selectionPanel;
	
	private LoaderSelectorUI() {
		mainPanel = new JPanel();
		selectionPanel = new JPanel();
		selectionScrollPane = new JScrollPane(selectionPanel);
		
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		
		for (String s : ClientLoaderManager.getLoadedNames()) {
			selectionPanel.add(new LoaderSelectionNode(this, s));
		}
		
		mainPanel.setLayout(new GridLayout());
		mainPanel.add(selectionScrollPane);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 450, 230);
		this.setContentPane(mainPanel);
		this.setResizable(false);
		this.setTitle("Reflection Wrapper - Loader Selector");
	}
	
	private static LoaderSelectorUI instance = new LoaderSelectorUI();
	
	/**
	 * @return Static instance
	 */
	public static LoaderSelectorUI getInstance() {
		return instance;
	}
	
}
