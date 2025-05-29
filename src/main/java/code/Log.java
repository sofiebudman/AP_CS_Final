package main.java.code;

import static main.java.code.Constants.Fonts.*;

import processing.core.PApplet;
import processing.core.PFont;

import controlP5.*;

import java.util.ArrayList;

public class Log {

    private static boolean showLog;
    private static Textarea logText;
    private static ArrayList<String> logMessages = new ArrayList<>();

    private static PFont logFont;
    private static PApplet p;
    private static ControlP5 cp5;

    public Log(PApplet p) {
        Log.p = p;
        cp5 = new ControlP5(p);

        // Load a monospaced font for log
        logFont = p.createFont("Courier New", 12);

        // Create a scrollable Textarea
        logText = cp5.addTextarea("logText")
                .setPosition(20, 70)
                .setSize(260, 610)
                .setFont(logFont)
                .setLineHeight(14)
                .setColorBackground(p.color(255)) // white background
                .setColor(p.color(0)) // text color
                .setColorForeground(p.color(0)) // border color
                .setText(getLogMessage())
                .setVisible(false); // initially hidden
    }

    public static void toggle() {
        showLog = !showLog;
        if (logText != null) {
            logText.setVisible(showLog);
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
            logText.setVisible(false);
        }
    }

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
