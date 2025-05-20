package main.java.code;
import static main.java.code.Constants.Scale.*;
import static main.java.code.Constants.FilePaths.*;
import static main.java.code.Constants.Coordinates.*;

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
    private ArrayList<City> cities = new ArrayList<City>();

    

    public Map(PApplet p, Notification notification) {
        this.p = p;
        //showMap = false;

        
        
        // Load all images in constructor
        ocean = p.loadImage(OCEAN_PATH);
       
        
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

        

        // Add cities with their positions relative to the map
        // North America cities
        cities.add(new City("LA", LA_HORIZONTAL_SHIFT, LA_VERTICAL_SHIFT, 8000000, p)); // New York
        cities.add(new City("BA", BUENOS_AIRES_HORIZONTAL_SHIFT, BUENOS_AIRES_VERTICAL_SHIFT, 3000000, p)); // Buenos Aires
        cities.add(new City("Cairo", CAIRO_HORIZONTAL_SHIFT, CAIRO_VERTICAL_SHIFT, 4000000, p)); // Cairo
        /*cities.add(new City(HORIZONTAL_SHIFT + 100, VERTICAL_SHIFT + 200, 4000000, p)); // Chicago
        cities.add(new City(HORIZONTAL_SHIFT + 300, VERTICAL_SHIFT + 250, 6000000, p)); // Los Angeles

        // South America cities
        cities.add(new City(HORIZONTAL_SHIFT + 250, VERTICAL_SHIFT + 400, 5000000, p)); // São Paulo
        cities.add(new City(HORIZONTAL_SHIFT + 200, VERTICAL_SHIFT + 450, 3000000, p)); // Buenos Aires

        // Europe cities
        cities.add(new City(HORIZONTAL_SHIFT + 500, VERTICAL_SHIFT + 200, 7000000, p)); // London
        cities.add(new City(HORIZONTAL_SHIFT + 550, VERTICAL_SHIFT + 250, 6000000, p)); // Paris

        // Africa cities
        cities.add(new City(HORIZONTAL_SHIFT + 500, VERTICAL_SHIFT + 350, 4000000, p)); // Cairo
        cities.add(new City(HORIZONTAL_SHIFT + 450, VERTICAL_SHIFT + 400, 3000000, p)); // Lagos

        // Asia cities
        cities.add(new City(HORIZONTAL_SHIFT + 700, VERTICAL_SHIFT + 250, 9000000, p)); // Tokyo
        cities.add(new City(HORIZONTAL_SHIFT + 650, VERTICAL_SHIFT + 300, 8000000, p)); // Shanghai

        // Australia cities
        cities.add(new City(HORIZONTAL_SHIFT + 800, VERTICAL_SHIFT + 400, 5000000, p)); // Sydney
        cities.add(new City(HORIZONTAL_SHIFT + 750, VERTICAL_SHIFT + 450, 3000000, p)); // Melbourne*/
    }
    public void drawCity(){
        for (City city : cities) {
            city.render();
        }
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



    public ArrayList<City> getCities() {
        return cities;
    }
}
