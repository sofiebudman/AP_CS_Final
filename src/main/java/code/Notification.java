package main.java.code;
import static main.java.code.Constants.Fonts.*;

import processing.core.PApplet;
import processing.core.PFont;

public class Notification {
    private static PApplet p;
    private static String currentMessage;
    private static long displayStartTime;
    private static final long DISPLAY_DURATION = 3000; // Display for 3 seconds
    
    public Notification(PApplet p) {
        Notification.p = p;
        currentMessage = "";
        displayStartTime = 0;
    }
    /*
     * Creates a new notification witht he miessage
     * Sets the display time to 30 seconds
     * Includes the current day
     */
    public static void newNotification(String message) {
        currentMessage = message;
        displayStartTime = System.currentTimeMillis();
        Log.addMessage(message, Map.getDay());  //adds the message to log

    }


    /**
     * Displays current message if it's valid
     * ensures that message has been shown for under 30 seconds
     * Draws the notification
     */
    public static void display() {
        if (currentMessage != null && !currentMessage.isEmpty()) {
           long currentTime = System.currentTimeMillis();
            if (currentTime - displayStartTime < DISPLAY_DURATION) {
                p.fill(0, 0, 0, 180);
                p.rect(1100, 55, 330, 30);
                p.fill(255, 255, 255);

                PFont mono = p.createFont(FARRO_REGULAR_FONT_PATH, 16);
                p.textFont(mono);
                p.text(currentMessage, 1115, 77);
            } else {
                currentMessage = ""; 
            }
        }
    }

 

    
}