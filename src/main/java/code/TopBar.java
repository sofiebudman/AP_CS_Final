package main.java.code;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PFont;
import static main.java.code.Constants.*;

public class TopBar {
    private PApplet p;
    private ControlP5 cp5;  // Single ControlP5 instance
    private Instructions instructionsScreen;
    private VirusControl virusControlScreen;
   
    public TopBar(PApplet p, Instructions instructionScreen, VirusControl virusControlScreen) {
        this.p = p;
        this.cp5 = new ControlP5(p);
        this.instructionsScreen = instructionScreen;
        this.virusControlScreen = virusControlScreen;

        // Create buttons once in constructor
        cp5.addButton("instructions")
            .setPosition(0, 0)
            .setSize(100, 50)
            .setLabel("Instructions")
            .onPress(e -> {
                this.instructionsScreen.toggle();
            });

        cp5.addButton("virusControl")
            .setPosition(100, 0)
            .setSize(100, 50)
            .setLabel("Virus Control")
            .onPress(e -> {
                this.virusControlScreen.toggle();
            });

        cp5.addButton("graphButton")
            .setPosition(200, 0)
            .setSize(100, 50)
            .setLabel("Graph");
    }
     
    public void draw() {
        // Draw any additional UI elements here if needed
        p.fill(51, 94, 200, 250);
        p.rect(200, 0, 1000, 50);
        p.fill(255);
        //PFont mono = p.createFont(GEO_REGULAR_FONT_PATH, 40);
        //p.textFont(mono);
        //p.text("Epidemic Simulator", 400, 40);
    }
}
