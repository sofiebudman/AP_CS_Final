package main.java.code;

import java.util.ArrayList;

import processing.core.PApplet;

public class Log {

    private static ArrayList<String> logMessages = new ArrayList<>();

    public static void addMessage(String message) {
        logMessages.add(message);
    }

    public static void displayLog(PApplet p){
        
    }
    
}
