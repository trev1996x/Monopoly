package edu.ilstu.monopoly;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.lang.Thread;
import javax.swing.JFrame;

import edu.ilstu.monopoly.items.*;

class Renderer extends Thread {
	private boolean toggle_debug = false; // for DebugBox colors

	/**
	 * Show the splash screen
	 * @param g2 Graphics2D
	 */
	public void showSplash(Graphics2D g2) {
		FontMetrics fm;
		g2.setFont(new Font("Arial", Font.BOLD, 56));
		g2.setColor(Color.BLACK);
		fm = g2.getFontMetrics();
		g2.drawString("ISU Monopoly", (this.frame.getWidth() / 2) - ((int)fm.getStringBounds("ISU Monopoly", g2).getWidth() / 2) + 5, fm.getAscent() + 200 + 5);
		g2.setColor(Color.RED);
		g2.drawString("ISU Monopoly", (this.frame.getWidth() / 2) - ((int)fm.getStringBounds("ISU Monopoly", g2).getWidth() / 2), fm.getAscent() + 200);

		StartButton startButton = new StartButton((this.frame.getWidth() / 2), 275);

		startButton.setLocation(startButton.getX() - (startButton.getBounds().width / 2), startButton.getY());

		if(startButton.isMouseHovering(mousePos)) {
			startButton.setHover(true);
			if(mouseClicked) this.gameRef.startPlaying();
		}
		else startButton.setHover(false);

		startButton.render(g2);

		CreditsButton creditsButton = new CreditsButton((this.frame.getWidth() / 2), 355);

		creditsButton.setLocation(creditsButton.getX() - (creditsButton.getBounds().width / 2), creditsButton.getY());

		if(creditsButton.isMouseHovering(mousePos)) {
			creditsButton.setHover(true);
			if(mouseClicked) creditsButton.showInfoBox((JFrame)this.gameRef.mainWindow);
		}
		else creditsButton.setHover(false);

		creditsButton.render(g2);

		}

	/**
	 * Render the game
	 * @param g2 Graphics2D
	 */
	public void showGame(Graphics2D g2) {
		FontMetrics fm;

		DebugBox box = new DebugBox(0, 100);

		if (box.isMouseHovering(this.mousePos) && this.mouseClicked) {
			toggle_debug = !toggle_debug;
		}

		box.setColor(toggle_debug ? Color.GREEN : Color.RED);

		box.render(g2);

		int frameWidth = this.frame.getWidth(),
				frameHeight = this.frame.getHeight();

		/**
		 * Begins top/north side panel
		 */
		g2.setColor(Color.BLACK);
		GameBox box1 = new GameBox(0, 20, frameWidth / 10, frameHeight / 10);
		box1.render(g2);

		g2.setColor(Color.BLUE); // set color
		GameBox box2 = new GameBox(80, 20, frameWidth / 10, frameHeight / 10); // create object
		box2.render(g2); // draw object to screen

		g2.setColor(Color.RED);
		GameBox box3 = new GameBox(160, 20, frameWidth / 10, frameHeight / 10);
		box3.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box4 = new GameBox(240, 20, frameWidth / 10, frameHeight / 10);
		box4.render(g2);

		g2.setColor(Color.RED);
		GameBox box5 = new GameBox(320, 20, frameWidth / 10, frameHeight / 10);
		box5.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box6 = new GameBox(400, 20, frameWidth / 10, frameHeight / 10);
		box6.render(g2);

		g2.setColor(Color.RED);
		GameBox box7 = new GameBox(480, 20, frameWidth / 10, frameHeight / 10);
		box7.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box8 = new GameBox(560, 20, frameWidth / 10, frameHeight / 10);
		box8.render(g2);

		g2.setColor(Color.RED);
		GameBox box9 = new GameBox(640, 20, frameWidth / 10, frameHeight / 10);
		box9.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box10 = new GameBox(720, 20, frameWidth / 10, frameHeight / 10);
		box10.render(g2);

		/**
		 * Begins right/east side panel
		 */
		g2.setColor(Color.BLACK);
		GameBox box11 = new GameBox(800, 20, frameWidth / 10, frameHeight / 10);
		box11.render(g2);

		g2.setColor(Color.RED);
		GameBox box12 = new GameBox(800, 20, frameWidth / 10, frameHeight / 10);
		box12.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box13 = new GameBox(800, 100, frameWidth / 10, frameHeight / 10);
		box13.render(g2);

		g2.setColor(Color.RED);
		GameBox box14 = new GameBox(800, 180, frameWidth / 10, frameHeight / 10);
		box14.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box15 = new GameBox(800, 260, frameWidth / 10, frameHeight / 10);
		box15.render(g2);

		g2.setColor(Color.RED);
		GameBox box16 = new GameBox(800, 340, frameWidth / 10, frameHeight / 10);
		box16.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box17 = new GameBox(800, 420, frameWidth / 10, frameHeight / 10);
		box17.render(g2);

		g2.setColor(Color.RED);
		GameBox box18 = new GameBox(800, 500, frameWidth / 10, frameHeight / 10);
		box18.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box19 = new GameBox(800, 580, frameWidth / 10, frameHeight / 10);
		box19.render(g2);

		g2.setColor(Color.RED);
		GameBox box20 = new GameBox(800, 640, frameWidth / 10, frameHeight / 10);
		box20.render(g2);

		g2.setColor(Color.BLUE);
		GameBox box21 = new GameBox(800, 720, frameWidth / 10, frameHeight / 10);
		box21.render(g2);

		//
		//
		//
		//
		//
		//
		//
	}

	@Override
	public void run() {
		Graphics2D g2 = this.frame.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setComposite(AlphaComposite.Src);
		g2.setFont(new Font("Arial", Font.BOLD, 18));

		if(!this.gameRef.isPlaying)
			showSplash(g2); // Show the splash screen
		else showGame(g2); // Show the game

		FontMetrics fm = g2.getFontMetrics();
		g2.setColor(Color.GREEN);
		fm = g2.getFontMetrics();
		g2.drawString("Monopoly", 20, fm.getAscent());

		g2.dispose();
	}

	/**
	 * Set the thread frame to render
	 * 
	 * @param frame BufferedImage
	 */
	public void setFrame(BufferedImage frame) {
		this.frame = frame;
	}

	/**
	 * Set the thread Game reference
	 * 
	 * @param gameRef Game class reference
	 */
	public void setGame(Game gameRef) {
		this.gameRef = gameRef;
	}

	/**
	 * Set the thread's cached Mouse Position
	 * 
	 * @param mousePos Cached Mouse Position
	 */
	public void setMousePosition(Point mousePos) {
		this.mousePos = mousePos;
	}

	/**
	 * Set the thread's cached Mouse Clicked Status
	 * @param mouseClicked
	 */
	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	@SuppressWarnings("unused")
	private Game gameRef;

	private BufferedImage frame;

	private Point mousePos;

	private boolean mouseClicked;

	private long framesCounted = 0L;

	private long lastCounted = 0L;
}

public class Game {

	/**
	 * Very basic constructor. Initialize JFrame and run the "run" method.
	 */
	public Game() {
		this.mainWindow = new Window("ISU Monopoly");
		this.display = mainWindow.getGamePanel();

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
		this.frontFrame = new BufferedImage(this.display.getWidth(), this.display.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.selectedFrame = this.frontFrame;

		// Initialize the renderer (thread)
		Renderer renderer = new Renderer();
		renderer.setGame(this);

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

			// TODO: Handle user input

			// Renderer finished, join context to keep going
			renderer.join();
		}
	}

	/**
	 * Start the game
	 */
	public void startPlaying() {
		this.isPlaying = true;
	}

	/**
	 * Stop the game
	 */
	public void stopPlaying() {
		this.isPlaying = false;
	}

	// By making these protected, they are accessible in the Renderer (Thread)
	protected Window mainWindow;
	protected GamePanel display;

	private BufferedImage selectedFrame;
	private BufferedImage frontFrame;
	private BufferedImage backFrame;

	protected boolean isPlaying = false;
}