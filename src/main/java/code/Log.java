package main.java.code;

import static main.java.code.Constants.Fonts.*;

import processing.core.PApplet;
import processing.core.PFont;

import controlP5.*;

import java.util.ArrayList;

public class Log {

    private static boolean showLog;
    private static Textarea logText;
    private static ArrayList<String> logMessages = new ArrayList<>(); //arraylist of all log messages

    private static PFont logFont;
    private static PApplet p;
    private static ControlP5 cp5;

    public Log(PApplet p) {
        Log.p = p;
        cp5 = new ControlP5(p);


        logFont = p.createFont("Courier New", 12);

        //creates scrollable text area using contorl p5
        logText = cp5.addTextarea("logText")
                .setPosition(20, 70)
                .setSize(260, 610)
                .setFont(logFont)
                .setLineHeight(14)
                .setColorBackground(p.color(255)) 
                .setColor(p.color(0)) 
                .setColorForeground(p.color(0)) 
                .setText(getLogMessage())
                .setVisible(false); 
    }

    //toggle visibility
    public static void toggle() {
        showLog = !showLog;
        if (logText != null) {
            logText.setVisible(showLog);
        }
    }
    /**
     * add each log message to a long string 
       */ 
    
    public static String getLogMessage() {
        String out = "";
        for (String s : logMessages) {
            out += (s + "\n");
        }
        return out;
    }

    public static void hide() {
        showLog = false;
        if (logText != null) {
            logText.setVisible(false);
        }
    }


    /**
     * 
     * add messages to the scrollable text, will exclude certain messages
     */
    public static void addMessage(String message, int day) {
        if (message.toLowerCase().contains("wait") &&
            message.toLowerCase().contains("days to toggle this border")) {
            return;
        }
        logMessages.add("Day " + day + ": " + message);
        if (logText != null) {
            logText.setText(getLogMessage());
        }
    }
    /**
     * Display log when applicable
     */
    public static void displayLog() {
        if (!showLog) return;

        // Draw background
        p.noStroke();
        p.fill(255, 184, 119);
        p.rect(0, 50, 300, 650);

        p.fill(255);
        p.rect(10, 60, 280, 630);
    }
}
