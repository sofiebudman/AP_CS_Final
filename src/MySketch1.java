package src;
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch1 extends PApplet {
    PImage africa;
    PImage northAmerica;
    PImage southAmerica;
    PImage eurasia;

    PImage africaClosed;
    PImage northAmericaClosed;
    PImage southAmericaClosed;
    PImage eurasiaClosed;

    // Track the current state of each continent
    boolean isAfricaClosed = false;
    boolean isNorthAmericaClosed = false;
    boolean isSouthAmericaClosed = false;
    boolean isEurasiaClosed = false;

    public void settings() {
        size(1000, 600);
    }

    public void setup() {
        background(255);
        
        // Load open images
        africa = loadImage("src/main/resources/africa_open.png");
        northAmerica = loadImage("src/main/resources/northamerica_open.png");
        southAmerica = loadImage("src/main/resources/southamerica_open.png");
        eurasia = loadImage("src/main/resources/eurasia_open.png");

        // Load closed images
        africaClosed = loadImage("src/main/resources/africa_closed.png");
        northAmericaClosed = loadImage("src/main/resources/northamerica_closed.png");
        southAmericaClosed = loadImage("src/main/resources/southamerica_closed.png");
        eurasiaClosed = loadImage("src/main/resources/eurasia_closed.png");

        // Resize all images
        africa.resize(1000, 0);
        northAmerica.resize(1000, 0);
        southAmerica.resize(1000, 0);
        eurasia.resize(1000, 0);
        africaClosed.resize(1000, 0);
        northAmericaClosed.resize(1000, 0);
        southAmericaClosed.resize(1000, 0);
        eurasiaClosed.resize(1000, 0);

        // Draw initial state
        drawContinents();
    }

    public void draw() {
        // Clear the background
        background(255);
        // Draw all continents in their current state
        drawContinents();
    }

    public void drawContinents() {
        // Draw each continent based on its current state
        image(isAfricaClosed ? africaClosed : africa, 50, 0);
        image(isNorthAmericaClosed ? northAmericaClosed : northAmerica, 50, 0);
        image(isSouthAmericaClosed ? southAmericaClosed : southAmerica, 50, 0);
        image(isEurasiaClosed ? eurasiaClosed : eurasia, 50, 0);
    }

    public void mousePressed() {
        int x = mouseX - 50; // Adjust for the 50px offset
        int y = mouseY;

        // Check each continent
        checkContinentClick(x, y, africa, "Africa");
        checkContinentClick(x, y, northAmerica, "North America");
        checkContinentClick(x, y, southAmerica, "South America");
        checkContinentClick(x, y, eurasia, "Eurasia");
    }

    private void checkContinentClick(int x, int y, PImage continent, String continentName) {
        if (x >= 0 && x < continent.width && y >= 0 && y < continent.height) {
            int c = continent.get(x, y);
            float a = alpha(c);

            if (a > 0) {
                println("Clicked on " + continentName);
                switch (continentName) {
                    case "Africa":
                        isAfricaClosed = !isAfricaClosed;
                        break;
                    case "North America":
                        isNorthAmericaClosed = !isNorthAmericaClosed;
                        break;
                    case "South America":
                        isSouthAmericaClosed = !isSouthAmericaClosed;
                        break;
                    case "Eurasia":
                        isEurasiaClosed = !isEurasiaClosed;
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("src.MySketch1");
    }
}