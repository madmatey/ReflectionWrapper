package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;

public class SetInvOverlayCommand extends Command {

	public SetInvOverlayCommand() {
		super("setinvoverlay");
	}

	@Override
	public void parse(String input) {
		int id = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("hW", "int").setValue(id);
	}

	@Override
	public String getSyntax() {
		return "setinvoverlay INTERFACEID";
	}

}
