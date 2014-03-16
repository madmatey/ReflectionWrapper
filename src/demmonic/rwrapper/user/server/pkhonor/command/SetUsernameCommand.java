package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class SetUsernameCommand extends Command {

	public SetUsernameCommand() {
		super("setusername");
	}

	@Override
	public void parse(String input) {
		String username = input.split(" ")[1];
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("hG", "java.lang.String").setValue(username);
	}

	@Override
	public String getSyntax() {
		return "setusername newusername";
	}

}
