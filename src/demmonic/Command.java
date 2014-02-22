package demmonic;

/**
 *
 * @author Demmonic
 *
 */
public abstract class Command {

	private String identifier;

	/**
	 * @param identifier
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
	 */
	public abstract void parse(String input);
	
	/**
	 * @return The correct syntax (an example of the command)
	 */
	public abstract String getSyntax();
	
}
