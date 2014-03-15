package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.ui.CommandUI;

public class GetInvOverlayCommand extends Command {

	public GetInvOverlayCommand() {
		super("getinvoverlay");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		
		CommandUI.getInstance().push("open interface: " + client.getField("hW", "int").getValue());
	}

	@Override
	public String getSyntax() {
		return "getinvoverlay";
	}

}
