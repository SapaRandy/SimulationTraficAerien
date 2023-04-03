package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Messages extends JPanel {

	public Thread t;

	private static final long serialVersionUID = 1L;

	private Image background;

	private ArrayList<JLabel> lines;

	/** Initialization
	 * 
	 * @param panel
	 */
	public Messages(JPanel panel) {
		try {
			/** We load the background image
			 * 
			 */
			this.background = ImageIO.read(new File("src/images/backgrounds/radar.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}

		this.lines = new ArrayList<JLabel>();

		/** Initialization of text lines
		 * 
		 */
		for(int i = 0; i < 12; i ++) {
			JLabel line = new JLabel("", SwingConstants.CENTER);
			line.setBounds(480, 270 + i * 20, 480, 20);
			line.setFont(line.getFont().deriveFont(20));
			line.setForeground(Color.GREEN);

			panel.setLayout(null);
			panel.add(line);
			this.lines.add(line);
		}
	}

	/**  Display of text lines
	 * 
	 * @param g
	 * @param text
	 */
	public void draw(Graphics g, ArrayList<String> text) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(480, 270, 480, 270);

		g.drawImage(this.background, 480, 270, null);

		/**
		 * For each line, we display it at the right place
		 */
		for(int i = 0; i < 12; i ++) {
			if(text.size() > i) this.lines.get(i).setText(text.get(i + Math.max(text.size() - 12, 0)));
		}
	}

}
