package demmonic.rwrapper.asm.layer;

/**
 * Used to provide a layer of communication between the RT runtime class, and the client
 * @author Demmonic
 * 
 * the goal of this class is to stop malicious code from being executed
 * within the client (such as opening websites, or executing files)
 */
public final class RuntimeLayer {

	private RuntimeLayer() { }
	
	//DUPLICATE METHODS FOR MIMICING THE RUNTIME CLASS IN THE RT LIBRARY
	public static RuntimeLayer getRuntime() {
		return new RuntimeLayer();
	}
	
	public Process exec(String cmd) {
		return null;
	}
	
	public Process exec(String[] cmd) {
		return null;
	}
	
	public long totalMemory() {
		return Runtime.getRuntime().totalMemory();
	}
	
	public long freeMemory() {
		return Runtime.getRuntime().freeMemory();
	}
	
}
