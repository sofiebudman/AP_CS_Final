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

        

        cities.add(new City("Los Angeles", LA_HORIZONTAL_SHIFT, LA_VERTICAL_SHIFT, 4000000, p));
        cities.add(new City("Buenos Aires", BUENOS_AIRES_HORIZONTAL_SHIFT, BUENOS_AIRES_VERTICAL_SHIFT, 2900000, p));
        cities.add(new City("Cairo", CAIRO_HORIZONTAL_SHIFT, CAIRO_VERTICAL_SHIFT, 20000000, p));
        cities.add(new City("Mumbai", MUMBAI_HORIZONTAL_SHIFT, MUMBAI_VERTICAL_SHIFT, 20400000, p));
        cities.add(new City("Sydney", SYDNEY_HORIZONTAL_SHIFT, SYDNEY_VERTICAL_SHIFT, 5312000, p));
        cities.add(new City("Mexico City", MEXICO_CITY_HORIZONTAL_SHIFT, MEXICO_CITY_VERTICAL_SHIFT, 9200000, p));
        cities.add(new City("New York", NEW_YORK_HORIZONTAL_SHIFT, NEW_YORK_VERTICAL_SHIFT, 8400000, p));
        cities.add(new City("Toronto", TORONTO_HORIZONTAL_SHIFT, TORONTO_VERTICAL_SHIFT, 2800000, p));
        cities.add(new City("Anchorage", ANCHORAGE_HORIZONTAL_SHIFT, ANCHORAGE_VERTICAL_SHIFT, 290000, p));
        cities.add(new City("Lima", LIMA_HORIZONTAL_SHIFT, LIMA_VERTICAL_SHIFT, 8800000, p));
        cities.add(new City("Rio de Janeiro", RIO_DJ_HORIZONTAL_SHIFT, RIO_DJ_VERTICAL_SHIFT, 6700000, p));
        cities.add(new City("Bogota", BOGOTA_HORIZONTAL_SHIFT, BOGOTA_VERTICAL_SHIFT, 7700000, p));
        cities.add(new City("Santiago", SANTIAGO_HORIZONTAL_SHIFT, SANTIAGO_VERTICAL_SHIFT, 5700000, p));
        cities.add(new City("Tokyo", TOKYO_HORIZONTAL_SHIFT, TOKYO_VERTICAL_SHIFT, 37400000, p));
        cities.add(new City("Delhi", DELHI_HORIZONTAL_SHIFT, DELHI_VERTICAL_SHIFT, 31800000, p));
        cities.add(new City("Shanghai", SHANGHAI_HORIZONTAL_SHIFT, SHANGHAI_VERTICAL_SHIFT, 26300000, p));
        cities.add(new City("Beijing", BEIJING_HORIZONTAL_SHIFT, BEIJING_VERTICAL_SHIFT, 21800000, p));
        cities.add(new City("Tehran", TEHRAN_HORIZONTAL_SHIFT, TEHRAN_VERTICAL_SHIFT, 8800000, p));
        cities.add(new City("Istanbul", ISTANBUL_HORIZONTAL_SHIFT, ISTANBUL_VERTICAL_SHIFT, 15400000, p));
        cities.add(new City("Bangkok", BANGKOK_HORIZONTAL_SHIFT, BANGKOK_VERTICAL_SHIFT, 8300000, p));
        cities.add(new City("London", LONDON_HORIZONTAL_SHIFT, LONDON_VERTICAL_SHIFT, 8900000, p));
        cities.add(new City("Paris", PARIS_HORIZONTAL_SHIFT, PARIS_VERTICAL_SHIFT, 11000000, p));
        cities.add(new City("Moscow", MOSCOW_HORIZONTAL_SHIFT, MOSCOW_VERTICAL_SHIFT, 12600000, p));
        cities.add(new City("Berlin", BERLIN_HORIZONTAL_SHIFT, BERLIN_VERTICAL_SHIFT, 3700000, p));
        cities.add(new City("Jakarta", JAKARTA_HORIZONTAL_SHIFT, JAKARTA_VERTICAL_SHIFT, 10800000, p));
        cities.add(new City("Riyadh", RIYADH_HORIZONTAL_SHIFT, RIYADH_VERTICAL_SHIFT, 7700000, p));
        cities.add(new City("Lagos", LAGOS_HORIZONTAL_SHIFT, LAGOS_VERTICAL_SHIFT, 30000000, p)); // Already in your example
        cities.add(new City("Kinshasa", KINSHASA_HORIZONTAL_SHIFT, KINSHASA_VERTICAL_SHIFT, 15000000, p));
        cities.add(new City("Dar es Salaam", DES_HORIZONTAL_SHIFT, DES_VERTICAL_SHIFT, 7200000, p));
        cities.add(new City("Cape Town", CAPE_TOWN_HORIZONTAL_SHIFT, CAPE_TOWN_VERTICAL_SHIFT, 4600000, p));
        cities.add(new City("Melbourne", MELBOURNE_HORIZONTAL_SHIFT, MELBOURNE_VERTICAL_SHIFT, 5000000, p));
        cities.add(new City("Perth", PERTH_HORIZONTAL_SHIFT, PERTH_VERTICAL_SHIFT, 2100000, p));


       
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
