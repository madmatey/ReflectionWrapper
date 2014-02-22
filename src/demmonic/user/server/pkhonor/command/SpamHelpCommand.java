package demmonic.user.server.pkhonor.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.container.reflect.ReflectionMethod;

public class SpamHelpCommand extends Command {

	public SpamHelpCommand() {
		super("spamhelp");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");
		Object instance = Loader.getClientInstance();
		
		ReflectionClass bufferClass = client.getField("ao", "pkhonor.dt").getType();
		Object outBuffer = client.getField("ao", "pkhonor.dt").getValue(instance);
		
		if (outBuffer != null && bufferClass != null) {
			ReflectionMethod putOpcodeMethod = bufferClass.getMethod("a", int.class);
			ReflectionMethod putLEShortMethod = bufferClass.getMethod("b", int.class);
			ReflectionMethod putStringMethod = bufferClass.getMethod("a", String.class);
			
			String command = "::help";
			
			for (int i = 0; i < 200; i++) {
				putOpcodeMethod.invoke(outBuffer, 103);
				putLEShortMethod.invoke(outBuffer, command.length() - 1);
				putStringMethod.invoke(outBuffer, command.substring(2));
			}
		}
	}

	@Override
	public String getSyntax() {
		return "spamhelp";
	}

}
