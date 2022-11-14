/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly.items;
/**
 * A game tile for the Monopoly board
 */

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import edu.ilstu.monopoly.Renderable;

public class GameBox extends Renderable {

    public GameBox(int x, int y, int sideSize) {
        super(x, y);
        this.width = this.height = sideSize;
    }

    @Override
    public void render(Graphics2D g2) {

        g2.fillRect(this.x, this.y, this.width, this.height);

    }

    public void render(Graphics2D g2, String label) {

        g2.fillRect(this.x, this.y, this.width, this.height);

        g2.setColor(Color.WHITE);

        g2.setFont(new Font("Verdana", Font.BOLD, 12));
        FontMetrics fm = g2.getFontMetrics();

        int _y = this.y;
        for(String line : label.split("\n"))
        {
            Rectangle2D bounds = fm.getStringBounds(line, g2);
            g2.setColor(Color.WHITE);
            g2.drawString(line,
            this.x + (int)(.5 * this.width - .5 * bounds.getWidth()),
            _y + fm.getAscent() + (int)(.5 * this.height - .5 * bounds.getHeight()));
            _y += (int)bounds.getHeight() + 4;
        }
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

    private int width;
    private int height;

}
