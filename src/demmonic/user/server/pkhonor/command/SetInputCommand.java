package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;

public class SetInputCommand extends Command {

	public SetInputCommand() {
		super("setinput");
	}

	@Override
	public void parse(String input) {
		String value = input.split(" ")[1];
		
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		client.getField("eY", "java.lang.String").setValue(value);
	}

	@Override
	public String getSyntax() {
		return "getopeninterface";
	}

}