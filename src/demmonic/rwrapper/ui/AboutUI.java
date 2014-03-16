package demmonic.rwrapper.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Demmonic
 *
 */
public final class AboutUI extends JFrame {

	private static final long serialVersionUID = 1515915655729880187L;
	
	private JPanel mainPanel;
	private JEditorPane aboutPane;
	
	private AboutUI() {
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridLayout());
		
		this.aboutPane = new JEditorPane();
		this.aboutPane.setEditable(false);
		this.aboutPane.setContentType("text/html");
		this.aboutPane.setText("A RSPS client loader, with a thin reflection wrapper<br><br>Objectweb ASM is used for bytecode instrumentation for flexibility that the reflection API cannot provide<br><br><a href=\"https://github.com/demmonic/ReflectionWrapper\">Project link</a>");
		
		this.mainPanel.add(aboutPane);
		this.mainPanel.setPreferredSize(new Dimension(400, 250));
		
		this.setTitle("Reflection Wrapper - About");
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	private static AboutUI aboutInterface;
	
	/**
	 * @return static instance
	 */
	public static AboutUI getInstance() {
		return (aboutInterface == null ? (aboutInterface = new AboutUI()) : aboutInterface);
	}
	
}
