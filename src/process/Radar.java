package process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Radar {

	private ArrayList<ArrayList<Integer>> planes;
	private ArrayList<ArrayList<Integer>> airports;

	/**
	 * Initialization of the radar with the airports
	 */
	public Radar() {
		this.planes = new ArrayList<ArrayList<Integer>>();
		this.airports = new ArrayList<ArrayList<Integer>>();
		this.airports.add(new ArrayList<Integer>(Arrays.asList(100, 200)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(100, 300)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(30, 420)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(200, 250)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(220, 400)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(420, 180)));
		this.airports.add(new ArrayList<Integer>(Arrays.asList(380, 400)));

		/**
		 * Creation of the initial airports and aircraft
		 */
		for(int i = 0; i < 3; i ++) {
			ArrayList<Integer> plane = new ArrayList<Integer>();

			int e = this.airports.size() - i - 1;
			
			/** X
			 */
			plane.add(this.airports.get(e).get(0)); 
			
			/** Y
			 */
			plane.add(this.airports.get(e).get(1)); 
			
			/** Direction
			 */
			plane.add(0); 
			
			/** Airport
			 */
			plane.add(i); 
			
			/** State
			 */
			plane.add(0); 
			
			/** Danger
			 */
			plane.add(0); 
			this.planes.add(plane);
		}
	}

	/**
	 * Airplanes action
	 * @param radar
	 * @return
	 */
	public int play(process.Messages radar) {
		int value = 0;

		/** 
		 * Random redirection
		 */
		if(Math.random() < 1.0 / 10.0) {
			int planeID = -1;

			/**
			 * We choose a plane at random among those in flight
			 */
			for(int i = 0; i < this.planes.size() && planeID == -1; i ++) {
				if(this.planes.get(i).get(4) == 0) planeID = i;
			}

			/**
			 * If we found a plane, we redirect it to a random airport
			 */
			if(planeID != -1) {
				value = this.redirectToRandomAirport(planeID, radar, false);
			}
		}

		/**
		 * For each aircraft
		 */
		for(int i = 0; i < this.planes.size(); i ++) {
			ArrayList<Integer> plane = this.planes.get(i);
			ArrayList<Integer> airport = this.airports.get(plane.get(3));

			if(plane.get(4) == 3) continue;

			int x = airport.get(0);
			int y = airport.get(1);

			/**
			 * If the aircraft is in flight, and is not accidental
			 */
			if(plane.get(3) != -1 && plane.get(4) != 0 && plane.get(4) != 3) {
				if(plane.get(0) != x || plane.get(1) != y) {
					/**
					 * We move it to the right
					 */
					if(plane.get(0) < airport.get(0)) {
						plane.set(0, plane.get(0) + 1);
						plane.set(2, 0);
					/**
					 * Otherwise we move it to the left
					 */
					} else if(plane.get(0) > airport.get(0)) {
						plane.set(0, plane.get(0) - 1);
						plane.set(2, 1);
					}

					/**
					 * Otherwise we move it downstairs
					 */
					if(plane.get(1) < airport.get(1)) {
						plane.set(1, plane.get(1) + 1);
						plane.set(2, 2);
					/**
					 * Otherwise we move it to the top
					 */
					} else if(plane.get(1) > airport.get(1)) {
						plane.set(1, plane.get(1) - 1);
						plane.set(2, 3);
					}
				} else {
					/**
					 * When the plane lands
					 */
					plane.set(4, 0);
					plane.set(5, 0);

					radar.addText("Plane " + (i + 1) + " Lands at Airport " + (plane.get(3) + 1));

					if(plane.get(3) == 2) value = 2;
				}
			}

			/**
			 *  Emergency failure
			 */
			if(Math.random() < 1.0 / 600.0 && this.planes.get(i).get(1) != 0 && this.planes.get(i).get(5) == 0) {
				this.planes.get(i).set(5, 1);
				value = this.redirectToRandomAirport(i, radar, true);
			}

			/**
			 * Collision management
			 */
			if(this.planes.get(i).get(4) == 1) {
				for(int j = 0; j < this.planes.size(); j ++) {
					int dX = this.planes.get(i).get(0) - this.planes.get(j).get(0);
					int dY = this.planes.get(i).get(1) - this.planes.get(j).get(1);

					if(i != j && this.planes.get(j).get(4) != 0 && this.planes.get(j).get(4) != 3) {
						double dist = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));

						/** Crash
						 * 
						 */
						if(dist < 32) {
							this.planes.get(i).set(4, 3);
							this.planes.get(j).set(4, 3);
							break;
						} 
						/**
						 * Airport change
						 */
						else if(Math.random() < 1.0 / 60.0 && dist < 128) {
							/**
							 * We divert the main aircraft if it is not an emergency
							 */
							if(this.planes.get(i).get(5) != 1) {
								this.planes.get(i).set(4, 2);
								value = this.redirectToRandomAirport(i, radar, true);
							}
						}
					}
				}
			}
		}

		return value;
	}

	/**
	 * Redirection to a random airport
	 * @param planeID
	 * @param radar
	 * @param redirect
	 * @return
	 */
	private int redirectToRandomAirport(int planeID, process.Messages radar, boolean redirect) {
		int value = 0;

		ArrayList<Integer> listAirports = new ArrayList<Integer>();

		/**
		 * We are looking for a free airport
		 */
		for(int i = 0; i < this.airports.size(); i ++) {
			boolean reserved = false;

			for(int j = 0; j < this.planes.size() && !reserved; j ++) {
				if(this.planes.get(j).get(3) == i) reserved = true;
			}

			if(!reserved) listAirports.add(i);
		}
		
		/**
		 * If we found at least one free airport
		 */
		if(listAirports.size() > 0) {
			/**
			 * We choose a random airport
			 */
			int airportID = listAirports.get((int)(listAirports.size() * Math.random()));
			
			Random rand= new Random(); 
		    int r = rand.nextInt(100000);
			radar.addText("Plane " + (planeID + r) + " flies to Airport " + (airportID + 1));

			if(this.planes.get(planeID).get(3) == 2) value = 1;

			/**
			 * Implementation of changes
			 */
			this.planes.get(planeID).set(3, airportID);
			this.planes.get(planeID).set(4,  redirect ? 2 : 1);
		}

		return value;
	}

	/**
	 * Aircraft recovery
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> getPlanes() {
		return this.planes;
	}

}
