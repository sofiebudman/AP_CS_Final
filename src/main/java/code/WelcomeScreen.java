package src.main.java.code;

import controlP5.ControlP5;
import processing.core.PApplet;

public class WelcomeScreen {

    private PApplet p;
    private ControlP5 nextButton;

    private boolean showWelcome;
    public WelcomeScreen(PApplet p) {
        this.p = p;
        nextButton = new ControlP5(p);
        showWelcome = true;

        nextButton.addButton("Next")
                .setPosition(600, 500)
                .setSize(200, 50)
                .setColorBackground(p.color(0, 0, 0))
                .setColorForeground(p.color(100, 100, 100))
                .setColorActive(p.color(150, 150, 150))
                .onClick((event) -> {
                    showWelcome = false;
                    nextButton.remove("Next");
                });
    }

    public void display(){
        if(!showWelcome){
            return;
        }
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.fill(200, 200, 200);
        p.rect(80,80,1300,550);

        p.fill(230,230,230);
        p.rect(90,90,1280,530);
        
        p.fill(0, 0, 0);

        p.text("Welcome to the Virus Simulation Game!", 400, 150);

        p.textSize(18);
        p.text("This simulation models the spread of an infectious disease and its potential impact on public health, global infrastructure, and society.\n" + //
                        "You will have the ability to modify key virus parameters and observe how changes affect transmission dynamics across regions.\n" + //
                        "As part of your role, you can influence international policy — including the opening and closing of borders and trade routes — to mitigate the spread of infection.\n" + //
                        "Every decision affects the outcome. Monitor closely, act strategically.\n" + //
                        "", 120,180);

    }

   
}
