package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;

public class SetUsernameCommand extends Command {

	public SetUsernameCommand() {
		super("setusername");
	}

	@Override
	public void parse(String input) {
		String username = input.split(" ")[1];
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		Object instance = Loader.getClientInstance();
		
		client.getField("hG", "java.lang.String").setValue(instance, username);
	}

	@Override
	public String getSyntax() {
		return "setusername USERNAME";
	}

}
