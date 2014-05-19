package demmonic.rwrapper.loader.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.container.reflect.ReflectionField;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class TestCommand extends Command {

	public TestCommand() {
		super("test");
	}

	@Override
	public String getSyntax() {
		return "test";
	}

	@Override
	public void parse(String cmd) {
		ReflectionClass clazz = Loader.getClass("client");
		ReflectionField field = clazz.getField("openInterfaceID", "int");
		field.setValue(5292);
	}

}
