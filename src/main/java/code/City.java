package main.java.code;public class City {
    private int populationVulnerable;
    private int populationInfected;
    private int populationImmune;
    private int populationRecovered; // tyler shoudl imake this
    private int posX;
    private int posY;
    private int countryNum;
    public City (int x, int y, int c, int pop) {
        
        populationVulnerable = pop;
        if (populationVulnerable > 10000000) {
            populationVulnerable = 10000000;
        }
        populationInfected = 0;
        populationImmune = 0;
        posX = x;
        posY = y;
        countryNum = c;
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
            populationRecovered += change;
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
        populationVulnerable += (int) populationRecovered * 1.01;
        //Keep max pop at 10000000
        if (populationVulnerable + populationRecovered + populationInfected > 10000000) {
            
            populationVulnerable = 10000000 - (populationRecovered+populationInfected);
        }
        
        
        
        //Render Changes
        render();
    }
    
    public void render() {
        //Render based on color
        
        //use posX posY
        
        //At X, Y, draw a blue circle based on populationRecovered + populationVulnerable
        //At X, Y, draw a white circle on top of the blue circle with populationVulnerable
        //At X, Y, draw a transparent red circle based on population Infected. At 1 person infected, radius of this circle should be 
        //slightly larger than the blue circle
        
        
    }
    
  public static void main(String args[]) {
  }
}
