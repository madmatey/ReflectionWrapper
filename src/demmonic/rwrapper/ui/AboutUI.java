package demmonic.rwrapper.ui;

import javax.swing.JFrame;

public class AboutUI extends JFrame {

	private static final long serialVersionUID = 1515915655729880187L;
	private static AboutUI aboutInterface;
	
	/**
	 * @return static instance
	 */
	public static AboutUI getInstance() {
		return (aboutInterface == null ? (aboutInterface = new AboutUI()) : aboutInterface);
	}
	
}
