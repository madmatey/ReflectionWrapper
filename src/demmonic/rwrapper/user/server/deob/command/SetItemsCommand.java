package demmonic.rwrapper.user.server.deob.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public final class SetItemsCommand extends Command {

	public SetItemsCommand() {
		super("itemonitem");
	}

	@Override
	public void parse(String input) {
		int interfaceId = Integer.parseInt(input.split(" ")[1]);
		int id1 = Integer.parseInt(input.split(" ")[2]);
		int id2 = Integer.parseInt(input.split(" ")[3]);
		
		ReflectionClass clazz = Loader.getClass("RSInterface");
		Object[] cache = (Object[])clazz.getField("interfaceCache", "[LRSInterface;").getValue();
		ReflectionClass instance = new ReflectionClass(cache[interfaceId].getClass(), cache[interfaceId]);
		int[] inv = (int[]) instance.getField("inventory", "[I").getValue();
		inv[0] = id1;
		inv[1] = id2;
	}

	@Override
	public String getSyntax() {
		return "setitems interfaceid firstslotid secondslotid";
	}
	
}
