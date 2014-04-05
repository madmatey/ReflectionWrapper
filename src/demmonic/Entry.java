package demmonic;

import javax.swing.UIManager;

import demmonic.rwrapper.ui.LoaderSelectorUI;

/**
 * 
 * @author Demmonic
 *
 */
public final class Entry {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		LoaderSelectorUI.getInstance().setVisible(true);
	}
	
	private Entry() { }
	
}
