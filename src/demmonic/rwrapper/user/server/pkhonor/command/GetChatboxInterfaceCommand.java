package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Command;
import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.ui.CommandUI;

/**
 * 
 * @author Demmonic
 *
 */
public final class GetChatboxInterfaceCommand extends Command {

	public GetChatboxInterfaceCommand() {
		super("getchatboxinterface");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		CommandUI.getInstance().push("open interface: " + client.getField("jm", "int").getValue());
	}

	@Override
	public String getSyntax() {
		return "getchatboxinterface";
	}
	
}
