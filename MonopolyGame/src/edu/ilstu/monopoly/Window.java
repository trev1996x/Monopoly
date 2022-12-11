/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly;

/**
 * The main JFrame
 */

import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;

public class Window extends JFrame {

	private static final long serialVersionUID = -4254972884311221585L; // auto-gen by Eclipse

	/**
	 * JFrame constructor
	 * 
	 * @param title Title of the JFrame
	 */
	Window(String title) {
		super(title); // set title and initialize JFrame
		File file = new File("./resources/favicon.png");
		Image iconImage = null;
		if(file.exists())
			iconImage = Toolkit.getDefaultToolkit().getImage("./resources/favicon.png");
		else
		{
			try {
			InputStream is = getClass().getResourceAsStream("/resources/favicon.png");
			iconImage = ImageIO.read(is);
			} catch(IOException ioe) {
				ioe.printStackTrace();
				iconImage = null;
			}
		}				
		if(iconImage != null) super.setIconImage(iconImage);
																				// https://ecomputernotes.com/java/awt-and-applets/toolkit-getdefaulttoolkit-getimage
		super.setAlwaysOnTop(true); // always on top of other apps
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // don't exit on close, allow for clean up
		Dimension defaultDimension = new Dimension(1000, 1000); // set default window size
		super.setSize(defaultDimension);
		super.setPreferredSize(defaultDimension);
		super.setResizable(false); // allow the window to be resized
		super.setOpacity(1.0f); // enforce window opacity (transparency)
		super.setLocationRelativeTo(null); // center on screen
		gamePanel = new GamePanel(); // create the game's JPanel
		super.add(gamePanel);
		super.setVisible(true); // show the JFrame
	}

	/**
	 * Get the JPanel
	 * 
	 * @return GamePanel
	 */
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	private GamePanel gamePanel;
}
