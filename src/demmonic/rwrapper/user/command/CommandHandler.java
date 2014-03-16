package demmonic.rwrapper.user.command;

import java.util.ArrayList;

import demmonic.rwrapper.ui.CommandUI;

/**
 * 
 * @author Demmonic
 *
 */
public final class CommandHandler {

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
		
		CommandUI commandInterface = CommandUI.getInstance();
		if (input.equals("help")) {
			commandInterface.push("---Commands---");
			for (Command c : commands) {
				commandInterface.push(c.getIdentifier() + " (" + c.getSyntax() + ")");
			}
			commandInterface.push("");
			return;
		}
		
		String identifier = input.split(" ")[0];
		
		for (Command c : commands) {
			if (c.getIdentifier().equals(identifier)) {
				c.parse(input);
				return;
			}
		}
		
		commandInterface.push("Unknown command " + identifier);
	}
	
	private CommandHandler() { }
	
}
