/**
* @author Ty Qualters, Joel Fangu, Trevor Lemaon
* ULID: jfangu, tjleamo, tmqualt
* Class: IT178
* 2022
*/
package edu.ilstu.monopoly;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;


public class PropertyPurchase {
    
    private JDialog infoBox;

    public void showPurchasePropertyDialog(JFrame owner, int price) {

        this.infoBox = new JDialog(owner, "Purchase Property?");

        JLabel purchasePropertyQuestion = new JLabel("Purchase property for $" + price + "?");
        JButton optionYes = new JButton("Yes");
        JButton optionNo = new JButton("No");

// listens if button is clicked yes or no
        optionYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clicked Yes
                confirmation = true;
            }
         });

         optionNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clicked No
            }
         });

        
        this.infoBox.add(purchasePropertyQuestion);
        this.infoBox.add(optionYes);
        this.infoBox.add(optionNo);


        this.infoBox.setModal(true);
        this.infoBox.setAlwaysOnTop(true);
        this.infoBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.infoBox.setSize(100, 150);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(0);
        this.infoBox.setLayout(layout);

        this.infoBox.setLocationRelativeTo(null);
        this.infoBox.setVisible(true);
        

    }


    // Just a default override for testing purposes
    public void showPurchasePropertyDialog(JFrame owner)
    {
        this.showPurchasePropertyDialog(owner, 0);
    }

    public boolean confirmation = false;
}
