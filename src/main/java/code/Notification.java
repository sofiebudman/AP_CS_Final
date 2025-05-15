package src.main.java.code;

import processing.core.PApplet;

public class Notification {
    private PApplet p;
    //arraylist of strings to display perchance?
    public Notification(PApplet p) {
        this.p = p;
    }

    public void newNotification(String message){
        p.fill(0,0,0,180);
        p.rect(900,50,200,30);
        p.fill(255, 255, 255);
        p.text(message, 800, 80);


    }



    //have a custon message to display a notification, make it an arraylist 

    
}
