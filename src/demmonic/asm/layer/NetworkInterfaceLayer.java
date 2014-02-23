package demmonic.asm.layer;

import java.net.InetAddress;
import java.util.Random;

/**
 * Used to provide a layer of communication between the RT networkinterface class, and the client
 * @author Demmonic
 *
 */
public class NetworkInterfaceLayer {

	private static Random rand = new Random();
	
	/**
	 * @param addr
	 * 			The address to.. ignore
	 * @return New this
	 */
	public static NetworkInterfaceLayer getByInetAddress(InetAddress addr) {
		return new NetworkInterfaceLayer();
	}
	
	/**
	 * @return Random "mac-address"
	 */
	public byte[] getHardwareAddress() {
		System.out.println("attempting to grab mac lel");
		
		byte[] random = new byte[8];
		for (int i = 0; i < 8; i++) {
			random[i] = (byte) rand.nextInt(128);
		}
		
		return random;
	}
	
}
