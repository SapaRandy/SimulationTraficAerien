package process;

import java.util.ArrayList;

public class Messages {

	private ArrayList<String> text;

	/**
	 * Initialization of the message screen
	 */
	public Messages() {
		this.text = new ArrayList<String>();
		this.text.add("Welcome into Traffic Airplane Controler !");
	}

	/**
	 * Add a line of text
	 * @param text
	 */
	public void addText(String text) {
		this.text.add(text);
	}

	/**
	 * Retrieve all texts
	 * @return
	 */
	public ArrayList<String> getText() {
		return this.text;
	}

}
