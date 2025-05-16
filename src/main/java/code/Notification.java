
package src.main.java.code;
import static src.main.java.code.Constants.*;

import processing.core.PApplet;
import processing.core.PFont;

public class Notification {
    private PApplet p;
    //arraylist of strings to display perchance?
    public Notification(PApplet p) {
        this.p = p;
    }

    public void newNotification(String message){
        p.fill(0,0,0,180);
        p.rect(900,55,260,30);
        p.fill(255, 255, 255);

        PFont mono = p.createFont(ELECTROLIZ_FONT_PATH, 20);
        p.textFont(mono);
        p.text(message, 905, 80);


    }



    //have a custon message to display a notification, make it an arraylist 

    
}
