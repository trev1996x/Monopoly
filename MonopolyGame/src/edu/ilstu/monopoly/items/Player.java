package edu.ilstu.monopoly.items;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;

import edu.ilstu.monopoly.Renderable;

public class Player extends Renderable {
    public Player() {
        System.out.println("Created a new player.");
    }
    /**
     * Temporary
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle();
    }

    /**
     * Temporary
     */
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(100, 100, 50, 50);
    }

    /**
     * Temporary
     */
    @Override
    public boolean isMouseHovering(Point mousePos) {
        return false;
    }

    // private int playerNumber = 0;
    public static final int MAX_PLAYERS = 4;
}
