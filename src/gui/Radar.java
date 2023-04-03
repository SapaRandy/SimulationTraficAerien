package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Radar extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image background;
	private Image planeTop;
	private Image planeBottom;
	private Image planeLeft;
	private Image planeRight;
	private Image planeLanded;
	private Image crash;
	private Image danger;

	private ArrayList<ArrayList<Integer>> airports;

	/**
	 * Initialization
	 */
	public Radar() {
		try {
			/**
			 * Images loading 
			 */
			this.background = ImageIO.read(new File("src/images/backgrounds/world.jpg"));
			this.planeTop = ImageIO.read(new File("src/images/planes/top.png"));
			this.planeBottom = ImageIO.read(new File("src/images/planes/bottom.png"));
			this.planeLeft = ImageIO.read(new File("src/images/planes/left.png"));
			this.planeRight = ImageIO.read(new File("src/images/planes/right.png"));
			this.planeLanded = ImageIO.read(new File("src/images/planes/landed.png"));
			this.crash = ImageIO.read(new File("src/images/planes/crash.png"));
			this.danger = ImageIO.read(new File("src/images/planes/danger.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}

		/**
		 * Initialization of airports
		 */
		this.airports = new ArrayList<ArrayList<Integer>>();
		this.airports.add(new ArrayList<Integer>(Arrays.asList(100, 240)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(100, 300)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(30, 420)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(200, 250)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(220, 400)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(420, 180)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(380, 400)));
	}

	/**
	 * Display of the module
	 * @param g
	 * @param planes
	 */
	public void draw(Graphics g, ArrayList<ArrayList<Integer>> planes) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 480, 540);

		g.drawImage(this.background, 0, 0, null);

		/**
		 * For each airplane
		 */
		for(int i = 0; i < planes.size(); i ++) {
			ArrayList<Integer> plane = planes.get(i);
			ArrayList<Integer> airport = this.airports.get(plane.get(3));
			g.setColor(Color.RED);

			/**
			 * If the plane falls
			 */
			if(plane.get(4) == 3) g.drawImage(this.crash, plane.get(0), plane.get(1), null);
			else {
					/**
					 * If the plane is running
					 */
				if(plane.get(3) != -1 && plane.get(4) != 0) g.drawLine(plane.get(0) + 32, plane.get(1) + 32, airport.get(0) + 32, airport.get(1) + 32);

				/**
				 * If the plane is landed
				 */
				if(plane.get(4) == 0) g.drawImage(this.planeLanded, plane.get(0), plane.get(1), null);
				/**
				 * If the plane is in flight
				 */
				else if(plane.get(4) == 1) {
					if(plane.get(2) == 0) g.drawImage(this.planeRight, plane.get(0), plane.get(1), null);
					else if(plane.get(2) == 1) g.drawImage(this.planeLeft, plane.get(0), plane.get(1), null);
					else if(plane.get(2) == 2) g.drawImage(this.planeBottom, plane.get(0), plane.get(1), null);
					else if(plane.get(2) == 3) g.drawImage(this.planeTop, plane.get(0), plane.get(1), null);
				} else if(plane.get(4) == 2) {
					/**
					 * Display of a landing plane
					 */
					g.drawImage(this.planeLanded, plane.get(0), plane.get(1), null);
				}

				if(plane.get(5) == 1) g.drawImage(this.danger, plane.get(0), plane.get(1) - 32, null);
			}
		}
	}

}
