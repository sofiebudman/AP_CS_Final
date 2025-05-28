package main.java.code;
import java.util.ArrayList;
import static main.java.code.Constants.Coordinates.*;
import static main.java.code.Constants.Scale.*;

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

    private boolean firstNotification = false; // Flag to track if it's the first notification

   
    

    private boolean topBarCreated = false;

    public void settings() {
        size(1440, 700);
    }


    public void setup() {
        notification = new Notification(this);
        instructionScreen = new Instructions(this);
        welcomeScreen = new WelcomeScreen(this);
        map = new Map(this, notification);
        
    }

    public void draw() {
        background(255); 
        
        // Check if we're past the welcome screen
        if (welcomeScreen.getCurrentPage() >= 4) {
            // Create TopBar only once when we reach this point

            if(!firstNotification){
                Notification.newNotification(Virus.getName() + " created");
                firstNotification = true;

            }
            
            if (!topBarCreated) {
                topBar = new TopBar(this, instructionScreen, virusControlScreen);
                map.start();
                map.startTimer();
                topBarCreated = true;
            }
            
            
            
            // Draw map and UI elements
            Log.displayLog(this);
            map.drawOcean();
            map.drawContinents();
            instructionScreen.draw();
           
            topBar.draw();
            map.drawCity(); // Draw cities

         

        } else {
            // Show welcome screen
            welcomeScreen.display();
        }
        
        // Display notifications last so they appear on top
        Notification.display();

    }

    
    public void mousePressed() {
        // Only handle map clicks if we're past the welcome screen
        if (welcomeScreen.getCurrentPage() >= 4) {
            map.handleMousePressed(mouseX, mouseY);
            
        }
        System.out.println("Coordinates - X: " + mouseX + ", Y: " + mouseY);
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { "main.java.code.MySketch1" });
    }
} 