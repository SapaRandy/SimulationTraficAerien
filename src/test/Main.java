package test;

import process.JThread;

/**
 * @author SAPA Randy
 */

public class Main {

	/** Main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/** JThread start
		 * 
		 */
		JThread jThread = new JThread();
		jThread.start();
	}

}