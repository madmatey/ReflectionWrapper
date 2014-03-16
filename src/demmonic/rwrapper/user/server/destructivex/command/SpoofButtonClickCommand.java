package demmonic.rwrapper.user.server.destructivex.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.container.reflect.ReflectionMethod;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class SpoofButtonClickCommand extends Command {

	public SpoofButtonClickCommand() {
		super("spoofbuttonclick");
	}

	@Override
	public void parse(String input) {
		int buttonId = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass clazz = Loader.getClass("client");
		Object outBuffer = clazz.getField("sD", "MZ").getValue();
		
		ReflectionClass bufferClass = new ReflectionClass(outBuffer.getClass(), outBuffer);
		ReflectionMethod writeOpcodeMethod = bufferClass.getMethod("I", int.class);
		ReflectionMethod writeShortMethod = bufferClass.getMethod("C", int.class);
		
		writeOpcodeMethod.invoke(185);
		writeShortMethod.invoke(buttonId);
	}

	@Override
	public String getSyntax() {
		return "spoofbuttonclick buttonindex";
	}
	
}
