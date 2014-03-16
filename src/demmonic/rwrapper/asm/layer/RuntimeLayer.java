package demmonic.rwrapper.asm.layer;

/**
 * Used to provide a layer of communication between the RT runtime class, and the client
 * @author Demmonic
 * 
 * A RSPS client should NEVER need to use this class
 * 
 * If it's using this class, it's either malicious or it's using some bullshit HWID method
 * for tracking players
 */
public class RuntimeLayer {

	//DUPLICATE METHODS FOR MIMICING THE RUNTIME CLASS IN THE RT LIBRARY
	public static RuntimeLayer getRuntime() {
		return new RuntimeLayer();
	}
	
}
