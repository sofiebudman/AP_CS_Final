package main.java.code;

import static main.java.code.Constants.Scale.*;

import processing.core.PApplet;
import processing.core.PImage;

public class Country {
    private String name;
    private boolean openBorder;
    //private ArrayList<City> cities;
    private boolean hasVaccine;
    // Each city is max 10 million people
    private int maxCities = 20;
    private boolean infected;
    private int xPos;
    private int yPos;

    private PApplet p;


    private PImage imageOpen;
    private PImage imageClosed; 
    private String openPath;
    private String closedPath;
    
    
    
    public Country (String name, String openPath, String closedPath, PApplet p) {
        this.p = p;
        this.name = name;
        openBorder = true;
        hasVaccine = false;
        infected = false;
        this.openPath = openPath;
        this.closedPath = closedPath;
        
        // Load and resize images once
        imageOpen = p.loadImage(openPath);
        imageOpen.resize(WIDTH_SCALE, 0);
        
        imageClosed = p.loadImage(closedPath);
        imageClosed.resize(WIDTH_SCALE, 0);
        /* 
        cities = new ArrayList<City>();
        for (int i = 0; i < (int) (Math.random()*maxCities)+1; i++) {
            cities.add(new City());
        }
        xPos = x;
        yPos = y;*/
    }


    public PImage getOpenImage(){
        return imageOpen;
    }
    public PImage getClosedImage(){
        return imageClosed;
    }
    public PImage getImage() {
        if (openBorder) {
            return imageOpen;
        } else {
            return imageClosed;
        }
    }

    public void drawImage(int xPos, int yPos) {
        if(openBorder){
            p.image(imageOpen, xPos, yPos);
        } else {
            p.image(imageClosed, xPos, yPos);
        }
    }

    
    public void handleMousePressed(int mouseX, int mouseY){
        int x = mouseX - HORIZONTAL_SHIFT; // Adjust for offsets
        int y = mouseY - VERTICAL_SHIFT;

        checkContinentClick(x, y);


    }

    private void checkContinentClick(int x, int y) {
        // Get the currently displayed image
        PImage currentImage = openBorder ? imageOpen : imageClosed;
        
        if (x >= 0 && x < currentImage.width && y >= 0 && y < currentImage.height) {
            int i = currentImage.get(x, y);
            float a = p.alpha(i);
            if(a > 0) {
                changeBorder();
                Notification.newNotification(name + " borders are now " + (openBorder ? "open" : "closed"));
            }
        }
    }

    public String getName() {
        return name;
    }
    
    public void changeBorder() {
        openBorder = !openBorder;
    }
    
    public void giveVaccine() {
        hasVaccine = true;
    }
    public void loseVaccine() {
        hasVaccine = false;
    }
    
    public void update(Virus virus) {
        
        //Infect cities
        //TODO Detect if city is near and then infect
        
        // Update cities
        
        //for (City c : cities) {
       //     c.update(virus, hasVaccine);
            
            
        //}
        

      
        
        
        
    }
    
    public void renderCountry() {
        //load image depending on openBorder, xPos, yPos
        
        //Render sidebar statistics
        
    }
    
  public static void main(String args[]) {

  }
}