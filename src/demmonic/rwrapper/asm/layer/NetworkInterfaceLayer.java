package demmonic.rwrapper.asm.layer;

import java.net.InetAddress;
import java.util.Random;

/**
 * Used to provide a layer of communication between the RT network interface class, and the client
 * @author Demmonic
 *
 */
public class NetworkInterfaceLayer {

	private static Random rand = new Random();

	//DUPLICATE METHODS FOR MIMICING THE NETWORK INTERFACE CLASS IN THE RT LIBRARY
	public static NetworkInterfaceLayer getByInetAddress(InetAddress addr) {
		return new NetworkInterfaceLayer();
	}

	public byte[] getHardwareAddress() {
		byte[] random = new byte[8];
		for (int i = 0; i < 8; i++) {
			random[i] = (byte) rand.nextInt(128);
		}
		
		return random;
	}
	
}
