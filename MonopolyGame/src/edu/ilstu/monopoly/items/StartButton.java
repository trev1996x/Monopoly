package edu.ilstu.monopoly.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.ilstu.monopoly.Renderable;

public class StartButton extends Renderable {
	
    private static Font renderFont = new Font("Arial", Font.BOLD, 36);

	public StartButton(int x, int y) {
		super(x,y);
		this.width = 200;
		this.height = 75;
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setFont(StartButton.renderFont);
		FontMetrics fm = g2.getFontMetrics();

        String startStr = "Start game";

        int strWidth = (int)fm.getStringBounds(startStr, g2).getWidth();
        //int strHeight = (int)fm.getStringBounds(startStr, g2).getHeight();

        if(drawHoverFactor) g2.setColor(Color.RED);
        else g2.setColor(Color.BLACK);

        g2.fillRoundRect(this.x, this.y, this.width, this.height, 20, 20);

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(this.x, this.y, this.width, this.height, 20, 20);

		if(drawHoverFactor) g2.setColor(Color.BLACK);
        else g2.setColor(Color.WHITE);
		g2.drawString(startStr, this.x - (strWidth / 2) + (this.width / 2), fm.getDescent() + this.y + (this.height / 2));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	/**
	 * Set the hover status of the button
	 * @param isHover
	 */
	public void setHover(boolean isHover) {
		this.drawHoverFactor = isHover;
	}
	
	@Override
	public boolean isMouseHovering(Point mousePos) {
		Rectangle bounds = this.getBounds();
		
		boolean onX = false, onY = false;

//		System.out.println(bounds);
//		System.out.println(mousePos);
		
		if((mousePos.x >= bounds.x) && (mousePos.x <= bounds.x + bounds.width))
			onX = true;
		
		if((mousePos.y >= bounds.y) && (mousePos.y <= bounds.y + bounds.height))
			onY = true;
		
		if(onX == true && onY == true) return true;
		
		return false;
	}
	
	private int width;
	private int height;
	private boolean drawHoverFactor;
	
}
