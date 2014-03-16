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
public final class GetOpenInterfaceCommand extends Command {

	public GetOpenInterfaceCommand() {
		super("getopeninterface");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		
		CommandUI.getInstance().push("open interface: " + client.getField("cL", "int").getValue());
	}

	@Override
	public String getSyntax() {
		return "getopeninterface";
	}

}
