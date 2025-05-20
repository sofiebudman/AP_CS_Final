package main.java.code;



public class Virus {
    private static int deathRate;
    private static int recoveryRate;
    private static int transmissionRate;
    private static int mutationRate;
    private static String name;
    private static String startingCountry;
    
    public Virus (/*String n, int d, int r, int t, int v, int m*/) {
        /* 
        name = n;
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }
        if(name.equals("")) {
            name = "Virus";
        }
        deathRate = d;
        recoveryRate = r;
        transmissionRate = t;
        mutationRate = m;*/
    }

    //Settet methods
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
    public static void mutate() {
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
        change = (int) (Math.random()*41)-20;
        transmissionRate += change;
        if (transmissionRate < 1) {
            transmissionRate = 1;
        }
        if (transmissionRate > 100) {
            transmissionRate = 100;
        }
    }
    public String toString() {
        return "Virus: " + name + "\nDeath Rate: " + deathRate + "\nRecovery Rate: " + recoveryRate + "\nTransmission Rate: " + transmissionRate + "\nMutation Rate: " + mutationRate;
    }   
    
  public static void main(String args[]) {
   
  }
}