package demmonic.rwrapper.user.server.destructivex;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.Server;
import demmonic.rwrapper.user.server.destructivex.command.OpenInterfaceCommand;
import demmonic.rwrapper.user.server.destructivex.command.SendCommandCommand;
import demmonic.rwrapper.user.server.destructivex.command.SpoofButtonClickCommand;

/**
 * 
 * @author Demmonic
 *
 */
public final class DestructiveServer extends Server {
	
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
