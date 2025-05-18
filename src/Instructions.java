import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Instructions {
    public Instructions(){
        JFrame instructionsFrame = new JFrame();

        JLabel instructionsLabel = new JLabel("Instructions");

        
        instructionsLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS)); 
        instructionsPanel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));

        JTextArea instructionsText = new JTextArea();
        instructionsText.setBackground(java.awt.Color.GRAY);
        instructionsText.setBorder(BorderFactory.createBevelBorder(0, java.awt.Color.BLUE, java.awt.Color.CYAN));

        instructionsText.setFont(new Font("Helvetica", Font.PLAIN, 14));
        instructionsText.setText("For it to exist, it needs to have a population of over 0.\n" + //
                        "By clicking on a country, you can open or close its borders. Once you change the border status, you cannot change it for another month. \n" + //
                        "If a country's borders are closed, its population will go down by a percentage determined by its population because they have no trade and does not get enough supplies to survive. The virus cannot enter the country if its borders are closed. \n" + //
                        "If a country's borders are open, its population can grow slightly, and it is able to research vaccines with other open countries. \n" + //
                        "Once a country discovers a vaccine, it will start vaccinating its population. Once its entire population is vaccinated, it can give the vaccine to other countries if the other country has its border open.  \n" + //
                        "");
        instructionsText.setEditable(false);  
        instructionsText.setLineWrap(true);  
        instructionsText.setWrapStyleWord(true); 
        instructionsText.setCaretPosition(0); 
        JScrollPane scrollPane = new JScrollPane(instructionsText);
        Dimension scrollPaneSize = new Dimension(300, 300);  // Set a reasonable size
        scrollPane.setBackground(java.awt.Color.MAGENTA);
       
        scrollPane.setPreferredSize(scrollPaneSize);
        scrollPane.setMaximumSize(scrollPaneSize);
        scrollPane.setMinimumSize(scrollPaneSize);
        

        instructionsPanel.add(instructionsLabel);

        instructionsPanel.add(scrollPane);


        instructionsFrame.add(instructionsPanel, BorderLayout.CENTER);
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionsFrame.setTitle("CS");
        instructionsFrame.pack();
        instructionsFrame.setVisible(true);

        


    }
    public static void main (String[] args){
        new Instructions();

    }
    
} 