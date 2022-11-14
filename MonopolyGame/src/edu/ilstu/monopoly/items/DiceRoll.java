/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly.items;
/**
 * The Dice Roll renderable object
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.ilstu.monopoly.Renderable;

public class DiceRoll extends Renderable {

    public DiceRoll(int x, int y) {
        super(x, y);
        this.width = 210;
        this.height = 100;
    }

    @Override
    public void render(Graphics2D g2) {
        if (!this.was_rolled) {
            g2.setColor(Color.BLACK);
            g2.fillRect(this.x, this.y, this.width, this.height);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString("Click to roll!",
                    this.x + (this.width / 2) - ((int) fm.getStringBounds("Click to roll!", g2).getWidth() / 2),
                    fm.getDescent() + this.y + (this.height / 2));
            return;
        }

        if (!this.done_iterating) {
            g2.setColor(Color.WHITE);

            g2.fillRect(this.x, this.y, this.height, this.height);
            g2.fillRect(this.height + this.x + 10, this.y, this.height, this.height);
        } else {
            g2.setColor(Color.BLACK);

            g2.fillRect(this.x, this.y, this.width, this.height);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString("Click to roll!",
                    this.x + (this.width / 2) - ((int) fm.getStringBounds("Click to roll!", g2).getWidth() / 2),
                    fm.getDescent() + this.y + (this.height / 2));
        }

        if (!this.done_iterating)
            g2.setColor(Color.BLACK);
        else
            g2.setColor(Color.WHITE);

        g2.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(Integer.toString(this.value_1),
                this.x + (this.height / 2)
                        - ((int) fm.getStringBounds(Integer.toString(this.value_1), g2).getWidth() / 2),
                fm.getDescent() + this.y + (!this.done_iterating ? (this.height / 2) : this.height - 10));

        g2.drawString(Integer.toString(this.value_2),
                this.x + this.height + 10 + (this.height / 2)
                        - ((int) fm.getStringBounds(Integer.toString(this.value_1), g2).getWidth() / 2),
                fm.getDescent() + this.y + (!this.done_iterating ? (this.height / 2) : this.height - 10));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean isMouseHovering(Point mousePos) {
        Rectangle bounds = this.getBounds();

        boolean onX = false, onY = false;

        // System.out.println(bounds);
        // System.out.println(mousePos);

        if ((mousePos.x >= bounds.x) && (mousePos.x <= bounds.x + bounds.width))
            onX = true;

        if ((mousePos.y >= bounds.y) && (mousePos.y <= bounds.y + bounds.height))
            onY = true;

        if (onX == true && onY == true)
            return true;

        return false;
    }

    public void startRoll() {
        if (!was_rolled)
            was_rolled = true;

        if (done_iterating == true)
            done_iterating = false;
    }

    public void rollDice() {
        if (done_iterating)
            return;

        if (iter > 10) {
            done_iterating = true;
            iter = 0;
            return;
        }

        if (System.currentTimeMillis() - last_iter_time >= 250L) {
            last_iter_time = System.currentTimeMillis();
            value_1 = (int) Math.floor(Math.random() * 6) + 1;
            value_2 = (int) Math.floor(Math.random() * 6) + 1;
            iter++;
        }

    }

    private int width;
    private int height;

    // Animation
    public int value_1 = -1;
    public int value_2 = -1;
    public int iter = 0;
    public long last_iter_time = 0;
    public boolean done_iterating = true;

    private boolean was_rolled = false;

}
