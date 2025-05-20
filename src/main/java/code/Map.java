package main.java.code;
import static main.java.code.Constants.Scale.*;
import static main.java.code.Constants.FilePaths.*;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Map {
    private PApplet p;
    //private boolean showMap;
    Country africa;
    Country northAmerica;
    Country southAmerica;
    Country eurasia;
    Country australia;
    private PImage ocean;
    private ArrayList<Country> countries = new ArrayList<Country>();

    public Map(PApplet p, Notification notification) {
        this.p = p;
        //showMap = false;
        
        // Load all images in constructor
        try {
            ocean = p.loadImage(OCEAN_PATH);
            if (ocean == null) {
                System.err.println("Failed to load ocean image from: " + OCEAN_PATH);
            } else {
                ocean.resize(WIDTH_SCALE, 0);
            }
        } catch (Exception e) {
            System.err.println("Error loading ocean image: " + e.getMessage());
        }
        
        africa = new Country("Africa", AFRICA_OPEN_PATH, AFRICA_CLOSED_PATH, p);
        northAmerica = new Country("North America", NORTH_AMERICA_OPEN_PATH, NORTH_AMERICA_CLOSED_PATH, p);
        southAmerica = new Country("South America", SOUTH_AMERICA_OPEN_PATH, SOUTH_AMERICA_CLOSED_PATH, p);
        eurasia = new Country("Eurasia", EURASIA_OPEN_PATH, EURASIA_CLOSED_PATH, p);
        australia = new Country("Australia", AUSTRALIA_OPEN_PATH, AUSTRALIA_CLOSED_PATH, p);
        countries.add(africa);
        countries.add(northAmerica);
        countries.add(southAmerica);
        countries.add(eurasia);
        countries.add(australia);
    }

    public void drawContinents() {
        for (Country c : countries) {
            c.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);

        }
        // draw city
        /* 
        africa.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);
        northAmerica.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);
        southAmerica.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);
        eurasia.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);
        australia.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);*/
    }

    public void drawOcean() {
       
        p.image(ocean, HORIZONTAL_SHIFT, 50);
        
    }

    public void handleMousePressed(int mouseX, int mouseY) {
        africa.handleMousePressed(mouseX, mouseY);
        northAmerica.handleMousePressed(mouseX, mouseY);
        southAmerica.handleMousePressed(mouseX, mouseY);
        eurasia.handleMousePressed(mouseX, mouseY);
        australia.handleMousePressed(mouseX, mouseY);
    }
}
