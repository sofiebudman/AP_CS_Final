package main.java.code;

import static main.java.code.Constants.Scale.*;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Timer;
import java.util.TimerTask;


public class Country {
    private String name;
    private boolean openBorder;
    private boolean hasVaccine;
    private boolean hasVirusInfo;
    private int countryNum;
   

    private PApplet p;

    //each image has two states: open and closed
    private PImage imageOpen;
    private PImage imageClosed; 
    
    
    private Timer timer;
    private int secondsElapsed;
    
    
    
    public Country (String name, int num, String openPath, String closedPath, PApplet p) {
        this.p = p;
        this.name = name;
        openBorder = true;
        hasVaccine = false;
        countryNum = num;
        
        //load and resize image
        imageOpen = p.loadImage(openPath);
        imageOpen.resize(WIDTH_SCALE, 0);
        imageClosed = p.loadImage(closedPath);
        imageClosed.resize(WIDTH_SCALE, 0);

        this.timer = new Timer();
        this.secondsElapsed = 30;
        
    }
    /*
     * Starts time to control city borders opening and closing
     * timer runs every 1 second
     */
    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                
                secondsElapsed++;
            }
        }, 0, 1000); 
    }

    public void stopTimer() {
        timer.cancel();
    }


    public PImage getOpenImage(){
        return imageOpen;
    }
    public PImage getClosedImage(){
        return imageClosed;
    }

    /**
     * @return Image at current state (open or closes)
     */
    public PImage getImage() {
        if (openBorder) {
            return imageOpen;
        } else {
            return imageClosed;
        }
    }


    /**
     * 
     * Draws the image at current xPos, yPos
     */
    public void drawImage(int xPos, int yPos) {
        if(openBorder){
            p.image(imageOpen, xPos, yPos);
        } else {
            p.image(imageClosed, xPos, yPos);
        }
    }

    /**
     * 
     * @param mouseX User's mouse position determined by processing
     * @param mouseY User's position determined by processing
     * Calls the checkContinent Click method to determine whether or not to change border
     */
    public void handleMousePressed(int mouseX, int mouseY){
        int x = mouseX - HORIZONTAL_SHIFT; // Adjust for offsets
        int y = mouseY - VERTICAL_SHIFT;

        checkContinentClick(x, y);


    }

    /**
     * 
     * @param x - mouse x
     * @param y - mouse y
     * Gets the position of the user's mouse X and Y
     * Compares that to the pixel alpha value of the image 
     * If that value is not zero (it has clicked on a country) it will switch the borders
     */

    private void checkContinentClick(int x, int y) {
        // Get the currently displayed image
        PImage currentImage = openBorder ? imageOpen : imageClosed;
        
        if (x >= 0 && x < currentImage.width && y >= 0 && y < currentImage.height) {
            int i = currentImage.get(x, y);
            float a = p.alpha(i);
            if(a > 0) {
                if (secondsElapsed >= 30) {
                    changeBorder();
                    Notification.newNotification(name + " borders are now " + (openBorder ? "open" : "closed"));
                    secondsElapsed = 0;
                } else {
                    Notification.newNotification("Wait "+(30-secondsElapsed)+" more days to toggle this border");
                }

            }
        }
    }

    public String getName() {
        return name;
    }
    
    public void changeBorder() {
        openBorder = !openBorder;
    }
    
    public boolean hasOpenBorder() {
        return openBorder;
    }

    public void giveVaccine() {
        hasVaccine = true;
    }
    public void loseVaccine() {
        hasVaccine = false;
    }

    public int getCountryNum() {
        return countryNum;
    }

    public void setHasVirusInfo(boolean b) {
        hasVirusInfo = b;
    }

    public boolean getHasVirusInfo() {
        return hasVirusInfo;
    }

    public boolean checkHasVaccine() {
        return hasVaccine;
    }
    
    
    
 
    
  public static void main(String args[]) {

  }
}