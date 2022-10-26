package edu.ilstu.monopoly.items;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.ilstu.monopoly.Renderable;

public class GameBox extends Renderable {

    public GameBox(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics2D g2) {

        g2.fillRect(this.x, this.y, this.width, this.height);

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
