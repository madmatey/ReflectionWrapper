package demmonic.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import demmonic.CommandHandler;

/**
 * 
 * @author Demmonic
 *
 */
public class CommandUI {

	private static JFrame mainFrame;
	private static JPanel mainPanel;
	
	private static JScrollPane historyScrollPane;
	private static JTextArea historyTextArea;
	private static JTextField inputTextField;
	
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes our UI components
	 */
	private static void initializeComponents() {
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		
		historyTextArea = new JTextArea();
		historyScrollPane = new JScrollPane(historyTextArea);
		inputTextField = new JTextField();
		
		mainFrame.setTitle("Command handler");
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 450, 315);
		mainFrame.setResizable(false);
		
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
	private static void addListeners() {
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
	public static void push(String message) {
		if (historyTextArea.getText().length() <= 0) {
			historyTextArea.setText(message);
		} else {
			historyTextArea.setText(historyTextArea.getText() + "\n" + message);
		}
	}
	
	/**
	 * Sets our command UI to visible
	 */
	public static void setVisible() {
		initializeComponents();
		addListeners();
		
		mainFrame.setVisible(true);
	}
	
}
