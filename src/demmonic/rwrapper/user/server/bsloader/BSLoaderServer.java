package demmonic.rwrapper.user.server.bsloader;

import java.applet.AppletStub;
import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.Server;

/**
 * 
 * @author Demmonic
 *
 */
public class BSLoaderServer extends Server {

	@Override
	public InputStream getClient() {
		try {
			return new FileInputStream("./data/loader.jar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReflectionClass getMainClass() {
		return get("Loader");
	}

	@Override
	public AppletStub getStub() {
		return new BSLoaderStub();
	}
	
}
