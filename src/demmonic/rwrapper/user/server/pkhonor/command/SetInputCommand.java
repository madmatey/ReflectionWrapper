package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class SetInputCommand extends Command {

	public SetInputCommand() {
		super("setinput");
	}

	@Override
	public void parse(String input) {
		String value = input.split(" ")[1];
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("eY", "java.lang.String").setValue(value);
	}

	@Override
	public String getSyntax() {
		return "setinput input";
	}

}