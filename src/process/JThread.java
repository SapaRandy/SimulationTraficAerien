package process;

import javax.swing.JFrame;

public class JThread implements Runnable {

	/**
	 * Starting the thread
	 */
	public void start() {
		/**
		 * Launching the program thread
		 */
		Thread jThread = new Thread(this, "JPlane");
		jThread.start();


		try {
			/**
			 * Main program loop
			 */
			while(true) {
				//System.out.println("Main Thread is Running !");
				Thread.sleep(2000 / 60);
			}
		} catch(InterruptedException exception) {
			System.exit(0);
		}
	}

	/**
	 * Operation of the program thread
	 */
	public void run() {
		JAirport myJAirport = new JAirport();

		/**
		 * Opening and displaying the window
		 */
		JFrame jPlane = new JFrame();
		jPlane.setSize(960, 540);
		jPlane.setResizable(false);
		jPlane.setContentPane(myJAirport);
		jPlane.setVisible(true);
		jPlane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
