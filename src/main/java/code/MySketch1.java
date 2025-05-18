package src.main.java.code;
import java.util.ArrayList;
import static src.main.java.code.Constants.*;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import controlP5.*;  // Add ControlP5 import
import g4p_controls.*;

public class MySketch1 extends PApplet {
    Instructions instructionScreen;
    VirusControl virusControlScreen;
    Notification notification;
    WelcomeScreen welcomeScreen;
    TopBar topBar;
    Map map;
    private boolean topBarCreated = false;

    public void settings() {
        size(1440, 700);
    }

    //load all country images
    void controlEvent(ControlEvent e) {
        if (e.isFrom("virusName")) {
            System.out.println("Event from virusName field");
            virusControlScreen.handleEvent(e);
        }
    }

    public void setup() {
        notification = new Notification(this);
        instructionScreen = new Instructions(this);
        virusControlScreen  = new VirusControl(this);
        welcomeScreen = new WelcomeScreen(this);
        map = new Map(this, notification);
    }

    public void draw() {
        background(255); 
        
        // Check if we're past the welcome screen
        if (welcomeScreen.getCurrentPage() >= 3) {
            // Create TopBar only once when we reach this point
            if (!topBarCreated) {
                topBar = new TopBar(this, instructionScreen, virusControlScreen);
                topBarCreated = true;
            }
            
            // Draw map and UI elements
            map.drawOcean();
            map.drawContinents();
            instructionScreen.draw();
            topBar.draw();
        } else {
            // Show welcome screen
            welcomeScreen.display();
        }
        
        // Display notifications last so they appear on top
        Notification.display();
    }

    public void mousePressed() {
        // Only handle map clicks if we're past the welcome screen
        if (welcomeScreen.getCurrentPage() >= 3) {
            map.handleMousePressed(mouseX, mouseY);
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", "src.main.java.code.MySketch1" });
    }
} 