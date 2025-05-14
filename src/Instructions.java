package src;

import processing.core.PApplet;

public class Instructions{
    PApplet p;
    public boolean showInstructions = false;
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
        p.fill(50, 50, 200, 200);
        p.rect(40, 140, 520, 220);
        p.fill(255);
        p.textSize(16);
        p.text("Instructions:\n1. Click continents.\n2. Track infection.\n3. Use buttons to toggle borders.", 60, 180);


    }
    public static void main(String[] args) {
        
    }
    
}
