import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;
import java.io.File;
public class Map {
    public static void main (String[] args){



        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SVG Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
            frame.setLayout(new GridLayout(1, 2));
            frame.setSize(800, 600);

            JSVGCanvas canvas1 = new JSVGCanvas();
            canvas1.setURI(new File("SVGs/usa-canada.svg").toURI().toString());
            canvas1.setPreferredSize(new Dimension(400, 400));

            // Second canvas
            JSVGCanvas canvas2 = new JSVGCanvas();
            canvas2.setURI(new File("SVGs/Africa.svg").toURI().toString());
            canvas2.setPreferredSize(new Dimension(400, 400));
            

            frame.add(canvas1);
            frame.add(canvas2);

            frame.pack();
            frame.setVisible(true);
        });
    }
    
}
