package demmonic;

import demmonic.user.server.pkhonor.PKHonorServer;

/**
 * 
 * @author Demmonic
 *
 */
public class Main {

	public static void main(String[] args) {
		new Loader(new PKHonorServer()).start();
	}
	
}
