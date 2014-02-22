package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.ui.CommandUI;

public class GetChatboxInterfaceCommand extends Command {

	public GetChatboxInterfaceCommand() {
		super("getchatboxinterface");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		Object instance = Loader.getClientInstance();
		
		CommandUI.push("open interface: " + client.getField("jm", "int").getValue(instance));
	}

	@Override
	public String getSyntax() {
		return "getchatboxinterface";
	}
	
}
