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

		int boxSize = 70, verticalOffset = 18;

		// TODO: Fix these damn boxes
		renderer.boxes[0] = new GameBox(boxSize*0, verticalOffset, boxSize);
		renderer.boxes[1] = new GameBox(boxSize*1, verticalOffset, boxSize);
		renderer.boxes[2] = new GameBox(boxSize*2, verticalOffset, boxSize);
		renderer.boxes[3] = new GameBox(boxSize*3, verticalOffset, boxSize);
		renderer.boxes[4] = new GameBox(boxSize*4, verticalOffset, boxSize);
		renderer.boxes[5] = new GameBox(boxSize*5, verticalOffset, boxSize);
		renderer.boxes[6] = new GameBox(boxSize*6, verticalOffset, boxSize);
		renderer.boxes[7] = new GameBox(boxSize*7, verticalOffset, boxSize);
		renderer.boxes[8] = new GameBox(boxSize*8, verticalOffset, boxSize);
		renderer.boxes[9] = new GameBox(boxSize*9, verticalOffset, boxSize);
		renderer.boxes[10] = new GameBox(boxSize*9, verticalOffset + boxSize * 0, boxSize);
		renderer.boxes[11] = new GameBox(boxSize*9, verticalOffset + boxSize * 1, boxSize);
		renderer.boxes[12] = new GameBox(boxSize*9, verticalOffset + boxSize * 2, boxSize);
		renderer.boxes[13] = new GameBox(boxSize*9, verticalOffset + boxSize * 3, boxSize);
		renderer.boxes[14] = new GameBox(boxSize*9, verticalOffset + boxSize * 4, boxSize);
		renderer.boxes[15] = new GameBox(boxSize*9, verticalOffset + boxSize * 5, boxSize);
		renderer.boxes[16] = new GameBox(boxSize*9, verticalOffset + boxSize * 6, boxSize);
		renderer.boxes[17] = new GameBox(boxSize*9, verticalOffset + boxSize * 7, boxSize);
		renderer.boxes[18] = new GameBox(boxSize*9, verticalOffset + boxSize * 8, boxSize);
		renderer.boxes[19] = new GameBox(boxSize*9, verticalOffset + boxSize * 9, boxSize);
		renderer.boxes[20] = new GameBox(boxSize*9, verticalOffset + boxSize * 10, boxSize);
		renderer.boxes[21] = new GameBox(630, 700, boxSize);
		renderer.boxes[22] = new GameBox(560, 700, boxSize);
		renderer.boxes[23] = new GameBox(490, 700, boxSize);
		renderer.boxes[24] = new GameBox(420, 700, boxSize);
		renderer.boxes[25] = new GameBox(350, 700, boxSize);
		renderer.boxes[26] = new GameBox(280, 700, boxSize);
		renderer.boxes[27] = new GameBox(210, 700, boxSize);
		renderer.boxes[28] = new GameBox(140, 700, boxSize);
		renderer.boxes[29] = new GameBox(0, 700, boxSize);
		renderer.boxes[30] = new GameBox(0, 700, boxSize);
		renderer.boxes[31] = new GameBox(0, 632, boxSize);
		renderer.boxes[32] = new GameBox(0, 564, boxSize);
		renderer.boxes[33] = new GameBox(0, 496, boxSize);
		renderer.boxes[34] = new GameBox(0, 428, boxSize);
		renderer.boxes[35] = new GameBox(0, 360, boxSize);
		renderer.boxes[36] = new GameBox(0, 292, boxSize);
		renderer.boxes[37] = new GameBox(0, 224, boxSize);
		renderer.boxes[38] = new GameBox(0, 156, boxSize);
		renderer.boxes[39] = new GameBox(0, 88, boxSize);

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