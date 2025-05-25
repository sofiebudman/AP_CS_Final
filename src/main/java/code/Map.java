package main.java.code;
import static main.java.code.Constants.Scale.*;
import static main.java.code.Constants.FilePaths.*;
import static main.java.code.Constants.Coordinates.*;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Map {
    private PApplet p;
 
    /* 
    Country africa;
    Country northAmerica;
    Country southAmerica;
    Country eurasia;
    Country australia;*/
    private PImage ocean;
    private ArrayList<Country> countries = new ArrayList<Country>();
    private ArrayList<City> cities = new ArrayList<City>();
    private City la; // Los Angeles

    

    public Map(PApplet p, Notification notification) {
        this.p = p;

        //load the images
     
        ocean = p.loadImage(OCEAN_PATH);
     
  

        //add coutnries to the list
        countries.add(new Country("Africa", AFRICA_OPEN_PATH, AFRICA_CLOSED_PATH, p));
        countries.add(new Country("North America", NORTH_AMERICA_OPEN_PATH, NORTH_AMERICA_CLOSED_PATH, p));
        countries.add(new Country("South America", SOUTH_AMERICA_OPEN_PATH, SOUTH_AMERICA_CLOSED_PATH, p));
        countries.add(new Country("Eurasia", EURASIA_OPEN_PATH, EURASIA_CLOSED_PATH, p));
        countries.add(new Country("Australia", AUSTRALIA_OPEN_PATH, AUSTRALIA_CLOSED_PATH, p));

        //Start timer for all countries
        for (Country c : countries) {
            c.startTimer();
            //System.out.println("Timer Started");
        }
        
        // add cities
        la = new City("Los Angeles", LA_HORIZONTAL_SHIFT, LA_VERTICAL_SHIFT, p, false);
        cities.add(la);
        cities.add(new City("Buenos Aires", BUENOS_AIRES_HORIZONTAL_SHIFT, BUENOS_AIRES_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Cairo", CAIRO_HORIZONTAL_SHIFT, CAIRO_VERTICAL_SHIFT, p, false));
        cities.add(new City("Mumbai", MUMBAI_HORIZONTAL_SHIFT, MUMBAI_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Sydney", SYDNEY_HORIZONTAL_SHIFT, SYDNEY_VERTICAL_SHIFT,p, false));
        cities.add(new City("Mexico City", MEXICO_CITY_HORIZONTAL_SHIFT, MEXICO_CITY_VERTICAL_SHIFT, p, false));
        cities.add(new City("New York", NEW_YORK_HORIZONTAL_SHIFT, NEW_YORK_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Toronto", TORONTO_HORIZONTAL_SHIFT, TORONTO_VERTICAL_SHIFT, p, false));
        cities.add(new City("Anchorage", ANCHORAGE_HORIZONTAL_SHIFT, ANCHORAGE_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Lima", LIMA_HORIZONTAL_SHIFT, LIMA_VERTICAL_SHIFT, p, false));
        cities.add(new City("Rio de Janeiro", RIO_DJ_HORIZONTAL_SHIFT, RIO_DJ_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Bogota", BOGOTA_HORIZONTAL_SHIFT, BOGOTA_VERTICAL_SHIFT, p, false));
        cities.add(new City("Santiago", SANTIAGO_HORIZONTAL_SHIFT, SANTIAGO_VERTICAL_SHIFT, p, false));
        cities.add(new City("Tokyo", TOKYO_HORIZONTAL_SHIFT, TOKYO_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Delhi", DELHI_HORIZONTAL_SHIFT, DELHI_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Shanghai", SHANGHAI_HORIZONTAL_SHIFT, SHANGHAI_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Beijing", BEIJING_HORIZONTAL_SHIFT, BEIJING_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Tehran", TEHRAN_HORIZONTAL_SHIFT, TEHRAN_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Istanbul", ISTANBUL_HORIZONTAL_SHIFT, ISTANBUL_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Bangkok", BANGKOK_HORIZONTAL_SHIFT, BANGKOK_VERTICAL_SHIFT,  p, false));
        cities.add(new City("London", LONDON_HORIZONTAL_SHIFT, LONDON_VERTICAL_SHIFT, p, false));
        cities.add(new City("Paris", PARIS_HORIZONTAL_SHIFT, PARIS_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Moscow", MOSCOW_HORIZONTAL_SHIFT, MOSCOW_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Berlin", BERLIN_HORIZONTAL_SHIFT, BERLIN_VERTICAL_SHIFT, p, false));
        cities.add(new City("Jakarta", JAKARTA_HORIZONTAL_SHIFT, JAKARTA_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Riyadh", RIYADH_HORIZONTAL_SHIFT, RIYADH_VERTICAL_SHIFT, p, false));
        cities.add(new City("Lagos", LAGOS_HORIZONTAL_SHIFT, LAGOS_VERTICAL_SHIFT,  p, false)); // Already in your example
        cities.add(new City("Kinshasa", KINSHASA_HORIZONTAL_SHIFT, KINSHASA_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Dar es Salaam", DES_HORIZONTAL_SHIFT, DES_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Cape Town", CAPE_TOWN_HORIZONTAL_SHIFT, CAPE_TOWN_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Melbourne", MELBOURNE_HORIZONTAL_SHIFT, MELBOURNE_VERTICAL_SHIFT,  p, false));
        cities.add(new City("Perth", PERTH_HORIZONTAL_SHIFT, PERTH_VERTICAL_SHIFT, p, false));

        

       
    }
    public void start(){
        la.becomeInfected(); // Example of starting the infection in Los Angeles
    }
    public void drawCity(){
        for (City city : cities) {
            //xcity.update(new Virus(), false); // Update each city with current virus state
            city.render();
        }
    }

    public void drawContinents() {
        for (Country c : countries) {
            c.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);

        }
       
    }

    public void drawOcean() {
       
        p.image(ocean, HORIZONTAL_SHIFT, 50);
        
    }
    public void handleMousePressed(int mouseX, int mouseY) {
        for(   Country c : countries) {
            c.handleMousePressed(mouseX, mouseY);
        }
  
    }



    public ArrayList<City> getCities() {
        return cities;
    }
}
