package demmonic.user.server.destructivex.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.container.reflect.ReflectionMethod;

public class SendCommandCommand extends Command {

	public SendCommandCommand() {
		super("sendcommand");
	}

	@Override
	public void parse(String input) {
		String command = "::";
		
		String[] split = input.split(" ");
		for (int i = 1; i < split.length; i++) {
			command += split[i] + " ";
		}
		
		ReflectionClass clazz = Loader.getClass("client");
		Object outBuffer = clazz.getField("sD", "MZ").getValue();
		
		ReflectionClass bufferClass = new ReflectionClass(outBuffer.getClass(), outBuffer);
		ReflectionMethod writeOpcodeMethod = bufferClass.getMethod("I", int.class);
		ReflectionMethod writeIntMethod = bufferClass.getMethod("Z", int.class);
		ReflectionMethod writeStringMethod = bufferClass.getMethod("I", String.class);
		
		writeOpcodeMethod.invoke(103);
		writeIntMethod.invoke(command.length() - 1);
		writeStringMethod.invoke(command.substring(2));
	}

	@Override
	public String getSyntax() {
		return "crash";
	}
	
}
