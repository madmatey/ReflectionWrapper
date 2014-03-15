package demmonic;

import javax.swing.UIManager;

import demmonic.user.server.destructivex.DestructiveServer;

/**
 * 
 * @author Demmonic
 *
 */
public class Main {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Loader(new DestructiveServer()).start();
	}
	
}
