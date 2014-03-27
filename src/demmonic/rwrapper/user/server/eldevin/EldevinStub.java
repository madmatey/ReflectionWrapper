package demmonic.rwrapper.user.server.eldevin;

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
public class EldevinStub implements AppletStub {

	private HashMap<String, String> params = new HashMap<String, String>();
	
	public EldevinStub() {
		params.put("name", "Eldevin");
		params.put("java_arguments", "-Xmx600m");
		params.put("separate_jvm", "true");
		params.put("safemode", "false");
        params.put("username", "LXGSCUBCW12169");
        params.put("sessionkey", "LBHCxsXTvMJmEVsLmJiJFlPKIGKjfdtNZFMfMyhgVUhdADxyPKReXQQGflUtsbOvqJKXiKEuKSBNFxqPyQOJDersNsNAvbEXOMrgLxcQUjUHXegNJrwZHicveZbCnMHW");
        params.put("fileserver", "https://cdn.eldevin.com/2014_03_26/out/");
        params.put("serverip", "192.34.56.7");
        params.put("serverport", "7778");
        params.put("cowpat", "fMFTQ6mJZPj6Yko0kz5ZdFKPUSQMFm7PCEohR6YARVIDi4jt7rab9S68Pu7IDVDr");
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
			return new URL("https://cdn.eldevin.com/2014_03_26/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public URL getDocumentBase() {
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
