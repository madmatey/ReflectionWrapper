package demmonic.rwrapper.asm.layer;

/**
 * 
 * @author Demmonic
 * 
 * A RSPS client should NEVER need to use this class
 * 
 * If it's using this class, it's either malicious or it's using some bullshit HWID method
 * for tracking players
 */
public class RuntimeLayer {

	public static RuntimeLayer getRuntime() {
		return new RuntimeLayer();
	}
	
}
