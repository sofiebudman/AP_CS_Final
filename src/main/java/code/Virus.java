package main.java.code;



public class Virus {
    private int deathRate;
    private int recoveryRate;
    private int transmissionRate;
    private int mutationRate;
    private String name;
    
    public Virus (String n, int d, int r, int t, int v, int m) {
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
        mutationRate = m;
    }
    
    public int getDeathRate() {
        return deathRate;
    }
    
    public int getRecoveryRate() {
        return recoveryRate;
    }
    
    public int getTransmissionRate() {
        return transmissionRate;
    }
    
    public int getMutationRate() {
        return mutationRate;
    }
    public String getName() {
        return name;
    }
    public void mutate() {
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
    
  public static void main(String args[]) {
   
  }
}