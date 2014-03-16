package demmonic.rwrapper.user.server.deob.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

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
		
		ReflectionClass client = Loader.getClass("client");

		client.getField("openInterfaceID", "int").setValue(id);
	}

	@Override
	public String getSyntax() {
		return "openinterface interfaceid";
	}

}
