package main.java.code;

import processing.core.PApplet;
import static main.java.code.Constants.City.*;



public class City {
    
    private int populationVulnerable;
    private int populationInfected;
    private int populationImmune;

    private int posX;
    private int posY;
    private int countryNum;

   

    private double cityRadius; //radius that represents the size of a city (relative to population)
    private double infectionRadius; // radius that represents how infected a city is

    
    private PApplet p;
    private String name; 

    

   


    
    public City (String name, int x, int y, int c, PApplet p, boolean isInfected, int pop) {
        this.name = name;
        this.posX = x;
        this.posY = y;
        //countryNum = c;
        this.countryNum = c;
        this.p = p;
        
        // Initialize population
        this.populationVulnerable = pop; // 1 million people min, 10 million max
        this.populationInfected = 0;
        this.populationImmune = 0;
        cityRadius = Math.pow((populationVulnerable+populationImmune+populationInfected) / CITY_RADIUS_DIVIDER, 0.4) * CITY_RADIUS_MULTIPLIER + MIN_CITY_RADIUS;
        infectionRadius= cityRadius+ Math.sqrt(25 + populationInfected * (4500 / CITY_RADIUS_DIVIDER));

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

    public String getName() {
        return name;
    }

    public int getCityRadius() {
        return (int) cityRadius;
    }
    
    //Infection
    public void infect() {
        if (populationVulnerable >= 100) {
            populationInfected += 100;
            populationVulnerable -= 100;
        }
    }



    //UPDATE Variables

    public void update(boolean hasVaccine, boolean borderOpen) {

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

        if (!borderOpen) {
            int population = populationImmune+populationInfected+populationVulnerable;
            double shrink = (1.0 - Math.min(0.20, Math.max(0.01, 0.25 * 0.8 * Math.pow(population, -0.1))));
            populationVulnerable = (int) ((populationVulnerable) * shrink);
            populationInfected = (int) ((populationInfected) * shrink);
            populationImmune = (int) ((populationImmune) * shrink);
            
        }
        
        //Recover People
        if (populationInfected > 0) {
            int change = (int) (populationInfected*Virus.getRecoveryRate());
            // 1*25 recovery of has vaccine
            if (hasVaccine) {
                change = (int) (change * 5);
            }
            if (change > populationInfected) {
                change = populationInfected;
            }
            populationInfected -= change;
            populationImmune += change;
        }
        //If most of the population is immune virus goes away
        if (populationInfected>0 && (populationImmune/populationInfected)>100) {
            populationImmune+=populationInfected;
            populationInfected=0;
        }
        //Immunity from vaccination
        if (populationVulnerable > 0 && hasVaccine) {
            int change = (int) (populationVulnerable * 0.05); 
            if (change > populationVulnerable) {
                change = populationVulnerable;
            }
            populationImmune+=change;
            populationVulnerable-=change;
        }
        //increase population
        populationVulnerable = (int) ((populationVulnerable) * 1.00003 + populationImmune * 0.00003);
        //Keep max pop at 10000000
        if (populationVulnerable + populationImmune + populationInfected > 10000000) {
            
            populationVulnerable = 10000000 - (populationImmune+populationInfected);
        }

        //Update Radius
        cityRadius = Math.pow((populationVulnerable+populationImmune+populationInfected) / 10_000_000.0, 0.4) * 18 + 2;
        infectionRadius = Math.sqrt(Math.pow(cityRadius + 5, 2) + (8000 - Math.pow(cityRadius + 5, 2)) * (populationInfected / 10_000_000.0));
    }

    //RENDERING

    public void render() {
        p.noStroke();
        
        // Draw vulnerable population circle (black)
        int color = 255;  // Default to white
        if (populationImmune + populationVulnerable + populationInfected > 0) {
            double immuneRatio = (double)populationImmune / (populationImmune + populationVulnerable + populationInfected);
            color = (int)(155 * (1.0 - immuneRatio)) + 100;  // Will range from 255 to 100
        }
        p.fill(45, 45, 45, 255);
        p.circle(posX, posY, (float)cityRadius);
        
        // Draw infected population circle (red) only if infected
        if (populationInfected > 0) {
            p.fill(255, 0, 0, 100);
            p.circle(posX, posY, (float)infectionRadius);
        }

        //Point
        p.fill(color, color, 255, 225);
        p.circle(posX, posY, (int)(cityRadius/1.5));
    }
  public static void main(String args[]) {
  }
}
