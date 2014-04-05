package demmonic.rwrapper.ui.loader;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.user.ClientLoaderManager;

/**
 * 
 * @author Demmonic
 *
 */
public class LoaderSelectionNode extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1868195148597885795L;

	private LoaderSelectorUI parent;
	
	private JLabel nameLabel;
	private JButton startButton;
	
	/**
	 * @param parent
	 * @param name
	 */
	public LoaderSelectionNode(LoaderSelectorUI parent, String name) {
		this.parent = parent;
		
		this.setBorder(new EtchedBorder());
		this.setPreferredSize(new Dimension(400, 200));
		this.setLayout(null);
		
		nameLabel = new JLabel(name);
		nameLabel.setBounds(15, 15, 500, 25);
		
		startButton = new JButton("Start");
		startButton.setBounds(310, 155, 100, 25);
		startButton.addActionListener(this);
		
		this.add(nameLabel);
		this.add(startButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.dispose();
		
		new Loader(ClientLoaderManager.forName(nameLabel.getText())).start();
	}
	
}
