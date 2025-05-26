package main.java.code;



public class Virus {
    private static int deathRate;
    private static int recoveryRate;
    private static int transmissionRate;
    private static int mutationRate; 
    private static String name;
    private static String startingCountry;

    private static boolean la;
    private static boolean buenosAires;
    private static boolean cairo;
    private static boolean sydney;
    private static boolean beijing;
    

    
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
    public static void setDeathRate(int d) {
        deathRate = d;
    }
    public static void setRecoveryRate(int r) {
        recoveryRate = r;
    }
    public static void setTransmissionRate(int t) {
        transmissionRate = t;
    }
    public static void setMutationRate(int m) {
        mutationRate = m;
    }

    
    public static void setStartingCountry(String c) {
        startingCountry = c;
        
    }

    
    public static int getDeathRate() {
        return deathRate;
    }
    
    public static int getRecoveryRate() {
        return recoveryRate;
    }
    
    public static int getTransmissionRate() {
        return transmissionRate;
    }
    
    public static int getMutationRate() {
        return mutationRate;
    }
    public static String getName() {
        return name;
    }
    public static String getStartingCountry () {
        return startingCountry;
    }
    public static void mutate() {
        /* 
         
        int change = (int) (Math.random()*41)-20;
        deathRate += change;
        if (deathRate < 1) {
            deathRate = 1;
        }
        if (deathRate > 100) {
            deathRate = 100;
        }
        change = (int) (Math.random()*41)-20;
        recoveryRate += change;
        if (recoveryRate < 1) {
            recoveryRate = 1;
        }
        if (recoveryRate > 100) {
            recoveryRate = 100;
        }
        change = (int) (Math.random()*8)-4;
        transmissionRate += change;
        if (transmissionRate < 2) {
            transmissionRate = 2;
        }
        if (transmissionRate > 8) {
            transmissionRate = 8;
        }
        */
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