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
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setJMenuBar(AppletMenuBar.get());
		mainFrame.setTitle("Reflection Wrapper");
		
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(765, 503));
		
		mainFrame.pack();
	}
	
	/**
	 * @param applet
	 * 			The applet to start
	 */
	public static void start(Applet applet) {
		applet.setBounds(0, 0, 765, 503);
		mainPanel.add(applet);
		
		applet.init();
		applet.start();
		
		mainFrame.setVisible(true);
	}
	
}
