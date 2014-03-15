package demmonic.rwrapper.user.server.deob;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.Server;
import demmonic.rwrapper.user.server.deob.command.GetItemsCommand;
import demmonic.rwrapper.user.server.deob.command.OpenInterfaceCommand;
import demmonic.rwrapper.user.server.deob.command.SetItemsCommand;
import demmonic.rwrapper.user.server.deob.command.SpoofButtonClickCommand;

/**
 * 
 * @author Demmonic
 *
 */
public final class DeobServer extends Server {
	
	public DeobServer() {
		add(new SetItemsCommand());
		add(new GetItemsCommand());
		add(new OpenInterfaceCommand());
		add(new SpoofButtonClickCommand());
	}
	
	@Override
	public InputStream getClient() {
		try {
			return new FileInputStream("./data/client8.jar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ReflectionClass getMainClass() {
		return get("client");
	}

}
