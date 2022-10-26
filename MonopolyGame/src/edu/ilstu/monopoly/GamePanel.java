package edu.ilstu.monopoly;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 8063771689965221597L; // auto-gen by Eclipse

	/**
	 * Initialize the JPanel
	 */
	GamePanel() {
		super();

		this.mousePos = new Point(0, 0);

		super.addMouseMotionListener(new MouseMotionAdapter() { // good code isn't always good code lol
			@Override
			public void mouseMoved(MouseEvent e) {
				mousePos = e.getPoint(); // do not precede with "this." as "this" will refer to the adapter
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

	private BufferedImage frame;
	private Point mousePos;
}
