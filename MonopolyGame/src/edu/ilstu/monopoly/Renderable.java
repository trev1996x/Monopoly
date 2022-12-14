/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly;

/**
 * Abstract class for a renderable object
 */

import java.awt.Point;

public abstract class Renderable implements IRenderable {

	public Renderable() {
		this.x = 0;
		this.y = 0;
		this.zIndex = 0;
	}

	public Renderable(int x, int y) {
		super(); // this will set values to default
		this.x = x;
		this.y = y;
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X location of the renderable object
	 * 
	 * @return int
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Get the Y location of the renderable object
	 * 
	 * @return int
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Get the points of the renderable object
	 * 
	 * @return Point
	 */
	public Point getPoint() {
		return new Point(this.x, this.y);
	}

	protected int x;
	protected int y;
	protected int zIndex; // this is for overlapping elements

}
