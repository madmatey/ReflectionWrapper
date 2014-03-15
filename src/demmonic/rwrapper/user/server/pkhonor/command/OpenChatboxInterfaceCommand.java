package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Command;
import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;

/**
 * 
 * @author Demmonic
 *
 */
public final class OpenChatboxInterfaceCommand extends Command {

	public OpenChatboxInterfaceCommand() {
		super("openchatboxinterface");
	}

	@Override
	public void parse(String input) {
		int id = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("jm", "int").setValue(id);
	}

	@Override
	public String getSyntax() {
		return "openchatboxinterface interfaceid";
	}
	
}
