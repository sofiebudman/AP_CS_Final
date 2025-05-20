package main.java.code;

import processing.core.PApplet;

public class City {
    private int populationVulnerable;
    private int populationInfected;
    private int populationImmune;

    private int posX;
    private int posY;
    private int countryNum;

    //TODO: notification when virus mutates

    private double spreadRadius; // invisible circle that represents how far it can spread
    private double immunityRadius; // blue circle that represents how immune a populaiton is
    private double cityRadius; //radius that represents the size of a city


    
    private PApplet p;
    private String name;

    public City (String name, int x, int y, /*int c */int pop, PApplet p) {
        this.name = name;
        populationVulnerable = pop;
        if (populationVulnerable > 10000000) {
            populationVulnerable = 10000000;
        }
        populationInfected = 0;
        populationImmune = 0;
        posX = x;
        posY = y;
        //countryNum = c;
        this.p = p;
        
        // Initialize radius values
        cityRadius = 20; // Base size of the city
        spreadRadius = cityRadius * 1.5; // Slightly larger than city radius
        immunityRadius = cityRadius; // Same size as city radius
    }

    



    //logic
    /*
     * - If a countries borders are open and its touchign the spread circle of another city, that city begins getting infected
     * - Borders can only change state every 30 seconds (need a timer for each individual country)
     */
    public void render() {
        // Draw immunity circle (blue)
        p.fill(0, 0, 255, 100);
        p.circle(posX, posY, (float)immunityRadius);
        
        // Draw vulnerable population circle (white)
        p.fill(255, 255, 255, 100);
        p.circle(posX, posY, (float)cityRadius);
        
        // Draw infected population circle (red)
        if (populationInfected > 0) {
            p.fill(255, 0, 0, 100);
            p.circle(posX, posY, (float)spreadRadius);
        }
        
        // Draw city center point
        p.fill(0);
        p.circle(posX, posY, 5);
    }

    public void startInfectionSpread(){
        //when a new city contracts the virus, this method contains the logic for it to start expanding 
    }
    //Getters
    public int getPopulationVulnerable() {
        return populationVulnerable;
    }
    public int getPopulationInfected() {
        return populationInfected;
    }
    public int getPopulationImmune() {
        return populationImmune;
    }
    public int getCountryNum() {
        return countryNum;
    }
    public void wipeImmunity() {
        populationVulnerable += populationImmune;
        populationImmune = 0;
    }
    public int getPosX () {
        return posX;
    }
    public int getPosY () {
        return posY;
    }
    
    //Initial Infection
    public void infect() {
        if (populationVulnerable >= 100) {
            populationInfected += 100;
            populationVulnerable -= 100;
        }
    }
    public void update(Virus virus, boolean hasVaccine) {
        //Transmit to People 
        if (populationInfected > 0) {
            int change = (int) (populationInfected*Virus.getTransmissionRate());
            if (change > populationVulnerable) {
                change = populationVulnerable;
            }
            populationInfected += change;
            populationVulnerable -= change;
        }
        
        //Kill People
        populationInfected -= (int) (populationInfected * Virus.getDeathRate());
        
        //Recover People
        if (populationInfected > 0) {
            int change = (int) (populationInfected*Virus.getRecoveryRate());
            // 1*25 recovery of has vaccine
            if (hasVaccine) {
                change = (int) (change * 1.25);
            }
            if (change > populationInfected) {
                change = populationInfected;
            }
            populationInfected -= change;
            populationImmune += change;
        }
        //Immunity from vaccination
        if (populationVulnerable > 0 && hasVaccine) {
            int change = (int) (populationVulnerable * 0.02);
            if (change > populationVulnerable) {
                change = populationVulnerable;
            }
        }
        //increase population
        populationVulnerable = (int) (populationVulnerable * 1.01); // is this what you meant tyler
        populationVulnerable += (int) populationImmune * 1.01;
        //Keep max pop at 10000000
        if (populationVulnerable + populationImmune + populationInfected > 10000000) {
            
            populationVulnerable = 10000000 - (populationImmune+populationInfected);
        }
        
        
        
        //Render Changes
        render();
    }
    
    
    
  public static void main(String args[]) {
  }
}
