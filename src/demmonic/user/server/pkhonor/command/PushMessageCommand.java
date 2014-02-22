package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.container.reflect.ReflectionMethod;

public class PushMessageCommand extends Command {

	public PushMessageCommand() {
		super("pushmessage");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		Object instance = Loader.getClientInstance();
		
		ReflectionMethod method = client.getMethod("a", String.class, int.class, String.class);
		
		String message = input.split(" ")[1].replace("_", " ");
		
		if (method != null) {
			method.invoke(instance, message, 0, "");
		}
	}

	@Override
	public String getSyntax() {
		return "pushmessage MESSAGEHERE";
	}

}
