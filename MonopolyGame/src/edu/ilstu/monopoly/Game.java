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

import edu.ilstu.monopoly.items.*;

// If you want to implement keyboard + clicking,
// you will need to implement a keyboard + mouse cache.
// these should be cleared only on new frames!!!
// will require some annoying work, but that should be the worst of it

class Renderer extends Thread {
	@Override
	public void run() {
		Graphics2D g2 = this.frame.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setComposite(AlphaComposite.Src);
		g2.setFont(new Font("Arial", Font.BOLD, 18));
		FontMetrics fm = g2.getFontMetrics();

		DebugBox box = new DebugBox(0, 100);

		if (box.isMouseHovering(this.mousePos)) {
			box.setColor(Color.GREEN);
			if (mouseClicked)
				System.out.println("Clicked in the DEBUG BOX!!!");
		} else {
			box.setColor(Color.RED);
		}

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
		g2.setColor(Color.GREEN);

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

	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	@SuppressWarnings("unused")
	private Game gameRef;

	private BufferedImage frame;

	private Point mousePos;

	private boolean mouseClicked;

}

public class Game {

	/**
	 * Very basic constructor. Initialize JFrame and run the "run" method.
	 */
	public Game() {
		Window mainWindow = new Window("ISU Monopoly");

		try {
			this.run(mainWindow.getGamePanel());
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
	public void run(GamePanel display) throws InterruptedException {
		// Set the front (first) frame to be rendered
		this.frontFrame = new BufferedImage(display.getWidth(), display.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.selectedFrame = this.frontFrame;

		// Initialize the renderer (thread)
		Renderer renderer = new Renderer();
		renderer.setGame(this);

		// While the JPanel can display something, keep rendering.
		while (display.isDisplayable()) {

			// Cache the Mouse Position from the JPanel
			renderer.setMousePosition(display.getMousePosition());
			renderer.setMouseClicked(display.getMouseClicked()); // get cached click action
			display.resetMouseClicked(); // reset action

			if (this.selectedFrame == this.frontFrame) {
				// Show first frame on JPanel
				display.setFrame(this.selectedFrame);
				// Render the other frame
				this.backFrame = new BufferedImage(display.getWidth(), display.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				this.selectedFrame = this.backFrame;
				renderer.setFrame(this.selectedFrame);
				renderer.run();
			} else {
				// Show second frame on JPanel
				display.setFrame(this.selectedFrame);
				// Render the other frame
				this.frontFrame = new BufferedImage(display.getWidth(), display.getHeight(),
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

	private BufferedImage selectedFrame;
	private BufferedImage frontFrame;
	private BufferedImage backFrame;

}