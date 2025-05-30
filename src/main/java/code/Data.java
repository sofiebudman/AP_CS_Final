package main.java.code;

import java.util.ArrayList;

public class Data {

    static ArrayList<String[]> data;

    public Data(){
        data = new ArrayList<>();
        data.add(new String[]{"Day", "Vulnerable", "Infected", "Immune"});
    }
    public static String[] cityData(int day, ArrayList<City> cities){
       
        int infected = 0;
        int immune = 0;
        int vulnerable = 0;

        for (City city : cities) {
  
            infected += city.getPopulationInfected();
            immune += city.getPopulationImmune();
            vulnerable += city.getPopulationVulnerable();
        }
        return new String[]{String.valueOf(day), String.valueOf(vulnerable), String.valueOf(infected), String.valueOf(immune)};


    }
    public static void logData(String[] row){
        data.add(row);
    }
    
    
}
