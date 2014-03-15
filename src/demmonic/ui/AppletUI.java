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
public class AppletUI extends JFrame {

	private static final long serialVersionUID = -3869979711758848339L;
	
	private JPanel mainPanel;
	
	public AppletUI() {
		mainPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(mainPanel);
		this.setJMenuBar(AppletMenuBar.getInstance());
		this.setTitle("Reflection Wrapper");
		
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(765, 503));
		
		this.pack();
	}
	
	/**
	 * @param applet
	 * 			The applet to start
	 */
	public void start(Applet applet) {
		applet.setBounds(0, 0, 765, 503);
		mainPanel.add(applet);
		
		applet.init();
		applet.start();
		
		this.setVisible(true);
	}
	
	private static AppletUI appletInterface;
	
	/**
	 * @return static instance
	 */
	public static AppletUI getInstance() {
		return (appletInterface == null ? (appletInterface = new AppletUI()) : appletInterface);
	}
	
}
