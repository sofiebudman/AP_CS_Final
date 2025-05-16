package src.main.java.code;

import controlP5.ControlP5;
import processing.core.PApplet;

public class TopBar {
    private PApplet p;
    private ControlP5 instructions;  
    private ControlP5 virusControlButton;
    private ControlP5 graphButton;
    private Instructions instructionsScreen;
    private VirusControl virusControlScreen;
   
    public TopBar(PApplet p, Instructions instructionScreen, VirusControl virusControlScreen) {
        this.p = p;
        instructions = new ControlP5(p); 
        virusControlButton = new ControlP5(p);
        graphButton = new ControlP5(p);
        this.instructionsScreen = instructionScreen;
        this.virusControlScreen = virusControlScreen;


        instructions.addButton("instructions")
            .setPosition(0, 0)
            .setSize(100, 50)
            .setLabel("Instructions")
            .onClick(e -> {
                this.instructionsScreen.toggle();
                
            });

        virusControlButton.addButton("virusControl")
            .setPosition(100,0)
            .setSize(100,50)
            .setLabel("Virus Control" )
            .onClick(e -> {
                this.virusControlScreen.toggle();
            });

        graphButton.addButton("graphButton")
            .setPosition(200,0)
            .setSize(100,50)
            .setLabel("Graph");

        p.textSize(120);
        p.text("Epidemic Simulator", 40, 120); 
            
    }
}
