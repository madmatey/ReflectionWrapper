package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.ui.CommandUI;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class GetOpenChatboxInterfaceCommand extends Command {

	public GetOpenChatboxInterfaceCommand() {
		super("getopenchatboxinterface");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		CommandUI.getInstance().push("open interface: " + client.getField("jm", "int").getValue());
	}

	@Override
	public String getSyntax() {
		return "getopenchatboxinterface";
	}
	
}
