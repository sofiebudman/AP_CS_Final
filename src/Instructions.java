package src;

import processing.core.PApplet;
import processing.core.PFont;

public class Instructions{
    PApplet p;
    public boolean showInstructions = true;
    public Instructions(PApplet p) {
        this.p = p;
    }
    public void show(){
        showInstructions = true;
    }
    public void hide(){
        showInstructions = false;
    }
    public void toggle() {
        showInstructions = !showInstructions;
    }
    
    public void draw(){
        if (!showInstructions)return;
        p.fill(229, 229, 229, 250);
        p.rect(0, 50, 520, 220);
        p.fill(0);
        //p.textSize(5);
        PFont mono = p.createFont("src/main/resources/fonts/roboto.ttf", 20);
        p.textFont(mono);
        p.text("Instructions:\n1. Click continents.\n2. Track infection.\n3. Use buttons to toggle borders.", 20, 120);


    }
    public static void main(String[] args) {
        
    }
    
}
