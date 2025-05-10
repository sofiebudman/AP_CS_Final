import javax.swing.*;
import java.awt.*;

public class FullMap {
    
    public static void main(String[] args) {

        JLayeredPane layeredPane = new JLayeredPane();
        JFrame frame = new JFrame("Stacked PNGs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        // Create a panel with vertical BoxLayout
        
    
        ImageIcon africa = new ImageIcon("Maps/africa_closed.png"); // Replace with your image file path
        JLabel a = new JLabel(africa);
        a.setBounds(50, 50, africa.getIconWidth(), africa.getIconHeight());
        ImageIcon eurasia = new ImageIcon("Maps/eurasia_closed.png"); // Replace with your image file path
        JLabel e = new JLabel(eurasia);
        e.setBounds(50, 50, eurasia.getIconWidth(), eurasia.getIconHeight());
        ImageIcon northAmerica = new ImageIcon("Maps/north_america_closed.png"); // Replace with your image file path
        JLabel na = new JLabel(northAmerica);
        na.setBounds(50, 50, northAmerica.getIconWidth(), northAmerica.getIconHeight());
        
        ImageIcon southAmerica = new ImageIcon("Maps/south_america_closed.png"); // Replace with your image file path
        JLabel sa = new JLabel(southAmerica);
        sa.setBounds(50, 50, southAmerica.getIconWidth(), southAmerica.getIconHeight());
        
        
        layeredPane.add(a, Integer.valueOf(0)); // Bottom layer
        layeredPane.add(e, Integer.valueOf(1)); // Top layer
        layeredPane.add(na, Integer.valueOf(2));
        layeredPane.add(sa, Integer.valueOf(3));


        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
      

     

       

   
        frame.setVisible(true);
    }
}