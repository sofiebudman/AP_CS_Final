package main.java.code;

import processing.core.PApplet;
import static processing.core.PConstants.*;
import java.util.ArrayList;
import static main.java.code.Constants.Graphc.*;

public class Graph {

    private static boolean isVisible;
        

    public Graph() {
        
        Graph.isVisible = false;
    }

    public static boolean isVisible() {
        return isVisible;
    }


    //visibility manipulation methods
    public static void toggle() {
        isVisible = !isVisible;
    }

    public static void show() {
        isVisible = true;
    }

    public static void hide() {
        isVisible = false;
    }

    /**
     * 
     * @param cities Takes in an arraylist of cities, this allows us to calculate the percentages every "tick"
     * @param p Takes in a PApplet object, allowing us to draw
     * Calculates percentage of infected, vulnerable immune
     * current start angle, the start angle is updated after each arc is drawn
     * angle of arc is determined by 2 pi (whole circle ) times percent of respective secions
     */
    public static void display(ArrayList<City> cities, PApplet p) {
        if (!isVisible) return;

        // Calculate total population 
        int totalPopulation = 0;
        int infected = 0;
        int immune = 0;
        int vulnerable = 0;

        for (City city : cities) {
            totalPopulation += city.getPopulationVulnerable() + city.getPopulationInfected() + city.getPopulationImmune();
            infected += city.getPopulationInfected();
            immune += city.getPopulationImmune();
            vulnerable += city.getPopulationVulnerable();
        }

        if (totalPopulation == 0) return; //accounts for zero division errors

        // Draw background of screen
        p.noStroke();
        p.fill(BACKGROUND_COLOR);
        p.rect(0, 50, 300, 650);
        
        p.fill(PANEL_COLOR);
        p.rect(10, 60, 280, 630, 10);

       
        p.fill(TEXT_COLOR);
        p.textAlign(CENTER);
        p.textSize(16);
        p.text("World Population Status", 150, 90);

        // Calculate percentages
        double infectedPercent = (double)infected / totalPopulation;
        double immunePercent = (double)immune / totalPopulation;
        double vulnerablePercent = (double)vulnerable / totalPopulation;

        // chart
        
        
        double startAngle = 0; 
        

        p.fill(INFECTED_COLOR);
        p.arc( (float) GRAPH_X + GRAPH_SIZE/2, (float) GRAPH_Y + GRAPH_SIZE/2,  (float) GRAPH_SIZE, (float) GRAPH_SIZE,  (float) startAngle, (float) startAngle + (float) TWO_PI * (float) infectedPercent);
        startAngle += TWO_PI * infectedPercent; 
        

       
        p.fill(IMMUNE_COLOR);
        p.arc( (float) GRAPH_X + GRAPH_SIZE/2, (float)  GRAPH_Y + GRAPH_SIZE/2, 
              (float)GRAPH_SIZE, (float) GRAPH_SIZE, 
              (float) startAngle, (float)(startAngle + TWO_PI * immunePercent));
        startAngle += TWO_PI * immunePercent;


        p.fill(VULNERABLE_COLOR);
        p.arc((float) GRAPH_X + GRAPH_SIZE/2, (float) GRAPH_Y + GRAPH_SIZE/2, 
              (float) GRAPH_SIZE, (float) GRAPH_SIZE, 
              (float) startAngle,(float) (startAngle + TWO_PI * vulnerablePercent));


        p.textAlign(LEFT);
        p.textSize(14);
        

        p.fill(INFECTED_COLOR);
        p.rect(LEGEND_X, LEGEND_Y, 15, 15);
        p.fill(TEXT_COLOR);
        p.text(String.format("Infected: %.1f%%", infectedPercent * 100), 
               LEGEND_X + 20, LEGEND_Y + 12);


        p.fill(IMMUNE_COLOR);
        p.rect(LEGEND_X, LEGEND_Y + LEGEND_SPACING, 15, 15);
        p.fill(TEXT_COLOR);
        p.text(String.format("Immune: %.1f%%", immunePercent * 100), 
               LEGEND_X + 20, LEGEND_Y + LEGEND_SPACING + 12);


        p.fill(VULNERABLE_COLOR);
        p.rect(LEGEND_X, LEGEND_Y + LEGEND_SPACING * 2, 15, 15);
        p.fill(TEXT_COLOR);
        p.text(String.format("Vulnerable: %.1f%%", vulnerablePercent * 100), 
               LEGEND_X + 20, LEGEND_Y + LEGEND_SPACING * 2 + 12);

       
        p.textAlign(LEFT, BASELINE);
    }
} 