package demmonic.user.server.deob;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.container.reflect.ReflectionClass;
import demmonic.user.Server;
import demmonic.user.server.deob.command.CrashCommand;
import demmonic.user.server.deob.command.GetItemsCommand;
import demmonic.user.server.deob.command.SetItemsCommand;
import demmonic.user.server.deob.command.OpenInterfaceCommand;
import demmonic.user.server.deob.command.SpoofButtonClickCommand;

public class DeobServer extends Server {
	
	public DeobServer() {
		add(new SetItemsCommand());
		add(new GetItemsCommand());
		add(new OpenInterfaceCommand());
		add(new SpoofButtonClickCommand());
		add(new CrashCommand());
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
