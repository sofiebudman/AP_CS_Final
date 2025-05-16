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


    //set up button objects

    
 
    
  
    
    
   

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

        topBar = new TopBar(this, instructionScreen, virusControlScreen);
        

        // Initialize ControlP5 first
        
        
        
       

        //load images
        

        // Draw initial state
        map.drawContinents();
        

        
        
        
            
    }

    public void draw() {
        background(255); 
        
        map.drawOcean();
        map.drawContinents();
        
        // Display instructions if they are toggled on
        instructionScreen.draw();
        virusControlScreen.draw();
        welcomeScreen.display();
        //topBar.draw();
        
        // Display notifications last so they appear on top
        Notification.display();
    }

   

    public void mousePressed() {
        map.handleMousePressed(mouseX, mouseY);
    }

    
    
    

    public static void main(String[] args) {
        PApplet.main("src.main.java.code.MySketch1");
    }
} 