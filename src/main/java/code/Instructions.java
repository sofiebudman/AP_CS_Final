package main.java.code;
import static main.java.code.Constants.Fonts.*;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PConstants;

public class Instructions{
    private PApplet p;
    private boolean showInstructions;
    private PFont instructionFont;
    private boolean isVisible;
    public Instructions(PApplet p) {
        this.p = p;
        FontManager.initialize(p);
        this.instructionFont = FontManager.getFont("FARRO_REGULAR_20");
        this.isVisible = false;
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
        if (!showInstructions) return;
        
        // Draw background with gradient effect
        p.noStroke();
        p.fill(255, 184, 119);
        p.rect(0, 50, 300, 650);
        
        // Inner panel with shadow effect
        p.fill(255);
        p.rect(10, 60, 280, 630, 10); // Rounded corners
        
        // Title
        p.fill(0);
        p.textFont(instructionFont);
        p.textAlign(PConstants.CENTER);
        p.text("Instructions", 150, 90);
        
        // Divider line
        p.stroke(200);
        p.line(20, 110, 280, 110);
        p.noStroke();
        
        // Instructions text
        p.textAlign(PConstants.LEFT);
        p.textSize(16);
        p.fill(60);
        p.text("Game Controls:", 20, 140);
        p.textSize(14);
        p.fill(80);
        p.text("• Click continents to toggle borders", 25, 165);
        p.text("• Watch infection spread ", 25, 185);
        p.text("• Monitor city populations and status", 25, 205);
        
        p.textSize(16);
        p.fill(60);
        p.text("City Information:", 20, 245);
        p.textSize(14);
        p.fill(80);
        p.text("• Hover over cities to see details", 25, 270);
        p.text("• Population counts and percentages", 25, 290);
        p.text("• Infection and immunity status", 25, 310);
        
        p.textSize(16);
        p.fill(60);
        p.text("Virus Mechanics:", 20, 350);
        p.textSize(14);
        p.fill(80);
        p.text("• Viruses spread between cities", 25, 375);
        p.text("• Vaccines can be developed", 25, 395);
        p.text("• Viruses can mutate over time", 25, 415);
        
        p.textSize(16);
        p.fill(60);
        p.text("Tips:", 20, 455);
        p.textSize(14);
        p.fill(80);
        p.text("• Close borders early to contain", 25, 480);
        p.text("• Monitor mutation rates", 25, 500);
        p.text("• Check the log for updates", 25, 520);
        
        // Reset text alignment
        p.textAlign(PConstants.LEFT, PConstants.BASELINE);
    }
    public static void main(String[] args) {
        
    }
    
}
