package demmonic.rwrapper.asm.layer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ProcessLayer {

	public InputStream getInputStream() {
		return new ByteArrayInputStream(new byte[] { (byte) 'C', (byte) ':', (byte) '/' });
	}
	
	public void destroy() {
		
	}
	
}
