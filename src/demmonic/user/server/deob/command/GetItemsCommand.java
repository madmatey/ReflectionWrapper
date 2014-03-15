package demmonic.user.server.deob.command;

import demmonic.Command;
import demmonic.Loader;
import demmonic.container.reflect.ReflectionClass;
import demmonic.ui.CommandUI;

public class GetItemsCommand extends Command {

	public GetItemsCommand() {
		super("getitems");
	}

	@Override
	public void parse(String input) {
		int interfaceId = Integer.parseInt(input.split(" ")[1]);
		
		ReflectionClass clazz = Loader.getClass("RSInterface");
		Object[] cache = (Object[])clazz.getField("interfaceCache", "[LRSInterface;").getValue();
		ReflectionClass instance = new ReflectionClass(cache[interfaceId].getClass(), cache[interfaceId]);
		int[] inv = (int[]) instance.getField("inventory", "[I").getValue();
		if (inv != null) {
			for (int i = 0; i < inv.length; i++) {
				CommandUI.getInstance().push(i + ": " + inv[i]);
			}
		}
	}

	@Override
	public String getSyntax() {
		return "getitems interfaceid";
	}
	
}
