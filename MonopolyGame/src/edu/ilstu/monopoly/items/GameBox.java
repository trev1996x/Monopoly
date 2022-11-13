package edu.ilstu.monopoly.items;

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

        Rectangle2D strBounds = fm.getStringBounds(label, g2);

        g2.drawString(label, this.x + (int)(.5 * this.width - .5 * strBounds.getWidth()), y + fm.getAscent() + (int)(.5 * this.height - .5 * strBounds.getHeight()));
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
