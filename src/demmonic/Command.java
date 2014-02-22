package demmonic;

/**
 *
 * @author Demmonic
 *
 */
public abstract class Command {

	/**
	 * This command's identifier
	 */
	private String identifier;

	/**
	 * @param identifier
	 * 			This command's identifier
	 */
	public Command(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * @return This command's identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	
	/**
	 * @param input
	 * 			The input to parse
	 */
	public abstract void parse(String input);
	
	/**
	 * @return The correct syntax (an example of the command)
	 */
	public abstract String getSyntax();
	
}
