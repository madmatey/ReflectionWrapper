package demmonic.rwrapper.ui;

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
public class AppletMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1528750029294526866L;
	
	private static JMenu viewMenu;
	private static JMenuItem consoleMenuItem;
	private static JMenuItem explorerMenuItem;
	
	private AppletMenuBar() {
		viewMenu = new JMenu("View");
		consoleMenuItem = new JMenuItem("Console");
		explorerMenuItem = new JMenuItem("Explorer");
		
		viewMenu.add(consoleMenuItem);
		viewMenu.add(explorerMenuItem);
		
		this.add(viewMenu);
		
		addListeners();
	}
	
	/**
	 * Adds listeners to our UI components
	 */
	private void addListeners() {
		consoleMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandUI.getInstance().setVisible(true);
			}
			
		});
		
		explorerMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InstanceTreeUI.getInstance().open();
			}
			
		});
	}
	
	private static AppletMenuBar barInterface;
	
	/**
	 * @return static instance
	 */
	public static AppletMenuBar getInstance() {
		return (barInterface == null ? (barInterface = new AppletMenuBar()) : barInterface);
	}
	
}
