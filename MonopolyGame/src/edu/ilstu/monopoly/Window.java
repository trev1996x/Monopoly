package edu.ilstu.monopoly;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = -4254972884311221585L; // auto-gen by Eclipse

	/**
	 * JFrame constructor
	 * 
	 * @param title Title of the JFrame
	 */
	Window(String title) {
		super(title); // set title and initialize JFrame
		super.setAlwaysOnTop(true); // always on top of other apps
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // don't exit on close, allow for clean up
		super.setLocationRelativeTo(null); // center on screen
		Dimension defaultDimension = new Dimension(800, 800); // set default window size
		super.setSize(defaultDimension);
		super.setPreferredSize(defaultDimension);
		super.setResizable(false); // allow the window to be resized
		super.setOpacity(1.0f); // enforce window opacity (transparency)
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
