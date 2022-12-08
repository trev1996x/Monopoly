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
import java.awt.Dimension;

public class PropertyPurchase {

    private JDialog infoBox;

    public void showPurchasePropertyDialog(JFrame owner, int price) {

        this.infoBox = new JDialog(owner, "Purchase Property?");
        this.infoBox.setPreferredSize(new Dimension(500, 500));
        this.infoBox.setSize(new Dimension(250, 250));
        this.infoBox.setResizable(false);
        this.infoBox.setModal(true);
        this.infoBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel purchasePropertyQuestion = new JLabel("Purchase property for $" + price + "?");
        JButton optionYes = new JButton("Yes");
        JButton optionNo = new JButton("No");

        // listens if button is clicked yes or no
        optionYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clicked Yes
                confirmation = true;
                infoBox.dispose();
            }
        });

        optionNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clicked No
                infoBox.dispose();
            }
        });

        this.infoBox.add(purchasePropertyQuestion);
        this.infoBox.add(optionYes);
        this.infoBox.add(optionNo);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(0);
        this.infoBox.setLayout(layout);

        this.infoBox.setLocationRelativeTo(null);
        this.infoBox.setVisible(true);

    }

    // Just a default override for testing purposes
    public void showPurchasePropertyDialog(JFrame owner) {
        this.showPurchasePropertyDialog(owner, 0);
    }

    public boolean confirmation = false;
}
