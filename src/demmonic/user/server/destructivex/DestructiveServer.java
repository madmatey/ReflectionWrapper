package demmonic.user.server.destructivex;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.container.reflect.ReflectionClass;
import demmonic.user.Server;
import demmonic.user.server.destructivex.command.OpenInterfaceCommand;
import demmonic.user.server.destructivex.command.SendCommandCommand;
import demmonic.user.server.destructivex.command.SpoofButtonClickCommand;

public class DestructiveServer extends Server {
	
	public DestructiveServer() {
		add(new OpenInterfaceCommand());
		add(new SpoofButtonClickCommand());
		add(new SendCommandCommand());
	}
	
	@Override
	public InputStream getClient() {
		try {
			return new FileInputStream("./data/DX Oldschool.jar");
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
