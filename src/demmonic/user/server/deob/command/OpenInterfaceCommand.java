package demmonic.user.server.deob.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;

/**
 * 
 * @author Demmonic
 *
 */
public class OpenInterfaceCommand extends Command {

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
		return "openinterface INTERFACEID";
	}

}
