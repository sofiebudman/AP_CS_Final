package main.java.code;


import static main.java.code.Constants.FilePaths.*;
import static main.java.code.Constants.Scale.*;

import processing.core.PApplet;

import processing.core.PImage;



public class MySketch1 extends PApplet {

    //instance variables for other UI elements for bettercontrol
    Instructions instructions;
    Notification notification;
    WelcomeScreen welcomeScreen;
    TopBar topBar;
    Map map;
    Log log;
    PImage end;
   

    private boolean firstNotification = false; // Flag to track if it's the first notification


    private boolean topBarCreated = false; // ensures this is only creates once


    /*
     * This line runs at the start of creating PApplet
     * Creates 1440 by 700 window
     */
    public void settings() {
        size(1440, 700);
    }


    public void setup() {
        notification = new Notification(this);
        instructions = new Instructions(this);
        welcomeScreen = new WelcomeScreen(this);
        log = new Log (this);

        end = loadImage(END_IMAGE_PATH);
        end.resize(WIDTH_SCALE-100,0);
       
        
        map = new Map(this, notification);

    }

    public void draw() {
        
        background(255); 
        
        // Check if we're past the welcome screen
        if (WelcomeScreen.getCurrentPage() == 4) {
            

            if(!firstNotification){
                Notification.newNotification(Virus.getName() + " created");
                firstNotification = true;

            }
            
            if (!topBarCreated) {
                topBar = new TopBar(this, instructions, log);
                map.start();
                map.startTimer();
                topBarCreated = true;
            }
            
            
            
            //draw all UI elements
          
            Log.displayLog();
            map.drawOcean();
            map.drawContinents();
            instructions.draw();
           
            topBar.draw();
            map.drawCity(); // Draw cities
            Notification.display();


            Graph.display(map.getCities(), this);
           
      

         

        } else if(WelcomeScreen.getCurrentPage() < 4){
            //show welcome screen here
            welcomeScreen.display();
           
        }
        else{
            //remove elements and show end screen
            Log.hide();
             topBar.remove();
             image(end,150,-50);

             
      
        }
        
        
        
    }
    public void mousePressed() {

        if (WelcomeScreen.getCurrentPage() >= 4) {
            map.handleMousePressed(mouseX, mouseY);
            
        }
        //System.out.println("Coordinates - X: " + mouseX + ", Y: " + mouseY);
    }


   
    public static void main(String[] args) {
        PApplet.main(new String[] { "main.java.code.MySketch1" });
    }
} 