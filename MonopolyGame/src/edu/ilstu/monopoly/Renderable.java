package edu.ilstu.monopoly;

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
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Point getPoint() {
		return new Point(this.x, this.y);
	}
	
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
	public int getZIndex() {
		return this.zIndex;
	}
	
	protected int x;
	protected int y;
	protected int zIndex; // this is for overlapping elements
	
}
