package process;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class JAirport extends JPanel {

	private static final long serialVersionUID = 1L;

	private process.Radar lWorld;
	private process.Airport lAirport;
	private process.Messages lRadar;

	private gui.Radar gWorld;
	private gui.Airport gAirport;
	private gui.Messages radar;

	/**
	 * Construction of the graphic interface
	 */
	public JAirport() {
		this.lWorld = new process.Radar();
		this.lAirport = new process.Airport();
		this.lRadar = new process.Messages();

		this.gWorld = new gui.Radar();
		this.gAirport = new gui.Airport();
		this.radar = new gui.Messages(this);

		/**
		 * Implementation of the refreshment at 60 FPS
		 */
		Timer timer = new Timer();
		timer.schedule(new Repaint(), 0, 2000 / 60);
	}

	/**
	 * Updating the screen
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		/**
		 * Map display
		 */
		int plane = this.lWorld.play(this.lRadar);

		if(plane == 1) this.lAirport.leaving();
		else if(plane == 2) this.lAirport.arriving();

		this.gWorld.draw(g, this.lWorld.getPlanes());

		/**
		 * View of the airport
		 */
		this.lAirport.play();
		this.gAirport.draw(g, this.lAirport.getX(), this.lAirport.getY());

		/**
		 * Displaying the text screen
		 */
		this.radar.draw(g, this.lRadar.getText());

		// System.out.println("JPlane is Running !");
	}

	public class Repaint extends TimerTask {

		/**
		 * Refreshment of the screen at each new frame
		 */
		public void run() {
			repaint();
		}
	}

}
