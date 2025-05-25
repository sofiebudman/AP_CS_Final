package main.java.code;

import processing.core.PApplet;



public class City {
    // Population state
    private int populationVulnerable;
    private int populationInfected;
    private int populationImmune;

    private int posX;
    private int posY;
    private int countryNum;

    //TODO: notification when virus mutates

    private double immunityRadius; // blue circle that represents how immune a populaiton is
    private double cityRadius; //radius that represents the size of a city
    private double infectionRadius;
    
    private PApplet p;
    private String name;

   


    
    public City (String name, int x, int y, /*int c int pop*/ PApplet p, boolean isInfected) {
        this.name = name;
        this.posX = x;
        this.posY = y;
        //countryNum = c;
        countryNum = 0;
        this.p = p;
        
        // Initialize population
        //this.populationVulnerable = (int) (Math.random() * 9000001) + 1000000; // 1 million people min, 10 million max
        this.populationVulnerable = 1000000; // 1 million for testing
        this.populationInfected = 0;
        this.populationImmune = 0;

        //Radius
        immunityRadius = 20;
        cityRadius = 10;
        infectionRadius = 50;

    }

    public double logisticSpread(double time){
        // Logistic function for spread radius
        double L = 500; // Maximum spread radius
        //double e = 2.718; // Reduced growth rate for smoother expansion
        double t = Virus.getTransmissionRate();
        //double x0 = populationVulnerable/2; // Fixed midpoint for more predictable growth
        return L / (1 + 0.1 *Math.pow(t, (-0.01 *(0.1 * time - (1800/t)))));


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
    
    //Infection
    public void infect() {
        if (populationVulnerable >= 100) {
            populationInfected += 100;
            populationVulnerable -= 100;
        }
    }



    //UPDATE Variables

    public void update(boolean hasVaccine) {

        //Transmit to People 
        if (populationInfected > 0) {
            int change = (int) (populationInfected*Virus.getTransmissionRate()*0.01);
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
        populationVulnerable = (int) (populationVulnerable * 1.01);
        populationVulnerable += (int) (populationImmune * 1.01);
        //Keep max pop at 10000000
        if (populationVulnerable + populationImmune + populationInfected > 10000000) {
            
            populationVulnerable = 10000000 - (populationImmune+populationInfected);
        }
        
    }

    //RENDERING

    public void render() {
        p.noStroke();
        // Draw immunity circle (blue)
        p.fill(0, 0, 255, 255);
        p.circle(posX, posY, (float)immunityRadius);
        
        // Draw vulnerable population circle (white)
        p.fill(255, 255, 255, 255);
        p.circle(posX, posY, (float)cityRadius);
        
        // Draw infected population circle (red) only if infected
        if (populationInfected > 0) {
            p.fill(255, 0, 0, 100);
            p.circle(posX, posY, (float)infectionRadius);
        }
    }
  public static void main(String args[]) {
  }
}
