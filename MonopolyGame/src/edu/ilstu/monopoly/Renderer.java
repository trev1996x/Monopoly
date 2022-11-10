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

    public void drawImage(Graphics2D g2, String imgSrc, Dimension imgSize) {
        try {
            g2.drawImage(
                    ImageIO.read(new File("resources/" + imgSrc)).getScaledInstance(imgSize.width, imgSize.height,
                            Image.SCALE_DEFAULT),
                    0, 0, Color.WHITE,
                    this.gameRef.display.getFocusCycleRootAncestor());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void drawImage(Graphics2D g2, String imgSrc, int width, int height) {
        drawImage(g2, imgSrc, new Dimension(width, height));
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

        StartButton startButton = new StartButton((this.frame.getWidth() / 2), 275);

        startButton.setLocation(startButton.getX() - (startButton.getBounds().width / 2), startButton.getY());

        if (startButton.isMouseHovering(mousePos)) {
            startButton.setHover(true);
            if (mouseClicked)
                this.gameRef.startPlaying();
        } else
            startButton.setHover(false);

        startButton.render(g2);

        CreditsButton creditsButton = new CreditsButton((this.frame.getWidth() / 2), 355);

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

        if (this.gameRef.currentPlayer == null)
            this.gameRef.currentPlayer = this.gameRef.players.get(0);

        // DebugBox box = new DebugBox(0, 100);

        // if (box.isMouseHovering(this.mousePos) && this.mouseClicked) {
        // toggle_debug = !toggle_debug;
        // }

        // box.setColor(toggle_debug ? Color.GREEN : Color.RED);

        // box.render(g2);

        int frameWidth = this.frame.getWidth(),
                frameHeight = this.frame.getHeight() - 20;

        g2.setColor(Color.GRAY);
        g2.fillRect(0, 20, (frameWidth / 11) * 10, (frameHeight / 11) * 10);

        /**
         * Begins top/north side panel
         * x-axis increments by 70
         */
        g2.setColor(Color.BLACK);
        this.boxes[0].render(g2);

        g2.setColor(Color.BLUE); // set color // create object
        this.boxes[1].render(g2); // draw object to screen

        g2.setColor(Color.RED);
        this.boxes[2].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[3].render(g2);

        g2.setColor(Color.RED);
        this.boxes[4].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[5].render(g2);

        g2.setColor(Color.RED);
        this.boxes[6].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[7].render(g2);

        g2.setColor(Color.RED);
        this.boxes[8].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[9].render(g2);

        g2.setColor(Color.BLACK);
        this.boxes[10].render(g2);

        /**
         * Begins right/east side panel
         * y-axis increments by 68
         */

        g2.setColor(Color.RED);
        this.boxes[11].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[12].render(g2);

        g2.setColor(Color.RED);
        this.boxes[13].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[14].render(g2);

        g2.setColor(Color.RED);
        this.boxes[15].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[16].render(g2);

        g2.setColor(Color.RED);
        this.boxes[17].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[18].render(g2);

        g2.setColor(Color.RED);
        this.boxes[19].render(g2);

        g2.setColor(Color.BLACK);
        this.boxes[20].render(g2);

        /*
         * Beings bottom/south side panel
         * x-axis increments in 70
         */

        g2.setColor(Color.BLUE);
        this.boxes[21].render(g2);

        g2.setColor(Color.RED);
        this.boxes[22].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[23].render(g2);

        g2.setColor(Color.RED);
        this.boxes[24].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[25].render(g2);

        g2.setColor(Color.RED);
        this.boxes[26].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[27].render(g2);

        g2.setColor(Color.RED);
        this.boxes[28].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[29].render(g2);

        g2.setColor(Color.BLACK);
        this.boxes[30].render(g2);

        /*
         * Begins the left/west side panel
         * y-axis increments by 68
         */

        g2.setColor(Color.BLUE);
        this.boxes[31].render(g2);

        g2.setColor(Color.RED);
        this.boxes[32].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[33].render(g2);

        g2.setColor(Color.RED);
        this.boxes[34].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[35].render(g2);

        g2.setColor(Color.RED);
        this.boxes[36].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[37].render(g2);

        g2.setColor(Color.RED);
        this.boxes[38].render(g2);

        g2.setColor(Color.BLUE);
        this.boxes[39].render(g2);

        // Identifiers for game boxes

        if (this.diceRoll == null)
            diceRoll = new DiceRoll(200, 200);
        this.diceRoll.rollDice();
        if (this.diceRolling && this.diceRoll.done_iterating) {
            // Dice done rolling!!
            // We need to update the game accordingly..
            // TODO: THIS SHIT ISN'T WORKING RN

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

        Color ActivePlayer = Color.YELLOW;
        Color InactivePlayer = Color.GREEN;

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
        Graphics2D g2 = this.frame.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.Src);
        g2.setFont(new Font("Arial", Font.BOLD, 18));

        switch (this.gameRef.status) {
            case CURRENTLY_PLAYING:
                this.showGame(g2);
                break;
            case GAME_SETUP:
                // this.showSetUp(g2);
                this.showGame(g2);
                break;
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

    @SuppressWarnings("unused")
    private Game gameRef;

    private BufferedImage frame;

    private Point mousePos;

    private boolean mouseClicked;

    private long framesThen = 0L;

    private long framesNow = 0L;

    private long lastCounted = 0L;

    private DiceRoll diceRoll;

    private boolean diceRolling = false;

    protected GameBox boxes[] = new GameBox[40];

}
