/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly;

/**
 * The JPanel that the game will render to
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel /* implements MouseInputListener */ {
	private static final long serialVersionUID = 8063771689965221597L; // auto-gen by Eclipse

	/**
	 * Initialize the JPanel
	 */
	GamePanel() {
		super();

		this.mousePos = new Point(0, 0);

		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					mouseClicked = true;
				}
			}
		});

		super.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mousePos = e.getPoint();
			}
		});

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the frame given to the JPanel
		g.drawImage(this.frame, super.getX(), super.getY(), Color.WHITE, getFocusCycleRootAncestor());
		g.dispose();
	}

	/**
	 * Configure the frame to display for the JPanel
	 * 
	 * @param newFrame BufferedImage
	 */
	public void setFrame(BufferedImage newFrame) {
		this.frame = newFrame;
		super.repaint();
	}

	/**
	 * Get the mouse position on the JPanel
	 * 
	 * @return Mouse position
	 */
	public Point getMousePosition() {
		return this.mousePos;
	}

	/**
	 * Check if the mouse was clicked
	 * 
	 * @return Mouse clicked
	 */
	public boolean getMouseClicked() {
		return this.mouseClicked;
	}

	/**
	 * Reset the mouse status
	 */
	public void resetMouseClicked() {
		this.mouseClicked = false;
	}

	private BufferedImage frame;
	private Point mousePos;
	private boolean mouseClicked = false;
}
