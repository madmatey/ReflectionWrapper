package demmonic.ui;

import java.applet.Applet;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Demmonic
 *
 */
public class AppletUI {

	private static JFrame mainFrame;
	private static JPanel mainPanel;
	
	static {
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		
		mainFrame.setTitle("DemmClient");
		mainFrame.setContentPane(mainPanel);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(765, 557));
		
		mainFrame.pack();
	}
	
	/**
	 * @param applet
	 * 			The applet to start
	 */
	public static void start(Applet applet) {
		applet.setBounds(0, 0, 765, 557);
		mainPanel.add(applet);
		
		applet.init();
		applet.start();
		
		mainFrame.setVisible(true);
	}
	
}
