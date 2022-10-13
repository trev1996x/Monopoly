/**
 * ULID: tmqualt, (Enter ULID), (Enter ULID)
 * Date Created: 12 October 2022
 */
package edu.ilstu;
/**
 * @author Ty Qualters
 * @author Trevor Leamon
 * @author Joel Fangu
 * 
 * The main window for the application
 */

import javax.swing.JFrame;
import java.awt.Graphics;

// Note: Monopoly has 40 boxes
//  4 are corner boxes: Start, Jail, Free Parking, Go to Jail
//  https://media.4rgos.it/i/Argos/7499339_R_Z002A

public class MainWindow extends JFrame {
    /**
     * Default MainWindow constructor
     * @param window_title Title of the window
     * @param width Width of the window
     * @param height Height of the window
     */
    MainWindow(String window_title, int width, int height) {
        super(window_title);
        super.setSize(width, height);
        super.setLocationRelativeTo(null); // center on screen
        super.setResizable(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true); // should be last, will show the window
    }

    // Following does not require a JavaDoc comment
    @Override
    public void paint(Graphics g) {
        super.paint(g); // call original method
        // Do whatever
    }

}
