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
        map = new Map(this);

        topBar = new TopBar(this, instructionScreen, virusControlScreen);
        

        // Initialize ControlP5 first
        
        
        
       

        //load images
        

        // Draw initial state
        map.drawContinents();
        

        
        
        
            
    }

    public void draw() {
       
        
        
        

        background(255); 

      
        fill(51,94,200,250);
        rect(200, 0, 1000, 50);

        fill(255);
        PFont mono = createFont(GEO_REGULAR_FONT_PATH, 40);
        textFont(mono);
        text("Epidemic Simulator", 400, 40);

        
        
        map.drawOcean();
        map.drawContinents();
        
        // Display instructions if they are toggled on
        instructionScreen.draw();
        virusControlScreen.draw();
        notification.newNotification("example notification");
        welcomeScreen.display();
        
    }

   

    public void mousePressed() {
        map.handleMousePressed(mouseX, mouseY);
    }

    
    
    

    public static void main(String[] args) {
        PApplet.main("src.main.java.code.MySketch1");
    }
} 