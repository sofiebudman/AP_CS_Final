package main.java.code;

import static main.java.code.Constants.Fonts.*;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import controlP5.*;

public class Log {
    private static boolean showLog;
    static ControlP5 cp5;
    static Textarea logText;
    private static ArrayList<String> logMessages = new ArrayList<>();
    private static boolean isInitialized = false;

    public static void initialize(PApplet p) {
        if (!isInitialized) {
            FontManager.initialize(p);
            cp5 = new ControlP5(p);
            logText = cp5.addTextarea("log")
                .setPosition(20, 70)
                .setSize(260, 610)
                .setLineHeight(20)
                .setColor(0)
                .setColorBackground(255)
                .setColorForeground(255)
                .setColorActive(255)
                .setFont(FontManager.getFont("ARIAL_12"))
                .setScrollActive(1)
                .setColorValue(255)  // Make text white
                .setText(getLogMessage());
            isInitialized = true;
        }
    }

    public static void toggle() {
        showLog = !showLog;
        if (logText != null) {
            if (showLog) {
                logText.show();
            } else {
                logText.hide();
            }
        }
    }

    public static String getLogMessage() {
        StringBuilder out = new StringBuilder();
        for (String s : logMessages) {
            out.append(s).append("\n");
        }
        return out.toString();
    }

    public static void hide() {
        showLog = false;
        if (logText != null) {
            logText.hide();
        }
    }

    public static void addMessage(String message, int day) {
        // Skip messages about waiting to toggle borders
        if (message.toLowerCase().contains("wait") && message.toLowerCase().contains("days to toggle this border")) {
            return;
        }
        logMessages.add("Day " + day + ": " + message);
        if (logText != null) {
            logText.setText(getLogMessage());
            // Scroll to bottom when new message is added
            logText.scroll(1);
        }
    }

    public static void displayLog(PApplet p) {
        if (!showLog) return;
        
        // Draw background
        p.noStroke();
        p.fill(255, 184, 119);
        p.rect(0, 50, 300, 650);
        
        p.fill(255);
        p.rect(10, 60, 280, 630);

        // Initialize if not done yet
        if (!isInitialized) {
            initialize(p);
        }
    }
}
