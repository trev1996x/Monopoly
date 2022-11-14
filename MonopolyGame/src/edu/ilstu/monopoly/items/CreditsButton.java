/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly.items;
/**
 * The credits button for the splash screen
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import edu.ilstu.monopoly.Renderable;

public class CreditsButton extends Renderable {
	
    private static Font renderFont = new Font("Arial", Font.BOLD, 36);

	public CreditsButton(int x, int y) {
		super(x,y);
		this.width = 200;
		this.height = 75;
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setFont(CreditsButton.renderFont);
		FontMetrics fm = g2.getFontMetrics();

        String startStr = "Credits";

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

	/**
	 * Show the credits box
	 * @param owner The main window (JFrame)
	 */
    public void showInfoBox(JFrame owner) {
        // if(this.infoBox != null && this.infoBox.isDisplayable()) return;

        this.infoBox = new JDialog(owner, "Credits");

        this.infoBox.setModal(true);
        this.infoBox.setAlwaysOnTop(true);
        this.infoBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.infoBox.setSize(350, 150);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(0);
        this.infoBox.setLayout(layout);

        JLabel credits = new JLabel("Created for IT 178.");
        JLabel copyright = new JLabel("(C) 2022 Ty Qualters, Trevor Leamon, and Joel Fangu.");

        this.infoBox.add(credits);
        this.infoBox.add(copyright);

        this.infoBox.setLocationRelativeTo(null);
        this.infoBox.setVisible(true);

    }
	
	private int width;
	private int height;
	private boolean drawHoverFactor;

    private JDialog infoBox;
	
}
