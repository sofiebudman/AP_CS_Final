import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GUI{
    private int count = 0;
    private JLabel label;
    private JFrame frame; 
    private JPanel panel;
    public GUI(){
        
    }
    public static void main (String[] args){

        JFrame frame = new JFrame("Image Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Load original image
        ImageIcon originalIcon = new ImageIcon("usa.png");
        Image originalImage = originalIcon.getImage();

        // Scale image to fit the frame size
        Image scaledImage = originalImage.getScaledInstance(
            800, 600, Image.SCALE_SMOOTH
        );

        // Create a new icon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Add to label and frame
        JLabel imageLabel = new JLabel(scaledIcon);
        frame.add(imageLabel);

        frame.setVisible(true);
       /*  JFrame frame = new JFrame();
        Font appFont = new Font("Lucida Console",Font.PLAIN, 15);

        JLabel epidemicSimulator = new JLabel("Epidemic Simulator");
        epidemicSimulator.setFont(appFont);
    
       /*  String[] options = {"North America", "South America", "Eurasia", "Africa", "Australia"};
        JComboBox<String> startingContinents = new JComboBox<String>(options);*/


/*         ImageIcon usa = new ImageIcon("maps/africa_open.png"); // Replace with your image file path
        JLabel usaLabel = new JLabel(usa);

        frame.add(usaLabel);
        JPanel panel = new JPanel();
        frame.setVisible(true);
       /*
        label.setFont(appFont);
        JLabel continentText = new JLabel("Chose a Continent for the virus to start in");
        continentText.setFont(appFont);

        panel.setBorder(BorderFactory.createEmptyBorder(100,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        panel.add(epidemicSimulator);
        panel.add(button);
        panel.add(label);
        panel.add(continentText);
    
        panel.add(startingContinents); 

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CS");
        frame.pack();
        frame.setVisible(true); */
    }

    
}