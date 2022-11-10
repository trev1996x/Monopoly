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

		int frameWidth = this.frontFrame.getWidth(),
				frameHeight = this.frontFrame.getHeight() - 20;

		renderer.boxes[0] = new GameBox(0, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[1] = new GameBox(70, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[2] = new GameBox(140, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[3] = new GameBox(210, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[4] = new GameBox(280, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[5] = new GameBox(350, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[6] = new GameBox(420, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[7] = new GameBox(490, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[8] = new GameBox(560, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[9] = new GameBox(630, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[10] = new GameBox(700, 20, frameWidth / 11, frameHeight / 11);
		renderer.boxes[11] = new GameBox(700, 88, frameWidth / 11, frameHeight / 11);
		renderer.boxes[12] = new GameBox(700, 156, frameWidth / 11, frameHeight / 11);
		renderer.boxes[13] = new GameBox(700, 224, frameWidth / 11, frameHeight / 11);
		renderer.boxes[14] = new GameBox(700, 292, frameWidth / 11, frameHeight / 11);
		renderer.boxes[15] = new GameBox(700, 360, frameWidth / 11, frameHeight / 11);
		renderer.boxes[16] = new GameBox(700, 428, frameWidth / 11, frameHeight / 11);
		renderer.boxes[17] = new GameBox(700, 496, frameWidth / 11, frameHeight / 11);
		renderer.boxes[18] = new GameBox(700, 564, frameWidth / 11, frameHeight / 11);
		renderer.boxes[19] = new GameBox(700, 632, frameWidth / 11, frameHeight / 11);
		renderer.boxes[20] = new GameBox(700, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[21] = new GameBox(630, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[22] = new GameBox(560, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[23] = new GameBox(490, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[24] = new GameBox(420, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[25] = new GameBox(350, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[26] = new GameBox(280, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[27] = new GameBox(210, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[28] = new GameBox(140, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[29] = new GameBox(70, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[30] = new GameBox(0, 700, frameWidth / 11, frameHeight / 11);
		renderer.boxes[31] = new GameBox(0, 632, frameWidth / 11, frameHeight / 11);
		renderer.boxes[32] = new GameBox(0, 564, frameWidth / 11, frameHeight / 11);
		renderer.boxes[33] = new GameBox(0, 496, frameWidth / 11, frameHeight / 11);
		renderer.boxes[34] = new GameBox(0, 428, frameWidth / 11, frameHeight / 11);
		renderer.boxes[35] = new GameBox(0, 360, frameWidth / 11, frameHeight / 11);
		renderer.boxes[36] = new GameBox(0, 292, frameWidth / 11, frameHeight / 11);
		renderer.boxes[37] = new GameBox(0, 224, frameWidth / 11, frameHeight / 11);
		renderer.boxes[38] = new GameBox(0, 156, frameWidth / 11, frameHeight / 11);
		renderer.boxes[39] = new GameBox(0, 88, frameWidth / 11, frameHeight / 11);

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