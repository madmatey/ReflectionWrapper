package demmonic.rwrapper.user.server.bsloader;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * 
 * @author Demmonic
 *
 */
public class BSLoaderStub implements AppletStub {

	private HashMap<String, String> params = new HashMap<String, String>();
	
	public BSLoaderStub() {
		params.put("separate_jvm", "true");
		params.put("worldid", "1");
		params.put("portoff", "0");
		params.put("free", "0");
	}

	@Override
	public void appletResize(int width, int height) {
		
	}

	@Override
	public AppletContext getAppletContext() {
		return null;
	}

	@Override
	public URL getCodeBase() {
		try {
			return new URL("http://www.battle-scape.com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public URL getDocumentBase() {
		try {
			return new URL("http://www.battle-scape.com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getParameter(String key) {
		return params.get(key);
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
}
