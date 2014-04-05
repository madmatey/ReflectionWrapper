package demmonic.rwrapper.ui.applet;

import java.applet.Applet;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Demmonic
 *
 */
public final class AppletUI extends JFrame {

	private static final long serialVersionUID = -3869979711758848339L;
	
	private JPanel mainPanel;
	
	private AppletUI() {
		this.mainPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(mainPanel);
		this.setJMenuBar(AppletMenuBar.getInstance());
		this.setTitle("Reflection Wrapper");
		
		this.mainPanel.setLayout(null);
		this.mainPanel.setPreferredSize(new Dimension(765, 503));
		
		this.pack();
	}
	
	/**
	 * @param applet
	 * 			The applet to start
	 */
	public void start(Applet applet, Dimension appletSize) {
		applet.setBounds(0, 0, appletSize.width, appletSize.height);
		
		mainPanel.add(applet);
		mainPanel.setPreferredSize(appletSize);
		applet.init();
		applet.start();
		
		this.pack();
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
