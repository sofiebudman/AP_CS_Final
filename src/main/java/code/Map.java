package main.java.code;
import static main.java.code.Constants.Scale.*;
import static main.java.code.Constants.FilePaths.*;
import static main.java.code.Constants.Coordinates.*;

import static main.java.code.Constants.Fonts.*;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

import java.util.Timer;
import java.util.TimerTask;

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

    private Timer timer;
    private static int days = 0;

    // Add font fields
    private static PFont cityInfoFont;
    private static PFont cityNameFont;
    private static PFont dayCounterFont;
    private static boolean isInitialized = false;

    public static int getDay(){
            return days;    
    }


    public Map(PApplet p, Notification notification) {
        this.p = p;
        initialize();

        //Timer
        this.timer = new Timer();

        //load the images
     
        ocean = p.loadImage(OCEAN_PATH);
     
  

        //add coutnries to the list
        countries.add(new Country("North America", 0, NORTH_AMERICA_OPEN_PATH, NORTH_AMERICA_CLOSED_PATH, p)); //0
        countries.add(new Country("South America", 1, SOUTH_AMERICA_OPEN_PATH, SOUTH_AMERICA_CLOSED_PATH, p)); //1
        countries.add(new Country("Eurasia", 2, EURASIA_OPEN_PATH, EURASIA_CLOSED_PATH, p)); //2
        countries.add(new Country("Africa", 3, AFRICA_OPEN_PATH, AFRICA_CLOSED_PATH, p)); //3 
        countries.add(new Country("Australia", 4, AUSTRALIA_OPEN_PATH, AUSTRALIA_CLOSED_PATH, p)); //4

        //Start timer for all countries
        for (Country c : countries) {
            c.startTimer();
            //System.out.println("Timer Started");
        }

        

        
        // add cities
        //Starters
        cities.add(new City("Los Angeles", LA_HORIZONTAL_SHIFT, LA_VERTICAL_SHIFT,0,  p, false, 3900000));
        cities.add(new City("Buenos Aires", BUENOS_AIRES_HORIZONTAL_SHIFT, BUENOS_AIRES_VERTICAL_SHIFT,  1, p, false, 3000000));
        cities.add(new City("Mumbai", MUMBAI_HORIZONTAL_SHIFT, MUMBAI_VERTICAL_SHIFT,  2, p, false, 10000000));
        cities.add(new City("Cairo", CAIRO_HORIZONTAL_SHIFT, CAIRO_VERTICAL_SHIFT, 3, p, false, 8000000));
        cities.add(new City("Sydney", SYDNEY_HORIZONTAL_SHIFT, SYDNEY_VERTICAL_SHIFT,4, p, false, 5200000));
        //All Others
        cities.add(new City("Mexico City", MEXICO_CITY_HORIZONTAL_SHIFT, MEXICO_CITY_VERTICAL_SHIFT, 0, p, false, 9200000));
        cities.add(new City("New York", NEW_YORK_HORIZONTAL_SHIFT, NEW_YORK_VERTICAL_SHIFT, 0, p, false, 8800000));
        cities.add(new City("Toronto", TORONTO_HORIZONTAL_SHIFT, TORONTO_VERTICAL_SHIFT, 0, p, false, 2700000));
        cities.add(new City("Anchorage", ANCHORAGE_HORIZONTAL_SHIFT, ANCHORAGE_VERTICAL_SHIFT, 0, p, false, 300000));
        cities.add(new City("Lima", LIMA_HORIZONTAL_SHIFT, LIMA_VERTICAL_SHIFT, 1, p, false, 6300000));
        cities.add(new City("Rio de Janeiro", RIO_DJ_HORIZONTAL_SHIFT, RIO_DJ_VERTICAL_SHIFT, 1, p, false, 6500000));
        cities.add(new City("Bogota", BOGOTA_HORIZONTAL_SHIFT, BOGOTA_VERTICAL_SHIFT, 1, p, false, 7900000));
        cities.add(new City("Santiago", SANTIAGO_HORIZONTAL_SHIFT, SANTIAGO_VERTICAL_SHIFT,1, p, false, 3200000));
        cities.add(new City("Tokyo", TOKYO_HORIZONTAL_SHIFT, TOKYO_VERTICAL_SHIFT,  2, p, false, 10000000));
        cities.add(new City("Delhi", DELHI_HORIZONTAL_SHIFT, DELHI_VERTICAL_SHIFT,  2, p, false, 10000000));
        cities.add(new City("Shanghai", SHANGHAI_HORIZONTAL_SHIFT, SHANGHAI_VERTICAL_SHIFT,  2, p, false, 10000000));
        cities.add(new City("Beijing", BEIJING_HORIZONTAL_SHIFT, BEIJING_VERTICAL_SHIFT,  2, p, false, 5400000));
        cities.add(new City("Tehran", TEHRAN_HORIZONTAL_SHIFT, TEHRAN_VERTICAL_SHIFT, 2,  p, false, 9000000));
        cities.add(new City("Istanbul", ISTANBUL_HORIZONTAL_SHIFT, ISTANBUL_VERTICAL_SHIFT,  2, p, false, 6500000));
        cities.add(new City("Bangkok", BANGKOK_HORIZONTAL_SHIFT, BANGKOK_VERTICAL_SHIFT,  2, p, false, 2800000));
        cities.add(new City("London", LONDON_HORIZONTAL_SHIFT, LONDON_VERTICAL_SHIFT, 2, p, false, 4300000));
        cities.add(new City("Paris", PARIS_HORIZONTAL_SHIFT, PARIS_VERTICAL_SHIFT,  2, p, false, 1700000));
        cities.add(new City("Moscow", MOSCOW_HORIZONTAL_SHIFT, MOSCOW_VERTICAL_SHIFT, 2,  p, false, 8400000));
        cities.add(new City("Berlin", BERLIN_HORIZONTAL_SHIFT, BERLIN_VERTICAL_SHIFT, 2, p, false, 1200000));
        cities.add(new City("Jakarta", JAKARTA_HORIZONTAL_SHIFT, JAKARTA_VERTICAL_SHIFT, 2,  p, false, 7600000));
        cities.add(new City("Riyadh", RIYADH_HORIZONTAL_SHIFT, RIYADH_VERTICAL_SHIFT, 2, p, false, 5500000));
        cities.add(new City("Lagos", LAGOS_HORIZONTAL_SHIFT, LAGOS_VERTICAL_SHIFT, 3, p, false, 9300000));
        cities.add(new City("Kinshasa", KINSHASA_HORIZONTAL_SHIFT, KINSHASA_VERTICAL_SHIFT,  3, p, false, 3300000));
        cities.add(new City("Dar es Salaam", DES_HORIZONTAL_SHIFT, DES_VERTICAL_SHIFT,  3, p, false, 7600000));
        cities.add(new City("Cape Town", CAPE_TOWN_HORIZONTAL_SHIFT, CAPE_TOWN_VERTICAL_SHIFT, 3,  p, false, 2000000));
        cities.add(new City("Melbourne", MELBOURNE_HORIZONTAL_SHIFT, MELBOURNE_VERTICAL_SHIFT,  4, p, false, 5300000));
        cities.add(new City("Perth", PERTH_HORIZONTAL_SHIFT, PERTH_VERTICAL_SHIFT, 4, p, false, 1400000));

        

       
    }

    private void initialize() {
    
                cityInfoFont = p.createFont(FARRO_REGULAR_FONT_PATH, 12);
                cityNameFont = p.createFont(FARRO_REGULAR_FONT_PATH, 20);
                dayCounterFont = p.createFont(FARRO_REGULAR_FONT_PATH, 20);

               
              
         
    }

    public void start(){
        String startingCountry = Virus.getStartingCountry();
        String [] options = {
            "North America",
            "South America",
            "Eurasia",
            "Africa",
            "Australia"
        };
        String [] startingCities = {"Los Angeles", "Buenos Aires", "Mumbai", "Cairo", "Sydney"};

        String startCity = "";
        for (int i = 0; i < options.length; i++) {
            if (startingCountry.equals(options[i])) {
                startCity = startingCities[i];
            }
        }

        for (int i = 0; i < cities.size(); i++) {
            String n = cities.get(i).getName();
            if (n.equals(startCity)) {
                cities.get(i).infect();
            }

        }
    }
     public String abb(int x) {

        if (x >= 1000000) {
            return Math.round((x / 1_000_000.0) * 100.0) / 100.0 + " M";
        } else if (x >= 1000) {
            return Math.round((x / 1_000.0) * 100.0) / 100.0 + " K";
        } else {
            return ""+x;
        }
    }

    public String percentages (int x, int total) {
        if( total == 0) {
            return "0.00";
        }
        double p = (double) x / total;
        return ""+Math.round(p * 10000.0) / 100.0;
        
        
    }
    

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {




                
                //Update days
                days++;
                
                
                //Delete empty cities
                for (int i = cities.size()-1; i >=0; i--) {
                    City c = cities.get(i);
                    if(c.getPopulationImmune()+c.getPopulationInfected()+c.getPopulationVulnerable() == 0) {
                        Notification.newNotification(c.getName() + " Perished!");
                        cities.remove(i);
                    }
                }

                //-----------------INFECTION SPREADING---------------------//
                //Loop through all cities
                for (int i = 0; i < cities.size(); i++) {
                    //Current city
                    City cur = cities.get(i);
                    //Only infect others if infected
                    if (cur.getPopulationInfected() > 0) {

                        //Change vaccine eligibility for country
                        if (!countries.get(cur.getCountryNum()).getHasVirusInfo()) {
                            countries.get(cur.getCountryNum()).setHasVirusInfo(true);
                        }

                        // loop through other cities
                        for (int j = 0; j < cities.size(); j++) {
                            //Make sure not infecting same city and that the cities are either in the same country or both are open countries
                            
                            //SOFIE WRITE getBorder method for country!
                            City other = cities.get(j);
                            if (i != j && (cur.getCountryNum() == other.getCountryNum() || (countries.get(cur.getCountryNum()).hasOpenBorder() &&  countries.get(other.getCountryNum()).hasOpenBorder()) )) {
                                
                                //Metrics to base infection off of
                                int distance = (int) p.dist(cur.getPosX(), cur.getPosY(), other.getPosX(), other.getPosY());
                                int infected = cur.getPopulationInfected();
                                
                                //Infection chance score 
                                
                                int score = (int) Math.min((cur.getPopulationInfected() / 100000.0) * 2 / Math.pow(distance, 0.05), 100.0);
                                
                                //Generate chance that other city will be infected
                                int chance = (int) (Math.random() * 100);
                                
                                
                                if (chance < score && distance <= 400) {
                                    if (other.getPopulationInfected()==0 && other.getPopulationImmune() == 0) {
                                        Notification.newNotification(other.getName() + " Infected!");
                                    }
                                    other.infect();
                                }
                            }
                        }
                    }
                }

                //-------------------------------------------------//

                //------------VACCINE-----------------//
                
                for (Country place : countries) {
                    if (place.getHasVirusInfo() && !place.checkHasVaccine()) {
                        
                        if (Math.random() < 0.005) {
                            Notification.newNotification(place.getName()+ " Discovered a Vaccine!");
                            place.giveVaccine();
                        }
                    }
                    if (place.checkHasVaccine() && days > 30) {
                        boolean give = true;
                        for (City c : cities) {
                            if (c.getCountryNum() == place.getCountryNum() && c.getPopulationInfected() > 0) {
                                give = false;
                                break;
                            }
                        }
                        if (give) {
                            boolean b = false;
                            for (Country u : countries) {
                                if (!u.checkHasVaccine() && u.hasOpenBorder())  {
                                    u.giveVaccine();
                                    b = true;
                                }
                            }
                            if (b) {
                                Notification.newNotification("All Open Countries Received a Vaccine!");
                            }
                        }
                    }
                }
                



                //------------------------------------//

                //-------------MUTATION----------------//
                if (Math.random() < (double) Virus.getMutationRate()) {
                    Notification.newNotification("Virus Mutated");
                    Virus.mutate();
                    for (Country c : countries) {
                        c.setHasVirusInfo(false);
                        c.loseVaccine();
                    }
                    for (City c : cities) {
                        c.wipeImmunity();
                    }
                }

                //-------------------------------------//


                //Update all cities
                for (City city : cities) {
                    boolean v = countries.get(city.getCountryNum()).checkHasVaccine();
                    city.update(v, countries.get(city.getCountryNum()).hasOpenBorder());
                }
                
                
            }
        }, 0, 1000);  //1000 = 1 second
    }

    public void drawCity(){
        //Get Rid of Cities with No People
        for (int i = cities.size()-1; i >=0; i--) {
            City c = cities.get(i);
            if(c.getPopulationImmune()+c.getPopulationInfected()+c.getPopulationVulnerable() == 0) {
                Notification.newNotification(c.getName() + " Perished!");
                cities.remove(i);
            }
        }
        for (City city : cities) {
            city.render(); 
        }
        //Hover for City Information
        for (City city : cities) {
            if (p.dist(p.mouseX, p.mouseY, city.getPosX(), city.getPosY()) <= city.getCityRadius()) {
                p.fill(100, 100, 100, 180);
                int w = 180;
                int h = 70;

                int textX = 0;
                if (city.getPosX() > 1260) {
                    p.rect(city.getPosX() - 135, city.getPosY() - 10 - h, w, h);
                    textX = city.getPosX()-130;
                } else {
                    p.rect(city.getPosX() , city.getPosY() - 10 - h, w, h);
                    textX = city.getPosX()+5;
                }

                
                int textY = city.getPosY() -h -2 ;  

                p.textFont(cityInfoFont);

                int totalPopulation = city.getPopulationVulnerable() + city.getPopulationInfected() + city.getPopulationImmune();
                String vulnerablePercentage = percentages(city.getPopulationVulnerable(), totalPopulation);
                String infectedPercentage = percentages(city.getPopulationInfected(), totalPopulation);
                String immunePercentage = percentages(city.getPopulationImmune(), totalPopulation);
                
                p.fill(255);
                p.textAlign(PConstants.LEFT, PConstants.TOP);
                p.text(city.getName() + " Pop: " + abb(totalPopulation), textX, textY);
                p.text("Vulnerable: "+vulnerablePercentage+"%", textX, textY + 14);
                p.text("Infected: "+infectedPercentage+"%", textX, textY + 28);
                p.text("Immune: "+immunePercentage+"%", textX, textY + 42);
                p.textAlign(PConstants.LEFT, PConstants.BASELINE);

                p.textFont(cityNameFont);
            }
        }

        //Day Counter
        p.fill(255);
       
        p.textFont(dayCounterFont);
        p.text("Day: "+days, 320, 35);

        String e = "Vaccines: ";
        for (Country c : countries) {
            p.textFont(dayCounterFont);
            e+=c.getName().substring(0, 2);
            if(c.checkHasVaccine()) {
                e+= " Y";
            } else {
                e+= " N ";
            }
        }
        p.text(e, 500, 35);
    }

    public void drawContinents() {
        for (Country c : countries) {
            c.drawImage(HORIZONTAL_SHIFT, VERTICAL_SHIFT);

        }
       
    }

    public void drawOcean() {
       
        p.image(ocean, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
        
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
