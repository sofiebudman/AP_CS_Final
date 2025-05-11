import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class FullMap {
    
    
   
    
    
    public static void main(String[] args) throws IOException {

        JLayeredPane layeredPane = new JLayeredPane();
        JFrame frame = new JFrame("Stacked PNGs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        BufferedImage africaImage = ImageIO.read(new File("Maps/africa_open.png"));
        
         JLabel a = new JLabel(new ImageIcon(africaImage));
        a.setBounds(50, 50, africaImage.getWidth(), africaImage.getHeight());

        a.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // This code runs when the image is clicked
                int x = e.getX();
                int y = e.getY();

                if (x >= 0 && x < africaImage.getWidth() && y >= 0 && y < africaImage.getHeight()) {
                    int pixel = africaImage.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {  // Non-transparent pixel
                        JOptionPane.showMessageDialog(frame, "Image clicked!");
                    } else {
                        System.out.println("Clicked on transparent part. Ignored.");
                    }
                }

                
            }
        });
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