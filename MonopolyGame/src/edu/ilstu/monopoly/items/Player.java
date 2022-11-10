package edu.ilstu.monopoly.items;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import edu.ilstu.monopoly.Renderable;

public class Player extends Renderable {
    public Player(int identifier) {
        System.out.println("Created a new player.");
        this.identifier = identifier;
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
        g2.fillOval(this.owner.getX() + ((int) this.owner.getBounds().getWidth() / 2) - 25,
                this.owner.getY() + ((int) this.owner.getBounds().getHeight() / 2) - 25, 50, 50);

        g2.setColor(Color.WHITE);

        g2.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(Integer.toString(this.identifier),
                this.owner.getX() + 37
                        - ((int) fm.getStringBounds(Integer.toString(this.identifier), g2).getWidth() / 2),
                fm.getDescent() + this.owner.getY() + 37);
    }

    /*
     * Create box that displays the current players stats here.
     * Player number, Money, List of properties they own.
     */

    /**
     * Temporary
     */
    @Override
    public boolean isMouseHovering(Point mousePos) {
        return false;
    }

    public void setGameBox(GameBox newBox) {
        this.owner = newBox;
    }

    public GameBox getGameBox() {
        return this.owner;
    }

    // private int playerNumber = 0;
    public static final int MAX_PLAYERS = 4;

    private GameBox owner;

    private int identifier = -1;
}
