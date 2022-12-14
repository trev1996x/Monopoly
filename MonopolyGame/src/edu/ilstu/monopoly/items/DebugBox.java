/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly.items;

/**
 * A renderable object intended for testing purposes
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.ilstu.monopoly.Renderable;

public class DebugBox extends Renderable {

	public DebugBox(int x, int y) {
		super(x, y);
		this.width = 100;
		this.height = 100;
		this.renderColor = Color.RED;
	}

	@Override
	public void render(Graphics2D g2) {

		g2.setColor(this.renderColor);
		g2.fillRect(this.x, this.y, this.width, this.height);

		g2.setColor(this.renderColor.darker());
		g2.setFont(new Font("Arial", Font.BOLD, 18));
		FontMetrics fm = g2.getFontMetrics();

		g2.drawString("Debug Box", this.x, fm.getDescent() + this.y + (this.height / 2));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public void setColor(Color color) {
		this.renderColor = color;
	}

	@Override
	public boolean isMouseHovering(Point mousePos) {
		Rectangle bounds = this.getBounds();

		boolean onX = false, onY = false;

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
	private Color renderColor;

}
