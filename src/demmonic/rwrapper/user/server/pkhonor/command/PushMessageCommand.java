package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Command;
import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.container.reflect.ReflectionMethod;

/**
 * 
 * @author Demmonic
 *
 */
public final class PushMessageCommand extends Command {

	public PushMessageCommand() {
		super("pushmessage");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		
		ReflectionMethod method = client.getMethod("a", String.class, int.class, String.class);
		
		String message = input.split(" ")[1].replace("_", " ");
		
		if (method != null) {
			method.invoke(message, 0, "");
		}
	}

	@Override
	public String getSyntax() {
		return "pushmessage message";
	}

}
