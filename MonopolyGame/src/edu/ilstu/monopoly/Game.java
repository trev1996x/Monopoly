package edu.ilstu.monopoly;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.ilstu.monopoly.items.*;

public class Game {

	/**
	 * Very basic constructor. Initialize JFrame and run the "run" method.
	 */
	public Game() {
		this.mainWindow = new Window("ISU Monopoly");
		this.display = mainWindow.getGamePanel();

		for (int i = 0; i < Player.MAX_PLAYERS; i++)
			this.players.add(new Player(i + 1));

		this.players.trimToSize(); // Leave this here

		// This will start the game threads, so this MUST run last!
		try {
			this.run();
		} catch (InterruptedException ie) { // "ie" stands for internet explorer btw
			ie.printStackTrace();
		}
	}

	/**
	 * Rendering logic
	 * 
	 * @param display Game JPanel
	 * @throws InterruptedException Thread interrupted exception
	 */
	public void run() throws InterruptedException {
		// Set the front (first) frame to be rendered
		this.frontFrame = new BufferedImage(this.display.getWidth(), this.display.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		this.selectedFrame = this.frontFrame;

		// Initialize the renderer (thread)
		Renderer renderer = new Renderer();
		renderer.setGame(this);

		int boxSize = 65, verticalOffset = 34, horizontalOffset = 43;

		// generate top 11
		for(int i = 0; i < 11; i++)
			renderer.boxes[i] = new GameBox(horizontalOffset + boxSize * i, verticalOffset, boxSize);
		
		// generate right 9
		for(int i = 11; i < 20; i++)
			renderer.boxes[i] = new GameBox(horizontalOffset + boxSize * 10, verticalOffset + boxSize * (i - 10), boxSize);

		// generate bottom 11
		for(int i = 20; i < 31; i++)
		renderer.boxes[i] = new GameBox(horizontalOffset + boxSize * (i - 20), verticalOffset + boxSize * 10, boxSize);

		// generate left 9
		for(int i = 31; i < 40; i++)
			renderer.boxes[i] = new GameBox(horizontalOffset, verticalOffset + boxSize * (i - 30), boxSize);

		// for(int i = 0; i < renderer.boxes.length; i++) if(renderer.boxes[i] == null) renderer.boxes[i] = new GameBox(0, 0, 0);

		// While the JPanel can display something, keep rendering.
		while (this.display.isDisplayable()) {

			// Cache the Mouse Position from the JPanel
			renderer.setMousePosition(this.display.getMousePosition());
			renderer.setMouseClicked(this.display.getMouseClicked()); // get cached click action
			this.display.resetMouseClicked(); // reset action

			if (this.selectedFrame == this.frontFrame) {
				// Show first frame on JPanel
				this.display.setFrame(this.selectedFrame);
				// Render the other frame
				this.backFrame = new BufferedImage(this.display.getWidth(), this.display.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				this.selectedFrame = this.backFrame;
				renderer.setFrame(this.selectedFrame);
				renderer.run();
			} else {
				// Show second frame on JPanel
				this.display.setFrame(this.selectedFrame);
				// Render the other frame
				this.frontFrame = new BufferedImage(this.display.getWidth(), this.display.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				this.selectedFrame = this.frontFrame;
				renderer.setFrame(this.selectedFrame);
				renderer.run();
			}

			// Renderer finished, join context to keep going
			renderer.join();
		}
	}

	/**
	 * Start the game
	 */
	public void startPlaying() {
		this.status = GameStatus.GAME_SETUP;
	}

	/**
	 * Stop the game
	 */
	public void stopPlaying() {
		this.status = GameStatus.SPLASH_SCREEN;
	}

	// By making these protected, they are accessible in the Renderer (Thread)
	protected Window mainWindow;
	protected GamePanel display;

	private BufferedImage selectedFrame;
	private BufferedImage frontFrame;
	private BufferedImage backFrame;

	protected ArrayList<Player> players = new ArrayList<Player>(Player.MAX_PLAYERS);

	protected Player currentPlayer;

	public enum GameStatus {
		SPLASH_SCREEN,
		GAME_SETUP,
		CURRENTLY_PLAYING
	}

	protected GameStatus status = GameStatus.SPLASH_SCREEN;
}