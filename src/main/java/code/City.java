package main.java.code;

import processing.core.PApplet;

import java.util.Timer;
import java.util.TimerTask;


public class City {
    // Constants
    /*private static final int MAX_POPULATION = 10000000;
    private static final double BASE_CITY_RADIUS = 20;
    private static final double SPREAD_RADIUS_MULTIPLIER = 1.5;
    private static final double POPULATION_GROWTH_RATE = 1.01;
    private static final double VACCINE_IMMUNITY_RATE = 0.02;
    private static final double VACCINE_RECOVERY_BOOST = 1.25;*/
    
    // Population state
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
    private boolean isInfected;
    private int secondsElapsed;

   


    private Timer timer;
    public City (String name, int x, int y, /*int c int pop*/ PApplet p, boolean isInfected) {
        this.name = name;
        this.posX = x;
        this.posY = y;
        //countryNum = c;
        this.p = p;
        this.isInfected = isInfected;
        
        // Initialize population
        this.populationVulnerable = 100000; // 1 million people
        this.populationInfected = 0;
        this.populationImmune = 0;
        
        // Initialize radius values
       // this.cityRadius = BASE_CITY_RADIUS;
       // this.spreadRadius = cityRadius * SPREAD_RADIUS_MULTIPLIER;
        this.immunityRadius = cityRadius;

    

        this.timer = new Timer();
        
        if (isInfected) {
            startTimer();
        }
    }
    public void becomeInfected(){
        if (!isInfected) {
            isInfected = true;
            startTimer();
            infect(); // Initial infection
        }
    }
    public void incrementSpreadRadius() {
       spreadRadius += Virus.getTransmissionRate() * 0.01; 
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            
                secondsElapsed++;
                System.out.println("Seconds elapsed: " + secondsElapsed);
                updateSpreadRadius();
            }
        }, 0, 10);  //1000 = 1 second, 10 = 0.01 seconds, a tick for virus spread is 10 milliseconds
    }

    private void updateSpreadRadius() {
        // Update spread radius based on infection time and population
        double logisticGrowth = logisticSpread(secondsElapsed);
        spreadRadius = cityRadius + (logisticGrowth * 0.1); // Increased scaling factor for more visible growth
    }

    public double logisticSpread(double time){
        // Logistic function for spread radius
        double L = 1000; // Maximum spread radius
        //double e = 2.718; // Reduced growth rate for smoother expansion
        double t = Virus.getTransmissionRate();
        //double x0 = populationVulnerable/2; // Fixed midpoint for more predictable growth
        return L / (1 + 0.1 *Math.pow(t, (-0.01 *(0.1 * time - (1800/t)))));


    }
    

    
    public void render() {
        p.noStroke();
        // Draw immunity circle (blue)
        p.fill(0, 0, 255, 100);
        p.circle(posX, posY, (float)immunityRadius);
        
        // Draw vulnerable population circle (white)
        p.fill(255, 255, 255, 100);
        p.circle(posX, posY, (float)cityRadius);
        
        // Draw infected population circle (red) only if infected
        if (isInfected) {
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
    /*public void update(Virus virus, boolean hasVaccine) {
        if (!isInfected) return;

        // Handle transmission
        updateTransmission();
        
        // Handle deaths
        updateDeaths();
        
        // Handle recovery
        updateRecovery(hasVaccine);
        
        // Handle vaccination immunity
        if (hasVaccine) {
            updateVaccineImmunity();
        }
        
        // Update population growth
        updatePopulationGrowth();
        
        // Ensure population limits
        enforcePopulationLimits();
        
        // Render changes
        render();
    }
    
    private void updateTransmission() {
        if (populationInfected > 0) {
            int change = (int) (populationInfected * Virus.getTransmissionRate());
            change = Math.min(change, populationVulnerable);
            populationInfected += change;
            populationVulnerable -= change;
        }
    }

    private void updateDeaths() {
        populationInfected -= (int) (populationInfected * Virus.getDeathRate());
    }
    /* 

    private void updateRecovery(boolean hasVaccine) {
        if (populationInfected > 0) {
            int change = (int) (populationInfected * Virus.getRecoveryRate());
            if (hasVaccine) {
                change = (int) (change * VACCINE_RECOVERY_BOOST);
            }
            change = Math.min(change, populationInfected);
            populationInfected -= change;
            populationImmune += change;
        }
    }*/
    /* 
    private void updateVaccineImmunity() {
        if (populationVulnerable > 0) {
            int change = (int) (populationVulnerable * VACCINE_IMMUNITY_RATE);
            change = Math.min(change, populationVulnerable);
            populationVulnerable -= change;
            populationImmune += change;
        }
    }
        */
    /* 
    private void updatePopulationGrowth() {
        populationVulnerable = (int) (populationVulnerable * POPULATION_GROWTH_RATE);
        populationImmune = (int) (populationImmune * POPULATION_GROWTH_RATE);
    }*/
    /* 
    private void enforcePopulationLimits() {
        int totalPopulation = populationVulnerable + populationImmune + populationInfected;
        if (totalPopulation > MAX_POPULATION) {
            populationVulnerable = MAX_POPULATION - (populationImmune + populationInfected);
        }
    }*/
    
    
    
  public static void main(String args[]) {
  }
}
