package src;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import src.main.java.code.CountryImage;
import controlP5.*;  // Add ControlP5 import


public class MySketch1 extends PApplet {
    Instructions instructionScreen;

    ControlP5 instructions;  // Declare ControlP5 object
   // ButtonBar buttonBar;  // Declare ButtonBar object
    ArrayList<CountryImage> countryImages = new ArrayList<>();


    CountryImage africa = new CountryImage("src/main/resources/africa_open.png", "src/main/resources/africa_closed.png");
    CountryImage northAmerica = new CountryImage("src/main/resources/northamerica_open.png", "src/main/resources/northamerica_closed.png");
    CountryImage southAmerica = new CountryImage("src/main/resources/southamerica_open.png", "src/main/resources/southamerica_closed.png");
    CountryImage eurasia = new CountryImage("src/main/resources/eurasia_open.png", "src/main/resources/eurasia_closed.png");
  
    
    PImage africaOpen;
    PImage northAmericaOpen;
    PImage southAmericaOpen;
    PImage eurasiaOpen;

    PImage africaClosed;
    PImage northAmericaClosed;
    PImage southAmericaClosed;
    PImage eurasiaClosed;
    PImage ocean;
    /* 

    // Track the current state of each continent
    boolean isAfricaClosed = false;
    boolean isNorthAmericaClosed = false;
    boolean isSouthAmericaClosed = false;
    boolean isEurasiaClosed = false; */

    public void settings() {
        size(1000, 600);
    }

    //load all country images
    public void setup() {
        instructionScreen = new Instructions(this);

        
       
        countryImages.add(africa);
        countryImages.add(northAmerica);
        countryImages.add(southAmerica);
        countryImages.add(eurasia);
        //background(255);
        instructions = new ControlP5(this);  // Initialize ControlP5
       /* 
        buttonBar = cp5.addButtonBar("buttonBar")
            .setPosition(0, 0)
            .setSize(200, 40)
            .addItems(split("Instructions", ","))
            .addItems(split("Graph", ","))
        
            /* .addItems(split("Africa,North America,South America,Eurasia", ","))*/
           

        // Optional: set default selection
        //buttonBar.setValue(0); // Selects "Africa" by default  // Initialize ButtonBar (remove the String argument)

        // Add countries to the ArrayList
        countryImages.add(africa);
        countryImages.add(northAmerica);
        countryImages.add(southAmerica);
        countryImages.add(eurasia);

        ocean = loadImage("src/main/resources/ocean.png");
        
        
        // Load open images
        africaOpen = loadImage(africa.getOpenPath());
        northAmericaOpen = loadImage(northAmerica.getOpenPath());
        southAmericaOpen = loadImage(southAmerica.getOpenPath());
        eurasiaOpen = loadImage(eurasia.getOpenPath());

        // Load closed images
        africaClosed = loadImage(africa.getClosedPath());
        northAmericaClosed = loadImage("src/main/resources/northamerica_closed.png");
        southAmericaClosed = loadImage("src/main/resources/southamerica_closed.png");
        eurasiaClosed = loadImage("src/main/resources/eurasia_closed.png");

        // Resize all images
        africaOpen.resize(1000, 0);
        northAmericaOpen.resize(1000, 0);
        southAmericaOpen.resize(1000, 0);
        eurasiaOpen.resize(1000, 0);
        africaClosed.resize(1000, 0);
        northAmericaClosed.resize(1000, 0);
        southAmericaClosed.resize(1000, 0);
        eurasiaClosed.resize(1000, 0);

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
    }

    public void draw() {
        background(255); 
        image(ocean, 0, 0);
        ocean.resize(1000,0);
        
        // Clear the background
        //background(255);
        // Draw all continents in their current state
        drawContinents();
        
        // Display instructions if they are toggled on
        instructionScreen.draw();
    }

    public void drawContinents() {
        for(CountryImage c: countryImages){
            if(c.isOpen()){
                if(c == africa) image(africaOpen, 50, 0);
                else if(c == northAmerica) image(northAmericaOpen, 50, 0);
                else if(c == southAmerica) image(southAmericaOpen, 50, 0);
                else if(c == eurasia) image(eurasiaOpen, 50, 0);
            }
            else {
                if(c == africa) image(africaClosed, 50, 0);
                else if(c == northAmerica) image(northAmericaClosed, 50, 0);
                else if(c == southAmerica) image(southAmericaClosed, 50, 0);
                else if(c == eurasia) image(eurasiaClosed, 50, 0);
            }
        }
    }

    public void mousePressed() {
        int x = mouseX - 50; // Adjust for the 50px offset
        int y = mouseY;

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
        PApplet.main("src.MySketch1");
    }
}