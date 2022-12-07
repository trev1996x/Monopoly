/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly.items;

/**
 * A renderable object representing a player of the game
 */

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
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

        g2.setColor(g2.getColor().darker());

        g2.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(Integer.toString(this.identifier),
                this.owner.getX() + 42 // hardset value
                        - ((int) fm.getStringBounds(Integer.toString(this.identifier), g2).getWidth() / 2),
                fm.getDescent() + this.owner.getY() + 45); // hardset value
    }

    /**
     * Temporary
     */
    @Override
    public boolean isMouseHovering(Point mousePos) {
        return false;
    }

    /**
     * Set the game box
     * 
     * @param newBox new Game box
     */
    public void setGameBox(GameBox newBox) {
        this.owner = newBox;
    }

    /*
     * Get the game box
     * 
     * @return Game box
     */
    public GameBox getGameBox() {
        return this.owner;
    }

    // private int playerNumber = 0;
    public static final int MAX_PLAYERS = 4;

    private GameBox owner;

    /**
     * Draw the player stats to screen
     * 
     * @param g2 Graphics2D
     * @param x  x Location
     * @param y  y Location
     */
    public void renderStats(Graphics2D g2, int x, int y) {
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g2.getFontMetrics();

        String stats = this.getStats();
        for (String line : stats.split("\n")) {
            Rectangle2D bounds = fm.getStringBounds(line, g2);
            g2.setColor(Color.WHITE);
            g2.fillRect(x, y, (int) bounds.getWidth() + 4, (int) bounds.getHeight() + 4);
            g2.setColor(Color.BLACK);
            g2.drawString(line,
                    x + 2,
                    y + fm.getAscent() + 2);
            y += (int) bounds.getHeight() + 4;
        }
    }

    /**
     * Get the money
     * 
     * @return int
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Set the money
     * 
     * @param money int
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Add to money
     * 
     * @param toAdd int
     */
    public void addMoney(int toAdd) {
        this.money += toAdd;
    }

    /**
     * Subtract from money
     * 
     * @param toRem int
     */
    public void subMoney(int toRem) {
        this.money -= toRem;
    }

    /**
     * To change player stats, use this method!
     * 
     * @return Player Stats in String form
     */
    public String getStats() {
        return new String(
                "Player " + Integer.toString(this.identifier) + " stats: \nMoney: $" + this.money);
    }

    private int money = 1_000;

    private int identifier = -1;
}
