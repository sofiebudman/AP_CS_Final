package main.java.code;



public class Virus {
    private static double deathRate;
    private static double recoveryRate;
    private static double transmissionRate;
    private static double mutationRate; 
    private static String name;
    private static String startingCountry;


    
    public Virus () {
     
    }

    public static void setName(String n) {
        name = n;
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }
        if(name.equals("")) {
            name = "Virus";
        }
    }
    public static void setDeathRate(double d) {
        deathRate = d;
    }
    public static void setRecoveryRate(double r) {
        recoveryRate = r;
    }
    public static void setTransmissionRate(double t) {
        transmissionRate = t;
    }
    public static void setMutationRate(double m) {
        mutationRate = m;
    }

    
    public static void setStartingCountry(String c) {
        startingCountry = c;
        
    }

    
    public static double getDeathRate() {
        return deathRate;
    }
    
    public static double getRecoveryRate() {
        return recoveryRate;
    }
    
    public static double getTransmissionRate() {
        return transmissionRate;
    }
    
    public static double getMutationRate() {
        return mutationRate;
    }
    public static String getName() {
        return name;
    }
    public static String getStartingCountry () {
        return startingCountry;
    }

    public String toString() {
        return "Virus: " + name + 
               "\nDeath Rate: " + deathRate + 
               "\nRecovery Rate: " + recoveryRate + 
               "\nTransmission Rate: " + transmissionRate + 
               "\nMutation Rate: " + mutationRate + 
               "\nStarting Country: " + startingCountry;
    }   
    
  public static void main(String args[]) {
   
  }
}