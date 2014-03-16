package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Command;
import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;

/**
 * 
 * @author Demmonic
 *
 */
public final class OpenInterfaceCommand extends Command {

	public OpenInterfaceCommand() {
		super("openinterface");
	}

	@Override
	public void parse(String input) {
		int id = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("cL", "int").setValue(id);
	}

	@Override
	public String getSyntax() {
		return "openinterface interfaceid";
	}

}