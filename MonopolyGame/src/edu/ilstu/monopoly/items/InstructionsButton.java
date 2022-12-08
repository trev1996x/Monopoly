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
import javax.swing.JDialog;
import javax.swing.JLabel;
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
		// int strHeight = (int)fm.getStringBounds(startStr, g2).getHeight();

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

	/**
	 * Show the credits box
	 * 
	 * @param owner The main window (JFrame)
	 */
	public void showInfoBox(JFrame owner) {
		// if(this.infoBox != null && this.infoBox.isDisplayable()) return;

		this.infoBox = new JDialog(owner, "Instruction Manual");

		this.infoBox.setModal(true);
		this.infoBox.setAlwaysOnTop(true);
		this.infoBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.infoBox.setSize(800, 800);

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(0);
		this.infoBox.setLayout(layout);

		JLabel howTo = new JLabel("How to play ISU Monopoly:");
		JLabel label0 = new JLabel("Monopoly user manual ");
		this.infoBox.add(label0);
		JLabel label1 = new JLabel("1.	Overview ");
		this.infoBox.add(label1);
		JLabel label2 = new JLabel(
				"Monopoly is a board game for two to four players, in which the main goal is to remain financially solvent while forcing to collect the funds from other player when they land on your space.");
		this.infoBox.add(label2);
		JLabel label3 = new JLabel("");
		this.infoBox.add(label3);
		JLabel label4 = new JLabel("2.	Gameplay and control ");
		this.infoBox.add(label4);
		JLabel label5 = new JLabel(
				"Ten little rectangles, one on each side of the square board, representing various properties, utilities, a jail, and other locations and occasions. Each player begins the game with a certain amount of fake money, and they  move across the board based on the results of a pair of dice. Any player who lands on an unclaimed property has the option to buy property, but if the player lands on a property that is already possessed by another player, that player will be charged rent.");
		this.infoBox.add(label5);
		JLabel label6 = new JLabel("");
		this.infoBox.add(label6);
		JLabel label7 = new JLabel("3.	Credits ");
		this.infoBox.add(label7);
		JLabel label8 = new JLabel(
				"At the beginning of the game, each players are given the same amount of funds, as long as the running across the board, the player can earn or lose the funds, if the player do not have enough money to pay rent or other obligations during they turn, they may choose to sell the property.");
		this.infoBox.add(label8);
		JLabel label9 = new JLabel("");
		this.infoBox.add(label9);
		JLabel label10 = new JLabel("4.	Winner ");
		this.infoBox.add(label10);
		JLabel label11 = new JLabel(
				"A player keeps running around the board until they run out of funds. A player who run out the funds results in removal from the game and considered as loser. The winner is the last player remaining present on the board. You can also choose to end the game at any anytime by calculating the sum of each player, the player with most funds is considered as the winner.");
		this.infoBox.add(label11);
		this.infoBox.add(howTo);

		this.infoBox.setLocationRelativeTo(null);
		this.infoBox.setVisible(true);

	}

	private int width;
	private int height;
	private boolean drawHoverFactor;

	private JDialog infoBox;

}
