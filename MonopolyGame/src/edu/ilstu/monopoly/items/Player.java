package edu.ilstu.monopoly.items;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.ilstu.monopoly.Renderable;

public class Player extends Renderable {
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

    }

    /**
     * Temporary
     */
    @Override
    public boolean isMouseHovering(Point mousePos) {
        return false;
    }

    private int playerNumber = 0;
    public static final int MAX_PLAYERS = 4;
}
