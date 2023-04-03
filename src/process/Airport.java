package process;

public class Airport {

	private int x;
	private int y;

	/**
	 * Construction of the airport
	 */
	public Airport() {
		this.x = 176;
		this.y = 176;
	}

	/**
	 * Moving from the airport
	 */
	public void play() {
		if(this.x > 0 && this.x < 176) {
			this.x ++;
			this.y ++;
		} else if(this.x > 176 && this.x < 352) {
			this.x ++;
			this.y --;
		}
	}

	/**
	 * Airplane taking off
	 */
	public void leaving() {
		this.x = 177;
		this.y = 177;
	}

	/**
	 * Airplane landing
	 */
	public void arriving() {
		this.x = 1;
		this.y = 1;
	}

	/**
	 * Accessors
	 * @return
	 */
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
