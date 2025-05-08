import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;
import java.io.File;
public class Map {
    public static void main (String[] args){



        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SVG Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Batik SVG canvas
            JSVGCanvas svgCanvas = new JSVGCanvas();
            svgCanvas.setURI(new File("SVGs/usa-canada.svg").toURI().toString());

            frame.getContentPane().add(svgCanvas, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
    
}
