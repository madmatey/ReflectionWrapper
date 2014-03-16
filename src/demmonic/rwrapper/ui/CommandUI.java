package demmonic.rwrapper.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import demmonic.rwrapper.user.command.CommandHandler;

/**
 * 
 * @author Demmonic
 *
 */
public final class CommandUI extends JFrame {

	private static final long serialVersionUID = 6061578507543986183L;
	
	private JPanel mainPanel;
	
	private JScrollPane historyScrollPane;
	private JTextArea historyTextArea;
	private JTextField inputTextField;
	
	private CommandUI() {
		this.mainPanel = new JPanel();
		
		this.historyTextArea = new JTextArea();
		this.historyScrollPane = new JScrollPane(historyTextArea);
		this.inputTextField = new JTextField();
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 450, 315);
		this.setResizable(false);
		this.setContentPane(mainPanel);
		this.setTitle("Reflection Wrapper - Command Handler");
		
		this.mainPanel.setLayout(null);
		
		this.historyTextArea.setFont(new Font("Verdana", Font.PLAIN, 9));
		this.historyTextArea.setEditable(false);
		this.historyScrollPane.setBounds(0, 0, 444, 262);
		this.mainPanel.add(historyScrollPane);
		
		this.inputTextField.setFont(new Font("Verdana", Font.PLAIN, 9));
		this.inputTextField.setBounds(2, 264, 440, 21);
		this.mainPanel.add(inputTextField);
		
		this.addListeners();
	}
	
	/**
	 * Adds listeners to our UI components
	 */
	private void addListeners() {
		inputTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				push(inputTextField.getText());
				CommandHandler.parse(inputTextField.getText());
				inputTextField.setText("");
			}
			
		});
	}
	
	/**
	 * Pushes a message to the history error
	 * 
	 * @param message
	 * 			The message to add
	 */
	public void push(String message) {
		if (historyTextArea.getText().length() <= 0) {
			historyTextArea.setText(message);
		} else {
			historyTextArea.setText(historyTextArea.getText() + "\n" + message);
		}
	}
	
	private static CommandUI commandInterface;
	
	/**
	 * @return static instance
	 */
	public static CommandUI getInstance() {
		return (commandInterface == null ? (commandInterface = new CommandUI()) : commandInterface);
	}
	
}
