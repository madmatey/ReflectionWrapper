package demmonic.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Represents the applet container's menu bar
 * @author Demmonic
 *
 */
public class AppletMenuBar {

	private static JMenuBar bar;
	
	private static JMenu viewMenu;
	private static JMenuItem consoleMenuItem;
	private static JMenuItem explorerMenuItem;
	
	static {
		bar = new JMenuBar();
		
		viewMenu = new JMenu("View");
		consoleMenuItem = new JMenuItem("Console");
		explorerMenuItem = new JMenuItem("Explorer");
		
		viewMenu.add(consoleMenuItem);
		viewMenu.add(explorerMenuItem);
		
		bar.add(viewMenu);
		
		addListeners();
	}
	
	/**
	 * Adds listeners to our UI components
	 */
	private static void addListeners() {
		consoleMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandUI.open();
			}
			
		});
		
		explorerMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InstanceTreeUI.open();
			}
			
		});
	}
	
	/**
	 * @return Static instance
	 */
	public static JMenuBar get() {
		return bar;
	}
	
}
