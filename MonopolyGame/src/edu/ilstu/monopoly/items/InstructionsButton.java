/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly.items;

/**
 * The manual button for the splash screen
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JDialog;
import java.awt.FlowLayout;

import edu.ilstu.monopoly.Renderable;

public class InstructionsButton extends Renderable {

	private static Font renderFont = new Font("Arial", Font.BOLD, 36);

	public InstructionsButton(int x, int y) {
		super(x, y);
		this.width = 250;
		this.height = 75;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setFont(InstructionsButton.renderFont);
		FontMetrics fm = g2.getFontMetrics();

		String startStr = "Manual";

		int strWidth = (int) fm.getStringBounds(startStr, g2).getWidth();

		if (drawHoverFactor)
			g2.setColor(Color.RED);
		else
			g2.setColor(Color.BLACK);

		g2.fillRoundRect(this.x, this.y, this.width, this.height, 20, 20);

		g2.setColor(Color.BLACK);
		g2.drawRoundRect(this.x, this.y, this.width, this.height, 20, 20);

		if (drawHoverFactor)
			g2.setColor(Color.BLACK);
		else
			g2.setColor(Color.WHITE);
		g2.drawString(startStr, this.x - (strWidth / 2) + (this.width / 2),
				fm.getDescent() + this.y + (this.height / 2));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	/**
	 * Set the hover status of the button
	 * 
	 * @param isHover
	 */
	public void setHover(boolean isHover) {
		this.drawHoverFactor = isHover;
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

	/**
	 * Show the credits box
	 * 
	 * @param owner The main window (JFrame)
	 */
	public void showInfoBox(JFrame owner) {

		this.infoBox = new JDialog(owner, "Instruction Manual");
		this.infoBox.setModal(true);
		this.infoBox.setAlwaysOnTop(true);
		this.infoBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.infoBox.setSize(775, 500);
		this.infoBox.setResizable(false);

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(0);
		this.infoBox.setLayout(layout);

		// Formats the instruction manual to one String (in HTML format) that can be
		// passed onto the JOptionPane for display
		final String instructionsString = "<html><b>Monopoly user manual</b><br><br>" +
				"<b>Overview:</b><br />"
				+ "Monopoly is a board game for two to four players, in which the main goal is to remain financially solvent<br />"
				+
				"while forcing to collect the funds from other player when they land on your space.<br /><br />"
				+
				"<b>Gameplay and Control:</b><br />" +
				"There are 10 spaces on each side of the board, representing various properties, utilities, a jail, and other<br />"
				+
				"locations around ISU. Each player begins the game with $1000. Each player rolls the dice to move across the<br />"
				+
				"board. Any player who lands on an unclaimed property has the option to buy property, but if the player<br />"
				+
				"lands on a property that is already possessed by another player, that player will be charged rent. If a<br />"
				+
				"player lands on Chance or Community Chest, a card will be drawn, and the event will be displayed in a pop-up box.<br /><br />"
				+
				"<b>Credits:</b><br />"
				+
				"If the player does not have enough money to pay rent or other fees during their turn, they may choose to sell<br />"
				+
				"the property at half of its value.<br /><br />"
				+
				"<b>Winning:</b><br />"
				+
				"If any player runs out of money, the game will come to an end. The player with the most money is declared the winner.<br /><<br />"
				+
				"You can also choose to end the game at any anytime by selecting the “End Game” button.</html>";
		JOptionPane instrucOptionPane = new JOptionPane(instructionsString);
		this.infoBox.add(instrucOptionPane);

		this.infoBox.setLocationRelativeTo(null);
		this.infoBox.setVisible(true);

	}

	private int width;
	private int height;
	private boolean drawHoverFactor;

	private JDialog infoBox;

}
