package main.java.code;

import processing.core.PApplet;
import static processing.core.PConstants.*;
import java.util.ArrayList;

public class Graph {
   /// private static PApplet p;
    private static boolean isVisible;
    private static final int GRAPH_X = 20; //left
    private static final int GRAPH_Y = 100;
    private static final int GRAPH_SIZE = 200;
    private static final int LEGEND_X = GRAPH_X + 20;
    private static final int LEGEND_Y = GRAPH_Y + GRAPH_SIZE + 30;
    private static final int LEGEND_SPACING = 25;

    // More subtle colors
    private static final int INFECTED_COLOR = 0xFFE57373;  
    private static final int IMMUNE_COLOR = 0xFF81C784;  
    private static final int VULNERABLE_COLOR = 0xFF64B5F6; 
    private static final int BACKGROUND_COLOR = 0xFFFFB877; 
    private static final int PANEL_COLOR = 0xFFFFFFFF;   
    private static final int TEXT_COLOR = 0xFF424242;      

    public Graph() {
        //his.p = p;
        Graph.isVisible = false;
    }

    public static boolean isVisible() {
        return isVisible;
    }

    public static void toggle() {
        isVisible = !isVisible;
    }

    public static void show() {
        isVisible = true;
    }

    public static void hide() {
        isVisible = false;
    }

    public static void display(ArrayList<City> cities, PApplet p) {
        if (!isVisible) return;

        // Calculate total population and status counts
        int totalPopulation = 0;
        int infected = 0;
        int immune = 0;
        int vulnerable = 0;

        for (City city : cities) {
            totalPopulation += city.getPopulationVulnerable() + 
                             city.getPopulationInfected() + 
                             city.getPopulationImmune();
            infected += city.getPopulationInfected();
            immune += city.getPopulationImmune();
            vulnerable += city.getPopulationVulnerable();
        }

        if (totalPopulation == 0) return;

        // Draw background
        p.noStroke();
        p.fill(BACKGROUND_COLOR);
        p.rect(0, 50, 300, 650);
        
        p.fill(PANEL_COLOR);
        p.rect(10, 60, 280, 630, 10);

        // Draw title
        p.fill(TEXT_COLOR);
        p.textAlign(CENTER);
        p.textSize(16);
        p.text("World Population Status", 150, 90);

        // Calculate percentages
        double infectedPercent = (double)infected / totalPopulation;
        double immunePercent = (double)immune / totalPopulation;
        double vulnerablePercent = (double)vulnerable / totalPopulation;

        // Draw pie chart
        double startAngle = 0;
        
        // Infected (soft red)
        p.fill(INFECTED_COLOR);
        p.arc( (float) GRAPH_X + GRAPH_SIZE/2, (float) GRAPH_Y + GRAPH_SIZE/2,  (float) GRAPH_SIZE, (float) GRAPH_SIZE,  (float) startAngle, (float) startAngle + (float) TWO_PI * (float) infectedPercent);
        startAngle += TWO_PI * infectedPercent;

        // Immune (soft green)
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