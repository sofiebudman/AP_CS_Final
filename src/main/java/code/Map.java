package src.main.java.code;

import static src.main.java.code.Constants.*;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Map {
    private PApplet p;

    private boolean show;

    private PImage africaOpen;
    private PImage northAmericaOpen;
    private PImage southAmericaOpen;
    private PImage eurasiaOpen;
    private PImage australiaOpen;

    private PImage africaClosed;
    private PImage northAmericaClosed;
    private PImage southAmericaClosed;
    private PImage eurasiaClosed;
    private PImage australiaClosed;
    private PImage ocean;

    private CountryImage africa;
    private CountryImage northAmerica;
    private CountryImage southAmerica;
    private CountryImage eurasia;
    private CountryImage australia;
    ArrayList<CountryImage> countryImages;

   

    public Map(PApplet p, Notification notification) {
        this.p = p;
        show = false;
       

        ocean = p.loadImage(OCEAN_PATH);

        africaOpen = p.loadImage(AFRICA_OPEN_PATH);
        northAmericaOpen = p.loadImage(NORTH_AMERICA_OPEN_PATH);
        southAmericaOpen = p.loadImage(SOUTH_AMERICA_OPEN_PATH);
        eurasiaOpen = p.loadImage(EURASIA_OPEN_PATH);
        australiaOpen = p.loadImage(AUSTRALIA_OPEN_PATH);
        //load closed images

        africaClosed = p.loadImage(AFRICA_CLOSED_PATH);
        northAmericaClosed =p.loadImage(NORTH_AMERICA_CLOSED_PATH);
        southAmericaClosed = p.loadImage(SOUTH_AMERICA_CLOSED_PATH);
        eurasiaClosed = p.loadImage(EURASIA_CLOSED_PATH);
        australiaClosed = p.loadImage(AUSTRALIA_CLOSED_PATH);

        // Resize all images
        africaOpen.resize(WIDTH_SCALE, 0);
        northAmericaOpen.resize(WIDTH_SCALE, 0);
        southAmericaOpen.resize(WIDTH_SCALE, 0);
        eurasiaOpen.resize(WIDTH_SCALE, 0);
        australiaOpen.resize(WIDTH_SCALE, 0);
        africaClosed.resize(WIDTH_SCALE, 0);
        northAmericaClosed.resize(WIDTH_SCALE, 0);
        southAmericaClosed.resize(WIDTH_SCALE, 0);
        eurasiaClosed.resize(WIDTH_SCALE, 0);
        australiaClosed.resize(WIDTH_SCALE, 0);

        

        countryImages = new ArrayList<>();


        africa = new CountryImage(AFRICA_OPEN_PATH, AFRICA_CLOSED_PATH);
        northAmerica = new CountryImage(NORTH_AMERICA_OPEN_PATH, NORTH_AMERICA_CLOSED_PATH);
        southAmerica = new CountryImage(SOUTH_AMERICA_OPEN_PATH, SOUTH_AMERICA_CLOSED_PATH);
        eurasia = new CountryImage(EURASIA_OPEN_PATH, EURASIA_CLOSED_PATH);
        australia = new CountryImage(AUSTRALIA_OPEN_PATH, AUSTRALIA_CLOSED_PATH);

        countryImages.add(africa);
        countryImages.add(northAmerica);
        countryImages.add(southAmerica);
        countryImages.add(eurasia);
        countryImages.add(australia);
    }

    public void drawContinents(){
        for(CountryImage c: countryImages){
            if(c.isOpen()){
                if(c == africa) p.image(africaOpen, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == northAmerica) p.image(northAmericaOpen,HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == southAmerica) p.image(southAmericaOpen,HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == eurasia) p.image(eurasiaOpen, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == australia) p.image(australiaOpen, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
            }
            else {
                if(c == africa) p.image(africaClosed,HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == northAmerica) p.image(northAmericaClosed, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == southAmerica) p.image(southAmericaClosed, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == eurasia) p.image(eurasiaClosed, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
                else if(c == australia) p.image(australiaClosed, HORIZONTAL_SHIFT, VERTICAL_SHIFT);
            }
        }
    }
    public void drawOcean(){
        p.image(ocean, HORIZONTAL_SHIFT, 50);
        ocean.resize(WIDTH_SCALE,0);
    }

   public void handleMousePressed(int mouseX, int mouseY) {
        int x = mouseX - HORIZONTAL_SHIFT; // Adjust for offsets
        int y = mouseY- VERTICAL_SHIFT;

        // Check each continent
        checkContinentClick(x, y, africaOpen, africa);
        checkContinentClick(x, y, northAmericaOpen, northAmerica);
        checkContinentClick(x, y, southAmericaOpen, southAmerica);
        checkContinentClick(x, y, eurasiaOpen, eurasia);
        checkContinentClick(x, y, australiaOpen, australia);
    }


    public void checkContinentClick(int x, int y, PImage continent, CountryImage c) {
        if (x >= 0 && x < continent.width && y >= 0 && y < continent.height) {
            int i = continent.get(x, y);
            float a = p.alpha(i);

            if (a > 0) {
                c.switchState();
                String countryName = "";
                if (c == africa) countryName = "Africa";
                else if (c == northAmerica) countryName = "North America";
                else if (c == southAmerica) countryName = "South America";
                else if (c == eurasia) countryName = "Eurasia";
                else if (c == australia) countryName = "Australia";
                
                if (c.isOpen()) {
                    Notification.newNotification(countryName + " borders are now open");
                } else {
                    Notification.newNotification(countryName + " borders are now closed");
                }
            }
        }
    }
    
    
}
