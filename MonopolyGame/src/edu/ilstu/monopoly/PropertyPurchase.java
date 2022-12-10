/**
* @author Ty Qualters, Joel Fangu, Trevor Lemaon
* ULID: jfangu, tjleamo, tmqualt
* Class: IT178
* 2022
*/
package edu.ilstu.monopoly;

import edu.ilstu.monopoly.items.GameBox;
import edu.ilstu.monopoly.items.Player;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Dimension;

public class PropertyPurchase {

    private JDialog infoBox;

    public void showPurchasePropertyDialog(JFrame owner, Player player, GameBox box, int price) {

        this.infoBox = new JDialog(owner, "Purchase Property?");
        this.infoBox.setPreferredSize(new Dimension(250, 250));
        this.infoBox.setSize(new Dimension(250, 250));
        this.infoBox.setResizable(false);
        this.infoBox.setModal(true);
        this.infoBox.setLocationRelativeTo(null);
        this.infoBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel purchasePropertyQuestion = new JLabel("Purchase " + box.label +  " for $" + price + "?");
        JLabel playerTotal = new JLabel("You currently have $" + player.getMoney() + ".");
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

        panel1.add(purchasePropertyQuestion);
        this.infoBox.add(panel1);
        
        panel2.add(playerTotal);
        this.infoBox.add(panel2);

        panel3.add(optionYes);
        panel3.add(optionNo);
        this.infoBox.add(panel3);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(0);
        panel1.setLayout(layout);
        panel2.setLayout(layout);
        panel3.setLayout(layout);
        this.infoBox.setLayout(layout);

        this.infoBox.setLocationRelativeTo(null);
        this.infoBox.setVisible(true);

    }
    
    public boolean confirmation = false;
}
