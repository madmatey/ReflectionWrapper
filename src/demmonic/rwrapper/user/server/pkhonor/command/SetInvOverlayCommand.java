package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class SetInvOverlayCommand extends Command {

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
		return "setinvoverlay interfaceid";
	}

}
