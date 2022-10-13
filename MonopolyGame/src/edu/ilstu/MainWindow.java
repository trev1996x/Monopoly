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

public class MainWindow extends JFrame {
    MainWindow(String window_title, int width, int height) {
        super(window_title);
        super.setSize(width, height);
        super.setLocationRelativeTo(null); // center on screen
        super.setResizable(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true); // should be last, will show the window
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // call original method
        // Do whatever
    }

}
