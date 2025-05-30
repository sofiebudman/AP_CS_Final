package main.java.code;
import static main.java.code.Constants.Fonts.*;

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
        p.noStroke();
        p.fill(255, 184, 119);
        p.rect(0, 50, 300, 650);
       
        
        p.fill(255);
        p.rect(10,60, 280, 630);
       
        p.fill(0);
        //p.textSize(5);
        PFont mono = p.createFont(FARRO_REGULAR_FONT_PATH, 18, true);
        p.textFont(mono);
        p.text("Red Circle = Infection\n" + //
                "White City = Vulnerable\n" + //
                "Blue City = Immune\n\n" + //
                
                "Vaccines and immunity\n" +
                "Lost on virus mutation\n\n" + //

                "Click countries toggle border\n\n" + //
                "Population grows on open \n border\n\n" + //
                "Population Shrinks on\nClosed Border (no trade)\n\n" + //

                "Hover over a city to see\nstatistics\n" + //
                 "Tip: Use Graph and Log to\nsee information", 20, 120);


    }
    public static void main(String[] args) {
        
    }
    
}