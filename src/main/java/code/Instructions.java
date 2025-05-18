package src.main.java.code;
import static src.main.java.code.Constants.Fonts.*;

import processing.core.PApplet;
import processing.core.PFont;

public class Instructions{
    private PApplet p;
    private boolean showInstructions;
    public Instructions(PApplet p) {
        this.p = p;
        showInstructions = false;
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
    //
    public void draw(){
        if (!showInstructions)return;
        p.fill(229, 229, 229, 250);
        p.rect(0, 50, 300, 220);
        p.fill(0);
        //p.textSize(5);
        PFont mono = p.createFont(FARRO_REGULAR_FONT_PATH, 30);
        p.textFont(mono);
        p.text("Instructions:\n1. Click continents.\n2. Track infection.\n3. Use buttons to\n toggle borders.", 20, 120);


    }
    public static void main(String[] args) {
        
    }
    
}
