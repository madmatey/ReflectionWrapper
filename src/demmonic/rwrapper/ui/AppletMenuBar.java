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
public final class AppletMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1528750029294526866L;
	
	private JMenu viewMenu;
	private JMenuItem consoleMenuItem;
	private JMenuItem explorerMenuItem;
	private JMenuItem aboutMenuItem;
	
	private AppletMenuBar() {
		this.viewMenu = new JMenu("View");
		this.consoleMenuItem = new JMenuItem("Console");
		this.explorerMenuItem = new JMenuItem("Explorer");
		this.aboutMenuItem = new JMenuItem("About");
		
		this.viewMenu.add(consoleMenuItem);
		this.viewMenu.add(explorerMenuItem);
		this.viewMenu.add(aboutMenuItem);
		
		this.add(viewMenu);
		
		this.addListeners();
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
		
		aboutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutUI.getInstance().setVisible(true);
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
