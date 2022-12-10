/**
 * ULID: tmqualt, tjleamo, jfangu
 * Date: 20221114
 */
package edu.ilstu.monopoly;

/**
 * The main class for rendering the game
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.lang.Thread;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import edu.ilstu.monopoly.Game.GameStatus;
import edu.ilstu.monopoly.items.*;

public class Renderer extends Thread {
    @SuppressWarnings("unused")
    private boolean toggle_debug = false; // for DebugBox colors
    public static Image boardBackground = null;
    public static Image wattyBackground = null;
    public static Image triBackground = null;
    public static Image hewittBackground = null;
    public static Image cardinalBackground = null;
    public static Image isupdBackground = null;
    public static Image commChestBackground = null;
    public static Image chanceBackground = null;
    public static Image freeParkingBackground = null;
    public static Image goToJailBackground = null;

    // sets static ints for the prices of the properties
    public static int price1 = 15;
    public static int price2 = 60;
    public static int price3 = 100;
    public static int price4 = 140;
    public static int price5 = 180;
    public static int price6 = 200;
    public static int price7 = 220;
    public static int price8 = 250;
    public static int price9 = 260;
    public static int price10 = 300;
    public static int price11 = 350;
    public static int price12 = 400;
    public static int lotS103Tragedy = 999;

    /**
     * Draw an image to the Graphics 2D
     * 
     * @param g2      Graphics2D
     * @param image   BufferedImage
     * @param imgSrc  Location of the image
     * @param imgSize Dimensions of the image
     * @param x       x Location
     * @param y       y Location
     */
    public void drawImage(Graphics2D g2, BufferedImage image, String imgSrc, Dimension imgSize, int x, int y) {
        g2.drawImage(
                image,
                x, y, Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());
    }

    /**
     * Draw an image to the Graphics 2D
     * 
     * @param g2      Graphics2D
     * @param imgSrc  Location of the image
     * @param imgSize Dimensions of the image
     * @param x       x Location
     * @param y       y Location
     */
    public void drawImage(Graphics2D g2, String imgSrc, Dimension imgSize, int x, int y) {
        try {
            g2.drawImage(
                    ImageIO.read(new File("resources/" + imgSrc)).getScaledInstance(imgSize.width, imgSize.height,
                            Image.SCALE_DEFAULT),
                    x, y, Color.WHITE,
                    this.gameRef.display.getFocusCycleRootAncestor());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Draw an image to the Graphics 2D
     * 
     * @param g2     Graphics2D
     * @param imgSrc Location of the image
     * @param width  Width of the image
     * @param height Height of the image
     * @param x      x Location
     * @param y      y Location
     */
    public void drawImage(Graphics2D g2, String imgSrc, int width, int height, int x, int y) {
        drawImage(g2, imgSrc, new Dimension(width, height), x, y);
    }

    /**
     * Show the splash screen
     * 
     * @param g2 Graphics2D
     */
    public void showSplash(Graphics2D g2) {

        FontMetrics fm;
        g2.setFont(new Font("Arial", Font.BOLD, 56));
        g2.setColor(Color.BLACK);
        fm = g2.getFontMetrics();
        g2.drawString("ISU Monopoly",
                (this.frame.getWidth() / 2) - ((int) fm.getStringBounds("ISU Monopoly", g2).getWidth() / 2) + 5,
                fm.getAscent() + 200 + 5);
        g2.setColor(Color.RED);
        g2.drawString("ISU Monopoly",
                (this.frame.getWidth() / 2) - ((int) fm.getStringBounds("ISU Monopoly", g2).getWidth() / 2),
                fm.getAscent() + 200);

        StartButton startButton = new StartButton((this.frame.getWidth() / 2), 290);

        startButton.setLocation(startButton.getX() - (startButton.getBounds().width / 2), startButton.getY());

        if (startButton.isMouseHovering(mousePos)) {
            startButton.setHover(true);
            if (mouseClicked) {
                this.gameRef.startPlaying();
            }
        } else
            startButton.setHover(false);

        startButton.render(g2);

        InstructionsButton instructionsButton = new InstructionsButton((this.frame.getWidth() / 2), 375);

        instructionsButton.setLocation(instructionsButton.getX() - (instructionsButton.getBounds().width / 2),
                instructionsButton.getY());

        if (instructionsButton.isMouseHovering(mousePos)) {
            instructionsButton.setHover(true);
            if (mouseClicked)
                instructionsButton.showInfoBox((JFrame) this.gameRef.mainWindow);
        } else
            instructionsButton.setHover(false);

        instructionsButton.render(g2);

        CreditsButton creditsButton = new CreditsButton((this.frame.getWidth() / 2), 460);

        creditsButton.setLocation(creditsButton.getX() - (creditsButton.getBounds().width / 2), creditsButton.getY());

        if (creditsButton.isMouseHovering(mousePos)) {
            creditsButton.setHover(true);
            if (mouseClicked)
                creditsButton.showInfoBox((JFrame) this.gameRef.mainWindow);
        } else
            creditsButton.setHover(false);

        creditsButton.render(g2);

    }

    /**
     * Draw text to a Graphics 2D
     * 
     * @param g2       Graphics2D
     * @param text     The text to render
     * @param color    The color of the text
     * @param fontSize The size of the text
     * @param x        x Location
     * @param y        y Location
     */
    public static void drawLabel(Graphics2D g2, String text, Color color, int fontSize, int x, int y) {
        FontMetrics fm;
        g2.setFont(new Font("Arial", Font.BOLD, fontSize));
        g2.setColor(color);
        fm = g2.getFontMetrics();
        g2.drawString(text, x, fm.getAscent() + y);
    }

    /**
     * Show the splash screen
     * 
     * @param g2 Graphics2D
     */
    public void showSetUp(Graphics2D g2) {

        // FontMetrics fm;
        // g2.setFont(new Font("Arial", Font.BOLD, 56));
        // g2.setColor(Color.blue);

        Shape rectangle1 = new Rectangle2D.Double(130.0, 200.0, 160.0, 100.0);
        g2.fill(rectangle1);

    }

    private void visitProperty(Player player, GameBox box, int price) {
        if (box.owner != null && box.owner != player) {
            // Box has an owner and it's not the same player
            // Charge player x amount of money
            String message = "You paid " + price + " for rent.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            int rentMoney = player.getMoney() >= price ? price : player.getMoney();
            player.subMoney(price);
            box.owner.addMoney(rentMoney);
        } 
        else if (box.owner == player); // ignorant conditional
        else if(player.getMoney() >= price) {
            // Ask if player wants to purchase property
            PropertyPurchase pp = new PropertyPurchase();
            pp.showPurchasePropertyDialog(this.gameRef.mainWindow, player, box, price);
            if (pp.confirmation) {
                // Player decided to purchase property
                player.subMoney(price); // Charge player x amount to buy
                // Set owner of box
                box.owner = player;
            }
        }
    }

    private void shouldEliminatePlayer(Player playerToEliminate) {
        if(playerToEliminate.getMoney() < 0)
            // eliminate them
            for(int i = 0; i < this.gameRef.players.size(); i++)
                if(this.gameRef.players.get(i) == playerToEliminate)
                {
                    this.showMessageBox(this.gameRef.mainWindow, playerToEliminate, "You've gone in debt. You've been eliminated.");
                    this.gameRef.players.set(i, null);
                    break;
                }

        int j = 0;
        for(int i = 0; i < this.gameRef.players.size(); i++)
            if(this.gameRef.players.get(i) != null)
                j++;

        if(j < 2) {
            this.showMessageBox(this.gameRef.mainWindow, playerToEliminate, "Game over!!! Player " + this.gameRef.currentPlayer.getIdentifier() + " has won!");
            // Cleaning up references (GC will pick up the rest..)
            this.gameRef.currentPlayer = null;
            for(int i = 0; i < this.gameRef.players.size(); i++)
            this.gameRef.players.set(i, null);
            // Take back to splash screen
            this.gameRef.status = GameStatus.SPLASH_SCREEN;
        }
    }

    private JDialog messageBox;

    public void showMessageBox(JFrame owner, Player player, String message) {

        this.messageBox = new JDialog(owner, "New message!");
        Dimension size = new Dimension(550,100);
        this.messageBox.setPreferredSize(size);
        this.messageBox.setSize(size);
        this.messageBox.setResizable(false);
        this.messageBox.setLocationRelativeTo(null);
        this.messageBox.setModal(true);
        this.messageBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel1 = new JPanel();
        JLabel label = new JLabel(message);

        panel1.add(label);
        this.messageBox.add(panel1);

        this.messageBox.setVisible(true);
    }


    private void communityChest(Player player) {
        // Random class is used to pick a community chest card at random
        Random random = new Random();
        int randChestCard = random.nextInt(10) + 1; // 1..10
        String message;
        switch(randChestCard) {
        case 1:
            message = "Congrats you won $200";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(200);
            break;
        case 2:
            message = "It's your birthday. Collect $10.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(10);
            break;
        case 3:
            message = "It's payday! Collect $200.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(200);
            break;
        case 4:
            message = "You need textbooks. Pay $100.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(100);
            break;
        case 5:
            message = "You buy concert tickets. Pay $50";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(50);
            break;
        case 6:
            message = "You win a giveaway. Collect $69";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(69);
            break;
        case 7:
            message = "You join an RSO. Pay $50 fee.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(50);
            break;
        case 8:
            message = "You go to a Football game. Pay the admission fee $15.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(15);
            break;
        case 9:
            message = "Your roommate paid you for Chipotle. Collect $15";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(15);
            break;
        case 10:
            // fallthrough (don't fw dis)
            // dont put anything here, it goes after default:
        default:
        // case 10:
            message = "You found $20 on the street.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(20);
            break;
        }
    }

    private void chance(Player player) {
        Random random = new Random();
        int randChanceCard = random.nextInt(10) + 1; // 1..10
        String message;
        switch (randChanceCard) {
        case 1:
            message = "Get a drinking ticket. Pay $100";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(100);
            break;
        case 2:
            message = "Get a jaywalking ticket. Pay $50.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(50);
            break;
        case 3:
            message = "You got a parking ticket. Pay $25.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(25);
            break;
        case 4:
            message = "Get hit by Connect Transit bus. Collect $200 settlement fee.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(200);
            break;
        case 5:
            message = "You found a homeless person. Donate $10";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(10);
            break;
        case 6:
            message = "You found an illegal copy of Frozen. Burn it and sell it for $20.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(20);
            break;
        case 7:
            message = "Biden Student Loan Forgiveness. Gain $500.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.addMoney(500);
            break;
        case 8:
            message = "Enemy AC-130J Inbound. Give $300 for a single 105mm Howitzer round.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(300);
            break;
        case 9:
            message = "You bought the Lot S103 Parking Lot pass. How unfortunate... Pay $200.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(200);
            break;
        case 10:
            // fallthrough (don't fw dis)
            // dont put anything here, it goes after default:
        default:
        // case 10:
            message = "You bought an OnlyFriends subscription. Pay $10 / month.";
            this.showMessageBox(this.gameRef.mainWindow, player, message);
            player.subMoney(10);
            break;
        }
    }

    /**
     * Render the game
     * 
     * @param g2 Graphics2D
     */
    public void showGame(Graphics2D g2) {

        // Colors
        final Color DARK_YELLOW = new Color(0xF6BE00);
        final Color DARK_RED = new Color(0x8B0000);
        final Color ActivePlayer = Color.RED;
        final Color InactivePlayer = Color.GRAY;

        if (this.gameRef.currentPlayer == null)
            this.gameRef.currentPlayer = this.gameRef.players.get(0);

        // hardcoded board background
        g2.setColor(Color.GRAY);
        g2.fillRect(horizontalOffset, verticalOffset, boxSize * 11, boxSize * 11); // area = 715x715
        g2.drawImage(Renderer.boardBackground, horizontalOffset, verticalOffset, Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());
        // this.drawImage(g2, "background.png", new Dimension(715,715), 43, 34);

        /**
         * Begins top/north side panel
         * x-axis increments by 70
         */
        g2.setColor(Color.BLACK);
        this.boxes[0].label = "START";
        this.boxes[0].render(g2);
        this.boxes[0].setMethod((box, player) -> player.addMoney(200)); // collect $200 when you pass go

        g2.setColor(DARK_YELLOW); // set color // create object
        this.boxes[1].label = "Schroeder\nHall";
        this.boxes[1].render(g2); // draw object to screen
        this.boxes[1].setMethod((box, player) -> this.visitProperty(player, box, price2));

        g2.setColor(DARK_RED);
        this.boxes[2].label = "Metcalf\nSchool";
        this.boxes[2].render(g2);
        this.boxes[2].setMethod((box, player) -> this.visitProperty(player, box, price2));

        g2.setColor(DARK_YELLOW);
        this.boxes[3].label = "Edwards Hall";
        this.boxes[3].render(g2);
        this.boxes[3].setMethod((box, player) -> this.visitProperty(player, box, price2));

        g2.setColor(DARK_RED);
        this.boxes[4].label = "Community\nChest";
        this.boxes[4].render(g2);
        this.boxes[4].setMethod((box, player) -> this.communityChest(player));
        g2.drawImage(Renderer.commChestBackground, this.boxes[4].getX(), this.boxes[4].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_YELLOW);
        this.boxes[5].label = "Watterson";
        this.boxes[5].render(g2);
        g2.drawImage(Renderer.wattyBackground, this.boxes[5].getX(), this.boxes[5].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());
        this.boxes[5].setMethod((box, player) -> this.visitProperty(player, box, price6));

        g2.setColor(DARK_RED);
        this.boxes[6].label = "Fairchild Hall";
        this.boxes[6].render(g2);
        this.boxes[6].setMethod((box, player) -> this.visitProperty(player, box, price3));

        g2.setColor(DARK_YELLOW);
        this.boxes[7].label = "Chance";
        this.boxes[7].render(g2);
        this.boxes[7].setMethod((box, player) -> this.chance(player));
        g2.drawImage(Renderer.chanceBackground, this.boxes[7].getX(), this.boxes[7].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[8].label = "Cook Hall";
        this.boxes[8].render(g2);
        this.boxes[8].setMethod((box, player) -> this.visitProperty(player, box, price3));

        g2.setColor(DARK_YELLOW);
        this.boxes[9].label = "DeGarmo Hall";
        this.boxes[9].render(g2);
        this.boxes[9].setMethod((box, player) -> this.visitProperty(player, box, price3));

        g2.setColor(Color.BLACK);
        this.boxes[10].label = "Jail";
        this.boxes[10].render(g2);
        g2.drawImage(Renderer.isupdBackground, this.boxes[10].getX(), this.boxes[10].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        /**
         * Begins right/east side panel
         * y-axis increments by 68
         */
        g2.setColor(DARK_RED);
        this.boxes[11].label = "Fell Hall";
        this.boxes[11].render(g2);
        this.boxes[11].setMethod((box, player) -> this.visitProperty(player, box, price4));

        g2.setColor(DARK_YELLOW);
        this.boxes[12].label = "Chance";
        this.boxes[12].render(g2);
        this.boxes[12].setMethod((box, player) -> this.chance(player));
        g2.drawImage(Renderer.chanceBackground, this.boxes[12].getX(), this.boxes[12].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[13].label = "McCormick\nHall";
        this.boxes[13].render(g2);
        this.boxes[13].setMethod((box, player) -> this.visitProperty(player, box, price4));

        g2.setColor(DARK_YELLOW);
        this.boxes[14].label = "CVA";
        this.boxes[14].render(g2);
        this.boxes[14].setMethod((box, player) -> this.visitProperty(player, box, price4));

        g2.setColor(DARK_RED);
        this.boxes[15].label = "Hewett\nManchester";
        this.boxes[15].render(g2);
        g2.drawImage(Renderer.hewittBackground, this.boxes[15].getX(), this.boxes[15].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());
        this.boxes[15].setMethod((box, player) -> this.visitProperty(player, box, price6));

        g2.setColor(DARK_YELLOW);
        this.boxes[16].label = "State Farm\nHall";
        this.boxes[16].render(g2);
        this.boxes[16].setMethod((box, player) -> this.visitProperty(player, box, price5));

        g2.setColor(DARK_RED);
        this.boxes[17].label = "Williams Hall";
        this.boxes[17].render(g2);
        this.boxes[17].setMethod((box, player) -> this.visitProperty(player, box, price5));

        g2.setColor(DARK_YELLOW);
        this.boxes[18].label = "Hovey Hall";
        this.boxes[18].render(g2);
        this.boxes[18].setMethod((box, player) -> this.visitProperty(player, box, price5));

        g2.setColor(DARK_RED);
        this.boxes[19].label = "Moulton Hall";
        this.boxes[19].render(g2);
        this.boxes[19].setMethod((box, player) -> this.visitProperty(player, box, price5));

        g2.setColor(Color.BLACK);
        this.boxes[20].label = "Go to\nJail";
        this.boxes[20].render(g2);
        this.boxes[20].setMethod((box, player) -> {
            player.setGameBox(boxes[10]);
            this.showMessageBox(this.gameRef.mainWindow, player, "You're going to jail!");
        }); // just send them to jail don't hold them
        g2.drawImage(Renderer.goToJailBackground, this.boxes[20].getX(), this.boxes[20].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        /*
         * Beings bottom/south side panel
         * x-axis increments in 70
         */

        g2.setColor(DARK_YELLOW);
        this.boxes[21].label = "Felmley Hall";
        this.boxes[21].render(g2);
        this.boxes[21].setMethod((box, player) -> this.visitProperty(player, box, price7));

        g2.setColor(DARK_RED);
        this.boxes[22].label = "Planetarium";
        this.boxes[22].render(g2);
        this.boxes[22].setMethod((box, player) -> this.visitProperty(player, box, price7));

        g2.setColor(DARK_YELLOW);
        this.boxes[23].label = "Science Lab";
        this.boxes[23].render(g2);
        this.boxes[23].setMethod((box, player) -> this.visitProperty(player, box, price7));

        g2.setColor(DARK_RED);
        this.boxes[24].label = "Milner Library";
        this.boxes[24].render(g2);
        this.boxes[24].setMethod((box, player) -> this.visitProperty(player, box, price7));

        g2.setColor(DARK_YELLOW);
        this.boxes[25].label = "Tri Towers";
        this.boxes[25].render(g2);
        g2.drawImage(Renderer.triBackground, this.boxes[25].getX(), this.boxes[25].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());
        this.boxes[25].setMethod((box, player) -> this.visitProperty(player, box, price6));

        g2.setColor(DARK_RED);
        this.boxes[26].label = "Bone Center";
        this.boxes[26].render(g2);
        this.boxes[26].setMethod((box, player) -> this.visitProperty(player, box, price9));

        g2.setColor(DARK_YELLOW);
        this.boxes[27].label = "Community\nChest";
        this.boxes[27].render(g2);
        this.boxes[27].setMethod((box, player) -> this.communityChest(player));
        g2.drawImage(Renderer.commChestBackground, this.boxes[27].getX(), this.boxes[27].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[28].label = "Uptown";
        this.boxes[28].render(g2);
        this.boxes[28].setMethod((box, player) -> this.visitProperty(player, box, price9));

        g2.setColor(DARK_YELLOW);
        this.boxes[29].label = "Ropp Building";
        this.boxes[29].render(g2);
        this.boxes[29].setMethod((box, player) -> this.visitProperty(player, box, price9));

        g2.setColor(Color.BLACK);
        this.boxes[30].label = "Free Parking";
        this.boxes[30].render(g2);
        g2.drawImage(Renderer.freeParkingBackground, this.boxes[30].getX(), this.boxes[30].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        /*
         * Begins the left/west side panel
         * y-axis increments by 68
         */

        g2.setColor(DARK_YELLOW);
        this.boxes[31].label = "Vidette\nBuilding";
        this.boxes[31].render(g2);
        this.boxes[31].setMethod((box, player) -> this.visitProperty(player, box, price10));

        g2.setColor(DARK_RED);
        this.boxes[32].label = "Hancock\nStadium";
        this.boxes[32].render(g2);
        this.boxes[32].setMethod((box, player) -> this.visitProperty(player, box, price10));

        g2.setColor(DARK_YELLOW);
        this.boxes[33].label = "University\nFarm";
        this.boxes[33].render(g2);
        this.boxes[33].setMethod((box, player) -> this.visitProperty(player, box, price10));

        g2.setColor(DARK_RED);
        this.boxes[34].label = "Watterson\nSubway";
        this.boxes[34].render(g2);
        this.boxes[34].setMethod((box, player) -> this.visitProperty(player, box, price1));

        g2.setColor(DARK_YELLOW);
        this.boxes[35].label = "Cardinal Court";
        this.boxes[35].render(g2);
        g2.drawImage(Renderer.cardinalBackground, this.boxes[35].getX(), this.boxes[35].getY(), Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[36].label = "Lot S103\n:(( </3";
        this.boxes[36].render(g2);
        this.boxes[36].setMethod((box, player) -> this.visitProperty(player, box, lotS103Tragedy));

        g2.setColor(DARK_YELLOW);
        this.boxes[37].label = "Quad";
        this.boxes[37].render(g2);
        this.boxes[37].setMethod((box, player) -> this.visitProperty(player, box, price11));

        g2.setColor(DARK_RED);
        this.boxes[38].label = "Julian Hall";
        this.boxes[38].render(g2);
        this.boxes[38].setMethod((box, player) -> this.visitProperty(player, box, price11));

        g2.setColor(DARK_YELLOW);
        this.boxes[39].label = "Old Union";
        this.boxes[39].render(g2);
        this.boxes[39].setMethod((box, player) -> this.visitProperty(player, box, price12));

        // Identifiers for game boxes

        if (this.diceRoll == null) {
            diceRoll = new DiceRoll(200, 200);
            diceRoll.setLocation((this.gameRef.mainWindow.getWidth() / 2) - (int) diceRoll.getBounds().getWidth() / 2,
                    800 - (int) diceRoll.getBounds().getHeight() / 2);
        }
        this.diceRoll.rollDice();
        if (this.diceRolling && this.diceRoll.done_iterating) {
            // Dice done rolling!!
            // We need to update the game accordingly..
            //

            // Create a reference to the old player
            Player oldPlayer = this.gameRef.currentPlayer;

            // Ensure oldPlayer is the one in the Arraylist
            for (Player iterPlayer : this.gameRef.players) {
                if (oldPlayer == iterPlayer) {
                    oldPlayer = iterPlayer;
                    break;
                }
            }

            Player newPlayer = oldPlayer; // for initializer safety

            // Get the next player in the Arraylist THAT IS NOT NULL
            for (int i = 0; i < this.gameRef.players.size(); i++) {
                if (this.gameRef.players.get(i) == oldPlayer) {
                    for (int j = i + 1, k = 0; k < this.gameRef.players.size(); k++, j++)
                    {
                        // Note: j current iterator
                        if(j >= this.gameRef.players.size()) j = 0;

                        if(this.gameRef.players.get(j) != null) {
                            newPlayer = this.gameRef.players.get(j);
                            break;
                        }
                    }
                    break;
                }
            }

            // Update the current player to the new player
            this.gameRef.currentPlayer = newPlayer;

            // determine how many spaces to move based on the roll
            int forward_move = this.diceRoll.value_1 + this.diceRoll.value_2;

            // associate all GameBoxes with a boxes[]

            for (int i = 0; i < boxes.length; i++) {
                // Find the box the oldPlayer is owned by
                if (boxes[i] == oldPlayer.getGameBox()) {
                    // check weird case, land on Start
                    // This check shouldn't be necessary
                    if(i + forward_move - boxes.length == 0 || i + forward_move == 40)
                    {
                        oldPlayer.setGameBox(boxes[0]);
                        if (boxes[0].hasMethod())
                            boxes[0].runMethod(oldPlayer);
                        this.shouldEliminatePlayer(oldPlayer);
                    }
                    // check INITIAL + forward_move < 40
                    else if (i + forward_move < boxes.length) {
                        // update the game box
                        oldPlayer.setGameBox(boxes[i + forward_move]);
                        if (boxes[i + forward_move].hasMethod())
                            boxes[i + forward_move].runMethod(oldPlayer);
                        this.shouldEliminatePlayer(oldPlayer);
                    } else {
                        // update the game box to 0 + offset
                        oldPlayer.setGameBox(boxes[0 + i + forward_move - (boxes.length - 1)]);
                        if (boxes[0 + i + forward_move - (boxes.length - 1)].hasMethod())
                            boxes[0 + i + forward_move - (boxes.length - 1)].runMethod(oldPlayer);
                        
                        // If they went past Go
                        if(i + forward_move != 0) {
                            assert boxes[0].hasMethod() : "Start Box has no reward system.";
                            boxes[0].runMethod(oldPlayer); // Give $200
                        }

                        this.shouldEliminatePlayer(oldPlayer);
                    }
                    break;
                }
            }

            this.diceRolling = false; // reset
        }
        if (this.diceRoll.isMouseHovering(mousePos)) {
            if (this.mouseClicked) {
                this.diceRoll.startRoll();
                this.diceRolling = true;
            }
        }
        this.diceRoll.render(g2);

        //
        //
        //
        //
        //
        //
        //
        for (Player player : this.gameRef.players)
            if (player != null)
                if (player.getGameBox() == null)
                    player.setGameBox(boxes[0]);

        for (Player player : this.gameRef.players)
            if (player != null) {
                if (this.gameRef.currentPlayer == player)
                    continue;
                else
                    g2.setColor(InactivePlayer);

                player.render(g2);
            }

        g2.setColor(ActivePlayer);
        if(this.gameRef.currentPlayer != null)
            this.gameRef.currentPlayer.render(g2);

        // g2.drawImage(Renderer.boardBackground, 43, 34, Color.WHITE,
        // this.gameRef.display.getFocusCycleRootAncestor());
        for(int i = 0; i < this.gameRef.players.size(); i++)
        {
            if(this.gameRef.players.get(i) != null)
                this.gameRef.players.get(i).renderStats(g2, horizontalOffset + boxSize, (verticalOffset + boxSize) * (i + 1));
        }
        // this.gameRef.currentPlayer.renderStats(g2, horizontalOffset + boxSize, verticalOffset + boxSize); // Note:
                                                                                                          // boxSize is
                                                                                                          // 65
    }

    /**
     * Render the header + FPS counter
     * 
     * @param g2 Graphics2D
     */
    public void drawHeader(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, this.frame.getWidth(), 18);

        g2.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fm = g2.getFontMetrics();
        g2.setColor(Color.WHITE);
        fm = g2.getFontMetrics();
        g2.drawString("Monopoly", 20, fm.getAscent());

        this.framesNow += 1L;

        long now = System.currentTimeMillis();
        if (now - this.lastCounted >= 1_000L) {
            this.lastCounted = now;
            this.framesThen = this.framesNow;
            this.framesNow = 0L;
        }

        g2.drawString(
                Long.toString(this.framesThen) + " FPS", this.frame.getWidth()
                        - (int) fm.getStringBounds(Long.toString(this.framesThen) + " FPS", g2).getWidth() - 20,
                fm.getAscent());

    }

    @Override
    public void run() {

        // preload IO
        if (Renderer.boardBackground == null) {
            try {
                Renderer.boardBackground = ImageIO.read(new File("resources/background.png")).getScaledInstance(
                        boxSize * 11, boxSize * 11,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/background.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.wattyBackground == null) {
            try {
                Renderer.wattyBackground = ImageIO.read(new File("resources/Watty.png")).getScaledInstance(85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/Watty.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.cardinalBackground == null) {
            try {
                Renderer.cardinalBackground = ImageIO.read(new File("resources/CardinalCourt.png")).getScaledInstance(
                        85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/CardinalCourt.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.hewittBackground == null) {
            try {
                Renderer.hewittBackground = ImageIO.read(new File("resources/HewittManchester.png")).getScaledInstance(
                        85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/HewittManchester.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.triBackground == null) {
            try {
                Renderer.triBackground = ImageIO.read(new File("resources/TriTowers.png")).getScaledInstance(85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/TriTowers.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.isupdBackground == null) {
            try {
                Renderer.isupdBackground = ImageIO.read(new File("resources/ISUPD.png")).getScaledInstance(85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/ISUPD.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.chanceBackground == null) {
            try {
                Renderer.chanceBackground = ImageIO.read(new File("resources/Chance.png")).getScaledInstance(85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/Chance.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.commChestBackground == null) {
            try {
                Renderer.commChestBackground = ImageIO.read(new File("resources/CommunityChest.png")).getScaledInstance(
                        85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/CommunityChest.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (Renderer.freeParkingBackground == null) {
            try {
                Renderer.freeParkingBackground = ImageIO.read(new File("resources/FreeParking.png")).getScaledInstance(
                        85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/FreeParking.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        if (Renderer.goToJailBackground == null) {
            try {
                Renderer.goToJailBackground = ImageIO.read(new File("resources/GoToJail.png")).getScaledInstance(85, 85,
                        Image.SCALE_DEFAULT);
                System.out.println("Imported resources/GoToJail.png");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        // ----------

        Graphics2D g2 = this.frame.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.Src);
        g2.setFont(new Font("Arial", Font.BOLD, 18));

        switch (this.gameRef.status) {
            case CURRENTLY_PLAYING:
                this.showGame(g2);
                break;
            // case GAME_SETUP: (DEPRECATED)
            // // this.showSetUp(g2);
            // this.showGame(g2);
            // break;
            case SPLASH_SCREEN:
            default:
                this.showSplash(g2);
                break;
        }

        drawHeader(g2);

        g2.dispose();
    }

    /**
     * Set the thread frame to render
     * 
     * @param frame BufferedImage
     */
    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    /**
     * Set the thread Game reference
     * 
     * @param gameRef Game class reference
     */
    public void setGame(Game gameRef) {
        this.gameRef = gameRef;
    }

    /**
     * Set the thread's cached Mouse Position
     * 
     * @param mousePos Cached Mouse Position
     */
    public void setMousePosition(Point mousePos) {
        this.mousePos = mousePos;
    }

    /**
     * Set the thread's cached Mouse Clicked Status
     * 
     * @param mouseClicked
     */
    public void setMouseClicked(boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }

    private Game gameRef;

    private BufferedImage frame;

    private Point mousePos;

    private boolean mouseClicked;

    private long framesThen = 0L;

    private long framesNow = 0L;

    private long lastCounted = 0L;

    private DiceRoll diceRoll;

    private boolean diceRolling = false;

    public int verticalOffset = 0, horizontalOffset = 0, boxSize = 0;

    protected GameBox boxes[] = new GameBox[40];

}