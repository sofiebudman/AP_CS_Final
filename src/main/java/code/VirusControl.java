package src.main.java.code;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ResourceBundle.Control;

import controlP5.*;

public class VirusControl {
    PApplet p;
    ControlP5 virusNameField;
    ControlP5 deathRateSlider;
    ControlP5 recoveryRateSlider;
    ControlP5 transmissionRateSlider;
    String virusName = "";
    private boolean show;
    
    public VirusControl(PApplet p) {
        this.p = p;
        virusNameField = new ControlP5(p);
        deathRateSlider = new ControlP5(p);
        recoveryRateSlider = new ControlP5(p);
        transmissionRateSlider = new ControlP5(p);
        show = true;

        virusNameField.addTextfield("virusName")
                .setPosition(540, 60)
                .setSize(200, 40)
                .setFont(p.createFont("Arial", 20))
                .setColorBackground(p.color(255, 255, 255))
                .setColorForeground(p.color(0, 0, 0))
                .setColorActive(p.color(0, 0, 0))
                .setColorValue(p.color(0, 0, 0));

        deathRateSlider.addSlider("deathRateSlider")
                .setPosition(540,120)
                .setSize(200,20)
                .setRange(0,100)
                .setValue(50);

        recoveryRateSlider.addSlider("recoveryRateSlider")
                .setPosition(540,150)
                .setSize(200,20)
                .setRange(0,100)
                .setValue(50);

        transmissionRateSlider.addSlider("transmissionRateSlider")
                .setPosition(540,180)
                .setSize(200,20)
                .setRange(0,100)
                .setValue(50);

        
    }
    public void show(){
        show = true;
    }
    public void hide()
    {
        show = false;
    }
    public void toggle() {
        show = !show;
    }

    public void handleEvent(ControlEvent e) {
        if (e.isAssignableFrom(Textfield.class)) {
            String text = e.getController().getStringValue();
            System.out.println("Text entered: " + text);
            virusName = text;
        }
    }

    public void draw() {
        if(!show) return;
        p.fill(229, 229, 229, 250);
        p.rect(520, 50, 520, 220);
        
    }
}
