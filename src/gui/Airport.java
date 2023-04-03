package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Airport extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image background;
	private Image plane;

	/** Initialization of the airport
	 * 
	 */
	public Airport() {
		try {
			/** Images loading
			 * 
			 */
			this.background = ImageIO.read(new File("src/images/backgrounds/airport.jpg"));
			this.plane = ImageIO.read(new File("src/images/planes/plane.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/** Airport display
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public void draw(Graphics g, int x, int y) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(480, 0, 480, 270);
		g.drawImage(this.background, 480, 0, null);

		/** If the plane is on the airport window, it is displayed
		 * 
		 */
		if(x > 0 && x < 352) g.drawImage(this.plane, x + 480, y, null);
	}

}
