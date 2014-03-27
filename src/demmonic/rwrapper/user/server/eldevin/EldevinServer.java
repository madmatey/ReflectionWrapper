package demmonic.rwrapper.user.server.eldevin;

import java.applet.AppletStub;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.Server;

/**
 * 
 * @author Demmonic
 *
 */
public class EldevinServer extends Server {

	@Override
	public InputStream getClient() {
		try {
			return new FileInputStream("./data/dumped.jar");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReflectionClass getMainClass() {
		return get("gbclient.GBApplet");
	}

	@Override
	public AppletStub getStub() {
		return new EldevinStub();
	}
	
	@Override
	public Dimension getSize() {
		return new Dimension(1200, 700);
	}
	
}
