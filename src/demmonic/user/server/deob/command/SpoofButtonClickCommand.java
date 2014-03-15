package demmonic.user.server.deob.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.container.reflect.ReflectionMethod;

public class SpoofButtonClickCommand extends Command {

	public SpoofButtonClickCommand() {
		super("spoofbuttonclick");
	}

	@Override
	public void parse(String input) {
		int buttonId = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass clazz = Loader.getClass("client");
		Object outBuffer = clazz.getField("stream", "Stream").getValue();
		
		ReflectionClass bufferClass = new ReflectionClass(outBuffer.getClass(), outBuffer);
		ReflectionMethod writeOpcodeMethod = bufferClass.getMethod("createFrame", int.class);
		ReflectionMethod writeShortMethod = bufferClass.getMethod("writeWord", int.class);
		
		writeOpcodeMethod.invoke(185);
		writeShortMethod.invoke(buttonId);
	}

	@Override
	public String getSyntax() {
		return "crash";
	}
	
}
