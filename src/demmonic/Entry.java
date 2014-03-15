package demmonic;

import javax.swing.UIManager;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.user.server.destructivex.DestructiveServer;

/**
 * 
 * @author Demmonic
 *
 */
public class Entry {

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
