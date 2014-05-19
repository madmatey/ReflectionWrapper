package demmonic.rwrapper.loader.pkhonor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.loader.pkhonor.command.TestCommand;
import demmonic.rwrapper.user.ClientLoader;

/**
 * 
 * @author Demmonic
 *
 */
public class GenericClientLoader extends ClientLoader {

	public GenericClientLoader() {
		super.add(new TestCommand());
	}
	
	@Override
	public InputStream getClient() {
		try {
			return new FileInputStream("./data/genericclient.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReflectionClass getMainClass() {
		return get("client");
	}
	
}
