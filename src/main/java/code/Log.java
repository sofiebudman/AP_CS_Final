package main.java.code;

import static main.java.code.Constants.Fonts.*;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

public class Log {
    private static boolean showLog;

    private static ArrayList<String> logMessages = new ArrayList<>();
    

    public static void toggle(){
        showLog = !showLog;

    }

    public static String getLogMessage(int index) {
        String out = "";
        for(String s: logMessages){
            out += s + "\n";
        }
        return out;
    }
    public static void hide(){
        showLog = false;
    }

    public static void addMessage(String message, int day) {
        logMessages.add("Day " + day + ": " +message);
    }

    public static void displayLog(PApplet p){
        if (!showLog) return;
        p.noStroke();
        p.fill(255, 184, 119);
        p.rect(0, 50, 300, 650);
       
        
        p.fill(255);
        p.rect(10,60, 280, 630);
       
        p.fill(0);
        //p.textSize(5);
        PFont mono = p.createFont(FARRO_REGULAR_FONT_PATH, 15, true);
        p.textFont(mono);
        PFont m = p.createFont(FARRO_REGULAR_FONT_PATH, 20, true);
        p.textFont(m);
        p.text(getLogMessage(0), 20, 120);

     

    }
    
}
