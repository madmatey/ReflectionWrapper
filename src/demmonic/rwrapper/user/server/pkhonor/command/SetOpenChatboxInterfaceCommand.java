package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class SetOpenChatboxInterfaceCommand extends Command {

	public SetOpenChatboxInterfaceCommand() {
		super("setopenchatboxinterface");
	}

	@Override
	public void parse(String input) {
		int id = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("jm", "int").setValue(id);
	}

	@Override
	public String getSyntax() {
		return "setopenchatboxinterface interfaceid";
	}
	
}
