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


    //set up button objects

    ControlP5 instructions;  
    ControlP5 virusControlButton;
    ControlP5 graphButton;
 
    ArrayList<CountryImage> countryImages = new ArrayList<>();


    CountryImage africa = new CountryImage(AFRICA_OPEN_PATH, AFRICA_CLOSED_PATH);
    CountryImage northAmerica = new CountryImage(NORTH_AMERICA_OPEN_PATH, NORTH_AMERICA_CLOSED_PATH);
    CountryImage southAmerica = new CountryImage(SOUTH_AMERICA_OPEN_PATH, SOUTH_AMERICA_CLOSED_PATH);
    CountryImage eurasia = new CountryImage(EURASIA_OPEN_PATH, EURASIA_CLOSED_PATH);
  
    
    PImage africaOpen;
    PImage northAmericaOpen;
    PImage southAmericaOpen;
    PImage eurasiaOpen;

    PImage africaClosed;
    PImage northAmericaClosed;
    PImage southAmericaClosed;
    PImage eurasiaClosed;
    PImage ocean;
   

    public void settings() {
        size(1166, 700);
    }

    //load all country images
    void controlEvent(ControlEvent e) {
        if (e.isFrom("virusName")) {
            System.out.println("Event from virusName field");
            virusControlScreen.handleEvent(e);
        }
    }

    public void setup() {
        instructionScreen = new Instructions(this);
        virusControlScreen  = new VirusControl(this);

        // Initialize ControlP5 first
        instructions = new ControlP5(this); 
        virusControlButton = new ControlP5(this);
        graphButton = new ControlP5(this);
        
        countryImages.add(africa);
        countryImages.add(northAmerica);
        countryImages.add(southAmerica);
        countryImages.add(eurasia);
       

        //load images
        ocean = loadImage(OCEAN_PATH);

        africaOpen = loadImage(africa.getOpenPath());
        northAmericaOpen = loadImage(northAmerica.getOpenPath());
        southAmericaOpen = loadImage(southAmerica.getOpenPath());
        eurasiaOpen = loadImage(eurasia.getOpenPath());

        africaClosed = loadImage(africa.getClosedPath());
        northAmericaClosed = loadImage(northAmerica.getClosedPath());
        southAmericaClosed = loadImage(southAmerica.getClosedPath());
        eurasiaClosed = loadImage(eurasia.getClosedPath());

        // Resize all images
        africaOpen.resize(WIDTH_SCALE, 0);
        northAmericaOpen.resize(WIDTH_SCALE, 0);
        southAmericaOpen.resize(WIDTH_SCALE, 0);
        eurasiaOpen.resize(WIDTH_SCALE, 0);
        africaClosed.resize(WIDTH_SCALE, 0);
        northAmericaClosed.resize(WIDTH_SCALE, 0);
        southAmericaClosed.resize(WIDTH_SCALE, 0);
        eurasiaClosed.resize(WIDTH_SCALE, 0);

        // Draw initial state
        drawContinents();
        textSize(120);
        text("Epidemic Simulator", 40, 120); 

        instructions.addButton("instructions")
            .setPosition(0, 0)
            .setSize(100, 50)
            .setLabel("Instructions")
            .onClick(e -> {
                instructionScreen.toggle();
                
            });

        virusControlButton.addButton("virusControl")
            .setPosition(100,0)
            .setSize(100,50)
            .setLabel("Virus Control" )
            .onClick(e -> {
                virusControlScreen.toggle();
            });

        graphButton.addButton("graphButton")
            .setPosition(200,0)
            .setSize(100,50)
            .setLabel("Graph");
            
        
        
            
    }

    public void draw() {
        
        

        background(255); 

      
        fill(51,94,200,250);
        rect(200, 0, 1000, 50);

        fill(255);
        PFont mono = createFont(GEO_REGULAR_FONT_PATH, 40);
        textFont(mono);
        text("Epidemic Simulator", 400, 40);

        image(ocean, 0, 50);
        ocean.resize(WIDTH_SCALE,0);
        
      
        drawContinents();
        
        // Display instructions if they are toggled on
        instructionScreen.draw();
        virusControlScreen.draw();
    }

    public void drawContinents() {
        for(CountryImage c: countryImages){
            if(c.isOpen()){
                if(c == africa) image(africaOpen, 20, 50);
                else if(c == northAmerica) image(northAmericaOpen, 20, 50);
                else if(c == southAmerica) image(southAmericaOpen, 20, 50);
                else if(c == eurasia) image(eurasiaOpen, 20, 50);
            }
            else {
                if(c == africa) image(africaClosed, 20, 50);
                else if(c == northAmerica) image(northAmericaClosed, 20, 50);
                else if(c == southAmerica) image(southAmericaClosed, 20, 50);
                else if(c == eurasia) image(eurasiaClosed, 20, 50);
            }
        }
    }

    public void mousePressed() {
        int x = mouseX - HORIZONTAL_SHIFT; // Adjust for offsets
        int y = mouseY- VERTICAL_SHIFT;

        // Check each continent
        checkContinentClick(x, y, africaOpen, africa);
        checkContinentClick(x, y, northAmericaOpen, northAmerica);
        checkContinentClick(x, y, southAmericaOpen, southAmerica);
        checkContinentClick(x, y, eurasiaOpen, eurasia);
    }

    private void checkContinentClick(int x, int y, PImage continent, CountryImage c) {
        if (x >= 0 && x < continent.width && y >= 0 && y < continent.height) {
            int i = continent.get(x, y);
            float a = alpha(i);

            if (a > 0) {
                c.switchState();
    
                
            }
        }
    }
    
    

    public static void main(String[] args) {
        PApplet.main("src.main.java.code.MySketch1");
    }
}