package main.java.code;
import static main.java.code.Constants.Fonts.*;

import processing.core.PApplet;
import processing.core.PFont;

public class Notification {
    private static PApplet p;
    private static String currentMessage;
    private static long displayStartTime;
    private static final long DISPLAY_DURATION = 3000; // Display for 3 seconds
    
    private PFont notificationFont;
    private String notificationText;
    private boolean isVisible;
    private int alpha;
    private long fadeInStartTime;
    private long fadeOutStartTime;
    private long displayDuration;
    
    public Notification(PApplet p) {
        this.p = p;
        FontManager.initialize(p);
        this.notificationFont = FontManager.getFont("FARRO_REGULAR_20");
        this.notificationText = "";
        this.isVisible = false;
        this.alpha = 0;
        this.fadeInStartTime = 0;
        this.fadeOutStartTime = 0;
        this.displayDuration = 3000; // 3 seconds
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
