package main.java.code;

import static main.java.code.Constants.Fonts.*;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.HashMap;

public class FontManager {
    private static HashMap<String, PFont> fonts = new HashMap<>();
    private static boolean isInitialized = false;

    public static void initialize(PApplet p) {
        if (!isInitialized) {
            // Create all fonts once
            fonts.put("FARRO_REGULAR_12", p.createFont(FARRO_REGULAR_FONT_PATH, 12, true));
            fonts.put("FARRO_REGULAR_16", p.createFont(FARRO_REGULAR_FONT_PATH, 16, true));
            fonts.put("FARRO_REGULAR_20", p.createFont(FARRO_REGULAR_FONT_PATH, 20, true));
            fonts.put("ARIAL_12", p.createFont("Arial", 12, true));
            isInitialized = true;
        }
    }

    public static PFont getFont(String key) {
        return fonts.get(key);
    }
} 