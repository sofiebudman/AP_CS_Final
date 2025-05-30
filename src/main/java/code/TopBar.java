package main.java.code;

import controlP5.ControlP5;
import processing.core.PApplet;


public class TopBar {
    private PApplet p;
    private ControlP5 cp5;  
   
   
    public TopBar(PApplet p, Instructions instructions, Log log) {
        this.p = p;
        this.cp5 = new ControlP5(p);
      

        cp5.addButton("instructions")
            .setPosition(0, 0)
            .setSize(100, 50)
            .setLabel("Instructions")
            .onPress(e -> {
                instructions.toggle();
                Log.hide();
                Graph.hide();
            });

        cp5.addButton("graphButton")
            .setPosition(200, 0)
            .setSize(100, 50)
            .setLabel("Graph")
            .onPress(e -> {
                Graph.toggle();
                Log.hide();
                instructions.hide();
            });

        cp5.addButton("logButton")
            .setPosition(100,0)
            .setSize(100,50)
            .setLabel("Log")
            .onPress(e -> {
                Log.toggle();
                instructions.hide();
                Graph.hide();
            });

        cp5.addButton("end")
            .setPosition(1300,0)
            .setSize(100,50)
            .setLabel("End Sim")
            .onPress(e -> {
                WelcomeScreen.increaseCurrentPage();

            });
        }
    
     
    public void draw() {
        
      
        p.fill(51, 94, 200, 250);
        p.rect(200, 0, 1400, 50);
        p.fill(255);
        
    }
    public void remove(){
        cp5.remove("instructions");
            cp5.remove("graphButton");
            cp5.remove("logButton");
            cp5.remove("end");
    }
}
