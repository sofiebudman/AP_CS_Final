import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
        frame = new JFrame();
        Font appFont = new Font("Lucida Console",Font.PLAIN, 15);

        JLabel epidemicSimulator = new JLabel("Epidemic Simulator");
        epidemicSimulator.setFont(appFont);
        JButton button = new JButton("Click me");
        String[] options = {"North America", "South America", "Eurasia", "Africa", "Australia"};
        JComboBox<String> startingContinents = new JComboBox<String>(options);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    
                count++;
                label.setText("Number of clicks: " + count);
            }
            
        });
        panel = new JPanel();
        label = new JLabel("Number of clicks: 0");
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
        frame.setVisible(true);
    }
    public static void main (String[] args){
        new GUI();
    }

    
}