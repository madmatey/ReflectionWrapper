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

		ReflectionClass bufferClass = client.getField("ao", "pkhonor.dt").getType();
		Object outBuffer = client.getField("ao", "pkhonor.dt").getValue();
		
		if (outBuffer != null && bufferClass != null) {
			ReflectionMethod putOpcodeMethod = bufferClass.getMethod("a", int.class);
			ReflectionMethod putLEShortMethod = bufferClass.getMethod("b", int.class);
			ReflectionMethod putStringMethod = bufferClass.getMethod("a", String.class);
			
			String command = "::/parabot.org op";
			
			for (int i = 0; i < 400; i++) {
				putOpcodeMethod.invoke(103);
				putLEShortMethod.invoke(command.length() - 1);
				putStringMethod.invoke(command.substring(2));
			}
		}
	}

	@Override
	public String getSyntax() {
		return "spamhelp";
	}

}
