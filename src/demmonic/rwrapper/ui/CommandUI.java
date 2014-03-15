package demmonic.rwrapper.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import demmonic.rwrapper.CommandHandler;

/**
 * 
 * @author Demmonic
 *
 */
public class CommandUI extends JFrame {

	private static final long serialVersionUID = 6061578507543986183L;
	
	private JPanel mainPanel;
	
	private JScrollPane historyScrollPane;
	private JTextArea historyTextArea;
	private JTextField inputTextField;
	
	public CommandUI() {
		initializeComponents();
		addListeners();
	}
	
	/**
	 * Initializes our UI components
	 */
	private void initializeComponents() {
		mainPanel = new JPanel();
		
		this.historyTextArea = new JTextArea();
		historyScrollPane = new JScrollPane(historyTextArea);
		inputTextField = new JTextField();
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 450, 315);
		this.setResizable(false);
		this.setContentPane(mainPanel);
		this.setTitle("Command handler");
		
		mainPanel.setLayout(null);
		
		historyTextArea.setFont(new Font("Verdana", Font.PLAIN, 9));
		historyTextArea.setEditable(false);
		historyScrollPane.setBounds(0, 0, 444, 262);
		mainPanel.add(historyScrollPane);
		
		inputTextField.setFont(new Font("Verdana", Font.PLAIN, 9));
		inputTextField.setBounds(2, 264, 440, 21);
		mainPanel.add(inputTextField);
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
