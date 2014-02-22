package demmonic;

import java.util.ArrayList;

import demmonic.ui.CommandUI;

/**
 * 
 * @author Demmonic
 *
 */
public class CommandHandler {

	private static ArrayList<Command> commands = new ArrayList<Command>();
	
	/**
	 * @param c
	 * 			The command to add
	 */
	public static void add(Command c) {
		commands.add(c);
	}
	
	/**
	 * Parses the input for commands
	 * @param input
	 * 			The input to parse
	 */
	public static void parse(String input) {
		if (input.split(" ").length <= 0)
			return;
		
		if (input.equals("help")) {
			CommandUI.push("---Commands---");
			for (Command c : commands) {
				CommandUI.push(c.getIdentifier() + " (" + c.getSyntax() + ")");
			}
			CommandUI.push("");
			return;
		}
		
		String identifier = input.split(" ")[0];
		
		for (Command c : commands) {
			if (c.getIdentifier().equals(identifier)) {
				c.parse(input);
				return;
			}
		}
		
		CommandUI.push("Unknown command " + identifier);
	}
	
}
