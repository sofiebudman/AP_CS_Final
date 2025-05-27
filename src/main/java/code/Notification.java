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
        this.p = p;
        currentMessage = "";
        displayStartTime = 0;
    }

    public static void newNotification(String message) {
        currentMessage = message;
        displayStartTime = System.currentTimeMillis();
        Log.addMessage(message, Map.getDay()); // Assuming Map.getDay() returns the current day

    }

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
                currentMessage = ""; // Clear the message after duration
            }
        }
    }

 

    
}
