package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.ui.CommandUI;

public class GetOpenInterfaceCommand extends Command {

	public GetOpenInterfaceCommand() {
		super("getopeninterface");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		Object instance = Loader.getClientInstance();
		
		CommandUI.push("open interface: " + client.getField("cL", "int").getValue());
	}

	@Override
	public String getSyntax() {
		return "getopeninterface";
	}

}
