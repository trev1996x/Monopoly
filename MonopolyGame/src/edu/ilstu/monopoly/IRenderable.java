/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly;
/**
 * Abstract interface for a renderable object
 */

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public interface IRenderable {
	/**
	 * Set the location of the renderable object
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	void setLocation(int x, int y);

	/**
	 * Render the renderable object
	 * @param g2 Graphics2D
	 */
	void render(Graphics2D g2);

	/**
	 * Get the bounds of the object being rendered
	 * @return Rectangle pertaining to rendered object
	 */
	Rectangle getBounds();

	/**
	 * Check if the mouse is hovering over the renderable object.
	 * @param mousePos Mouse Position
	 * @return True/False
	 */
	public boolean isMouseHovering(Point mousePos);
}
