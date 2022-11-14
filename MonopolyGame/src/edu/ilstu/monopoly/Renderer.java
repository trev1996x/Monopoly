package edu.ilstu.monopoly;

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
import java.io.File;
import java.io.IOException;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.imageio.ImageIO;

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

    public void drawImage(Graphics2D g2, BufferedImage image, String imgSrc, Dimension imgSize, int x, int y) {
        g2.drawImage(
                image,
                x, y, Color.WHITE,
                this.gameRef.display.getFocusCycleRootAncestor());
    }

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
            if (mouseClicked)
            {
                this.gameRef.startPlaying();
            }
        } else
            startButton.setHover(false);

            startButton.render(g2);

            InstructionsButton instructionsButton = new InstructionsButton((this.frame.getWidth() / 2), 375);
    
            instructionsButton.setLocation(instructionsButton.getX() - (instructionsButton.getBounds().width / 2), instructionsButton.getY());
    
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

        // DebugBox box = new DebugBox(0, 100);

        // if (box.isMouseHovering(this.mousePos) && this.mouseClicked) {
        // toggle_debug = !toggle_debug;
        // }

        // box.setColor(toggle_debug ? Color.GREEN : Color.RED);

        // box.render(g2);

        // int frameWidth = this.frame.getWidth(),
        //         frameHeight = this.frame.getHeight() - 20;

        // hardcoded board background
        g2.setColor(Color.GRAY);
        g2.fillRect(horizontalOffset, verticalOffset, boxSize * 11, boxSize * 11); // area = 715x715
        g2.drawImage(Renderer.boardBackground, horizontalOffset, verticalOffset, Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());
        // this.drawImage(g2, "background.png", new Dimension(715,715), 43, 34);

        /**
         * Begins top/north side panel
         * x-axis increments by 70
         */
        g2.setColor(Color.BLACK);
        this.boxes[0].render(g2, "START");

        g2.setColor(DARK_YELLOW); // set color // create object
        this.boxes[1].render(g2, "Schroeder\nHall"); // draw object to screen

        g2.setColor(DARK_RED);
        this.boxes[2].render(g2, "Metcalf\nSchool");

        g2.setColor(DARK_YELLOW);
        this.boxes[3].render(g2, "Edwards Hall");

        g2.setColor(DARK_RED);
        this.boxes[4].render(g2, "Community\nChest");
        g2.drawImage(Renderer.commChestBackground, this.boxes[4].getX(), this.boxes[4].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());


        g2.setColor(DARK_YELLOW);
        this.boxes[5].render(g2, "Watterson");
        g2.drawImage(Renderer.wattyBackground, this.boxes[5].getX(), this.boxes[5].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[6].render(g2, "Fairchild Hall");

        g2.setColor(DARK_YELLOW);
        this.boxes[7].render(g2, "Chance");
        g2.drawImage(Renderer.chanceBackground, this.boxes[7].getX(), this.boxes[7].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());


        g2.setColor(DARK_RED);
        this.boxes[8].render(g2, "Cook Hall");

        g2.setColor(DARK_YELLOW);
        this.boxes[9].render(g2, "DeGarmo Hall");

        g2.setColor(Color.BLACK);
        this.boxes[10].render(g2, "Jail");
        g2.drawImage(Renderer.isupdBackground, this.boxes[10].getX(), this.boxes[10].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());

        /**
         * Begins right/east side panel
         * y-axis increments by 68
         */

        g2.setColor(DARK_RED);
        this.boxes[11].render(g2, "Fell Hall");

        g2.setColor(DARK_YELLOW);
        this.boxes[12].render(g2, "Chance");
        g2.drawImage(Renderer.chanceBackground, this.boxes[12].getX(), this.boxes[12].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[13].render(g2, "McCormick\nHall");

        g2.setColor(DARK_YELLOW);
        this.boxes[14].render(g2, "CVA");

        g2.setColor(DARK_RED);
        this.boxes[15].render(g2, "Hewett\nManchester");
        g2.drawImage(Renderer.hewittBackground, this.boxes[15].getX(), this.boxes[15].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_YELLOW);
        this.boxes[16].render(g2, "State Farm\nHall");

        g2.setColor(DARK_RED);
        this.boxes[17].render(g2, "Williams Hall");

        g2.setColor(DARK_YELLOW);
        this.boxes[18].render(g2, "Hovey Hall");

        g2.setColor(DARK_RED);
        this.boxes[19].render(g2, "Moulton Hall");

        g2.setColor(Color.BLACK);
        this.boxes[20].render(g2, "Go to\nJail");
        g2.drawImage(Renderer.goToJailBackground, this.boxes[20].getX(), this.boxes[20].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());


        /*
         * Beings bottom/south side panel
         * x-axis increments in 70
         */

        g2.setColor(DARK_YELLOW);
        this.boxes[21].render(g2, "Felmley Hall");

        g2.setColor(DARK_RED);
        this.boxes[22].render(g2, "Planetarium");

        g2.setColor(DARK_YELLOW);
        this.boxes[23].render(g2, "Science Lab");

        g2.setColor(DARK_RED);
        this.boxes[24].render(g2, "Milner Library");

        g2.setColor(DARK_YELLOW);
        this.boxes[25].render(g2, "Tri Towers");
        g2.drawImage(Renderer.triBackground, this.boxes[25].getX(), this.boxes[25].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[26].render(g2, "Bone Center");

        g2.setColor(DARK_YELLOW);
        this.boxes[27].render(g2, "Community\nChest");
        g2.drawImage(Renderer.commChestBackground, this.boxes[27].getX(), this.boxes[27].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());


        g2.setColor(DARK_RED);
        this.boxes[28].render(g2, "Uptown");

        g2.setColor(DARK_YELLOW);
        this.boxes[29].render(g2, "Ropp Building");

        g2.setColor(Color.BLACK);
        this.boxes[30].render(g2, "Free Parking");
        g2.drawImage(Renderer.freeParkingBackground, this.boxes[30].getX(), this.boxes[30].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());


        /*
         * Begins the left/west side panel
         * y-axis increments by 68
         */

        g2.setColor(DARK_YELLOW);
        this.boxes[31].render(g2, "Vidette\nBuilding");

        g2.setColor(DARK_RED);
        this.boxes[32].render(g2, "Hancock\nStadium");

        g2.setColor(DARK_YELLOW);
        this.boxes[33].render(g2, "University\nFarm");

        g2.setColor(DARK_RED);
        this.boxes[34].render(g2, "Watterson\nSubway");

        g2.setColor(DARK_YELLOW);
        this.boxes[35].render(g2, "Cardinal Court");
        g2.drawImage(Renderer.cardinalBackground, this.boxes[35].getX(), this.boxes[35].getY(), Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());

        g2.setColor(DARK_RED);
        this.boxes[36].render(g2, "Lot S103\n:(( </3");

        g2.setColor(DARK_YELLOW);
        this.boxes[37].render(g2, "Quad");

        g2.setColor(DARK_RED);
        this.boxes[38].render(g2, "Julian Hall");

        g2.setColor(DARK_YELLOW);
        this.boxes[39].render(g2, "Old Union");

        // Identifiers for game boxes

        if (this.diceRoll == null)
        {
            diceRoll = new DiceRoll(200, 200);
            diceRoll.setLocation((this.gameRef.mainWindow.getWidth() / 2) - (int)diceRoll.getBounds().getWidth() / 2, 800 - (int)diceRoll.getBounds().getHeight() / 2);
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

            // Get the next player in the Arraylist
            for (int i = 0; i < this.gameRef.players.size(); i++) {
                if (this.gameRef.players.get(i) == oldPlayer) {
                    if (i + 1 < this.gameRef.players.size()) {
                        newPlayer = this.gameRef.players.get(i + 1);
                        break;
                    } else {
                        newPlayer = this.gameRef.players.get(0);
                    }
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
                    // check if (i + forward) < boxes.length
                    if (i + forward_move < boxes.length) {
                        // update the game box
                        oldPlayer.setGameBox(boxes[i + forward_move]);
                    } else {
                        // update the game box to 0 + offset
                        oldPlayer.setGameBox(boxes[0 + i + forward_move - boxes.length]);
                    }
                    break;
                }
            }

            this.diceRolling = false; // reset
        }
        if (this.diceRoll.isMouseHovering(mousePos))
        {
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
        this.gameRef.currentPlayer.render(g2);

        // g2.drawImage(Renderer.boardBackground, 43, 34, Color.WHITE, this.gameRef.display.getFocusCycleRootAncestor());
        this.gameRef.currentPlayer.renderStats(g2, horizontalOffset + boxSize, verticalOffset + boxSize); // Note: boxSize is 65
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
        if(Renderer.boardBackground == null)
        {
            try {
                Renderer.boardBackground = ImageIO.read(new File("resources/background.png")).getScaledInstance(boxSize * 11, boxSize * 11,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/background.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.wattyBackground == null)
        {
            try {
                Renderer.wattyBackground = ImageIO.read(new File("resources/Watty.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/Watty.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.cardinalBackground == null)
        {
            try {
                Renderer.cardinalBackground = ImageIO.read(new File("resources/CardinalCourt.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/CardinalCourt.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.hewittBackground == null)
        {
            try {
                Renderer.hewittBackground = ImageIO.read(new File("resources/HewittManchester.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/HewittManchester.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.triBackground == null)
        {
            try {
                Renderer.triBackground = ImageIO.read(new File("resources/TriTowers.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/TriTowers.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.isupdBackground == null)
        {
            try {
                Renderer.isupdBackground = ImageIO.read(new File("resources/ISUPD.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/ISUPD.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.chanceBackground == null)
        {
            try {
                Renderer.chanceBackground = ImageIO.read(new File("resources/Chance.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/Chance.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.commChestBackground == null)
        {
            try {
                Renderer.commChestBackground = ImageIO.read(new File("resources/CommunityChest.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/CommunityChest.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if(Renderer.freeParkingBackground == null)
        {
            try {
                Renderer.freeParkingBackground = ImageIO.read(new File("resources/FreeParking.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/FreeParking.png");
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }

        if(Renderer.goToJailBackground == null)
        {
            try {
                Renderer.goToJailBackground = ImageIO.read(new File("resources/GoToJail.png")).getScaledInstance(85, 85,
                            Image.SCALE_DEFAULT);
                System.out.println("Imported resources/GoToJail.png");
            } catch(IOException ioe) {
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
            //     // this.showSetUp(g2);
            //     this.showGame(g2);
            //     break;
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
