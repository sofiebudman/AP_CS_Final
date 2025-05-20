package main.java.code;

import static main.java.code.Constants.Scale.*;
import static main.java.code.Constants.Fonts.*;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Slider;
import controlP5.Textfield;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PFont;

public class WelcomeScreen {

    private PApplet p;
    PImage titleImage;
    PImage backgroundImage;
    PImage virusControlImage;
    private ControlP5 cp5;
    private boolean controlsCreated;
    private int currentPage = 0;

    // Animation variables
    private double titleScale = 0.1;
    private double titleAlpha = 0;
    private double titleY = -100;
    private boolean animationComplete = false;
    private static final double ANIMATION_SPEED = 0.02;
    private static final double ZOOM_SPEED = 0.015;

    private Button nextButton;
    private Textfield virusName;
    private Slider deathRateSlider;
    private Slider recoveryRateSlider;
    private Slider transmissionRateSlider; 
    private DropdownList countryStart;

    private String feedbackMessage = "";
    private boolean virusNameValid = false;
    private int storedDeathRate = 50;
    private int storedRecoveryRate = 50;
    private int storedTransmissionRate = 50;
    private String storedCountry = "North America";

    private PFont mainFont;
    private static final int TITLE_SIZE = 24;
    private static final int HEADER_SIZE = 20;
    private static final int BODY_SIZE = 16;
    private static final int FEEDBACK_SIZE = 14;

   //private String virusNameText;

    public WelcomeScreen(PApplet p) {
        this.p = p;
        cp5 = new ControlP5(p);
        mainFont = p.createFont(FARRO_REGULAR_FONT_PATH, 16);
        titleImage = p.loadImage("src/main/resources/elements/title.png");
        backgroundImage = p.loadImage("src/main/resources/elements/background.png");
        virusControlImage = p.loadImage("src/main/resources/elements/virusControl.png");
        titleImage.resize(WIDTH_SCALE,0);
        backgroundImage.resize(WIDTH_SCALE-100,0);
        virusControlImage.resize(WIDTH_SCALE-100,0);

        controlsCreated = false;
        currentPage = 0;
        setupNextButton();
        nextButton.hide();
    }

    public void title() { 
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        
        // Only animate if we're on the first page and animation isn't complete
        if (currentPage == 0 && !animationComplete) {
            // Update animation values
            titleScale = Math.min(1.0, titleScale + ZOOM_SPEED);
            titleAlpha = Math.min(255, titleAlpha + ANIMATION_SPEED * 255);
            titleY = PApplet.lerp(-100, 0, (float)titleScale);
            
            // Check if animation is complete
            if (titleScale >= 1.0 && titleAlpha >= 255) {
                animationComplete = true;
                nextButton.show();
            }
        }
        
        // Apply transformations for zoom effect
        p.pushMatrix();
        p.translate(p.width/2, p.height/2);
        p.scale((float)titleScale);
        p.tint(255, (int)titleAlpha);
        p.imageMode(p.CENTER);
        p.image(titleImage, 0, (float)titleY);
        p.popMatrix();
        
        // Reset image mode
        p.imageMode(PConstants.CORNER);
        p.noTint();
    }

    private void setupNextButton() {
        nextButton = cp5.addButton("Next")
                .setPosition(600, 550)
                .setSize(200, 50)
                .setLabel("Next")
                .setColorBackground(p.color(0, 0, 0))
                .setColorForeground(p.color(100, 100, 100))
                .setColorActive(p.color(150, 150, 150))
                .onPress((event) -> {
                    if (currentPage == 2) {
                        // Store all values when moving to the next page
                        storedDeathRate = (int) deathRateSlider.getValue();
                        storedRecoveryRate = (int) recoveryRateSlider.getValue();
                        storedTransmissionRate = (int) transmissionRateSlider.getValue();
                        storedCountry = countryStart.getLabel();
                        
                        // Set the values in the Virus class
                        Virus.setDeathRate(storedDeathRate);
                        Virus.setRecoveryRate(storedRecoveryRate);
                        Virus.setTransmissionRate(storedTransmissionRate);
                        Virus.setStartingCountry(storedCountry);
                    }
                    
                    currentPage++;
                    if (currentPage == 3) {
                        cp5.remove("virusName");
                        cp5.remove("deathRateSlider");
                        cp5.remove("recoveryRateSlider");
                        cp5.remove("transmissionRateSlider");
                        cp5.remove("countryStart");
                        cp5.remove("Next");
                    }
                });
    }

    public void addButtons() {
        // Add labels for the controls with improved styling
        p.textAlign(PConstants.LEFT);
        p.fill(0);
        p.textFont(mainFont, HEADER_SIZE);
        p.text("Virus Configuration", 540, 30);
        
        p.textFont(mainFont, BODY_SIZE);
        p.text("Enter Virus Name:", 540, 60);
        
        virusName = cp5.addTextfield("virusName")
                .setPosition(540, 70)
                .setSize(200, 40)
                .setFont(p.createFont(FARRO_REGULAR_FONT_PATH, 16))
                .setColorBackground(p.color(255, 255, 255))
                .setColorForeground(p.color(0, 0, 0))
                .setColorActive(p.color(0, 0, 0))
                .setColorValue(p.color(0, 0, 0))
                .onChange((event) -> {
                    String text = event.getController().getStringValue();
                    if (text.length() > 20) {
                        feedbackMessage = "Virus name must be 20 characters or less";
                        virusNameValid = false;
                    } else if (text.length() > 0) {
                        feedbackMessage = "Virus name entered: " + text;
                        virusNameValid = true;
                        Virus.setName(text);
                    } else {
                        feedbackMessage = "Please enter a virus name";
                        virusNameValid = false;
                    }
                });

        p.text("Death Rate (%):", 540, 130);
        deathRateSlider = cp5.addSlider("deathRateSlider")
                .setPosition(540, 140)
                .setSize(200, 20)
                .setRange(0, 100)
                .setValue(50)
                .setDecimalPrecision(0)
                .setLabel("Death Rate");
               

        p.text("Recovery Rate (%):", 540, 180);
        recoveryRateSlider = cp5.addSlider("recoveryRateSlider")
                .setPosition(540, 190)
                .setSize(200, 20)
                .setRange(0, 100)
                .setValue(50)
                .setLabel("Recovery Rate");
             

        p.text("Transmission Rate (%):", 540, 230);
        transmissionRateSlider = cp5.addSlider("transmissionRateSlider")
                .setPosition(540, 240)
                .setSize(200, 20)
                .setRange(0, 100)
                .setValue(50)
                .setLabel("Transmission Rate");
                

        p.text("Starting Country:", 540, 280);
        countryStart = cp5.addDropdownList("countryStart")
                .setPosition(540, 290)
                .setItems(new String[]{"North America", "South America", "Europe", "Africa", "Asia", "Australia"})
                .setSize(200, 120)
                .setItemHeight(20)
                .setBarHeight(30)
                .setLabel("Select Country");
        controlsCreated = true;
    }

    public void background() {
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(backgroundImage, 150,0);
    }

    public void virusControlScreen() {
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(virusControlImage, 150,0);

        if(!controlsCreated) {
            addButtons();
        }

        // Display feedback message with improved styling
        p.fill(0);
        p.textFont(mainFont, FEEDBACK_SIZE);
        p.textAlign(PConstants.LEFT);
        p.text(feedbackMessage, 540, 430);

        // Display current values with improved styling
        p.textFont(mainFont, BODY_SIZE);
        p.text("Current Configuration:", 540, 460);
        p.textFont(mainFont, FEEDBACK_SIZE);
        p.text("Death Rate: " + (int)deathRateSlider.getValue() + "%", 540, 480);
        p.text("Recovery Rate: " + (int)recoveryRateSlider.getValue() + "%", 540, 500);
        p.text("Transmission Rate: " + (int)transmissionRateSlider.getValue() + "%", 540, 520);
        p.text("Starting Country: " + countryStart.getValue(), 540, 540);
    }

    public void display() {
        if(currentPage == 0) {
            title();
        }
        else if(currentPage == 1) {
            background();
        }
        else if (currentPage == 2) {
            virusControlScreen();
            Virus.setDeathRate((int) deathRateSlider.getValue());
        }
        else if(currentPage == 3) {
            // Remove all controls when transitioning to map
            
            cp5.remove("virusName");
            cp5.remove("deathRateSlider");
            cp5.remove("recoveryRateSlider");
            cp5.remove("transmissionRateSlider");
            cp5.remove("countryStart");
            cp5.remove("Next");
            
            // Don't display anything on page 3, let the map show
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /*public void keyPressed() {
        if (p.keyCode == p.ENTER && currentPage == 2) {
            Virus.setName()
            virusNameText = virusName.getText();
            System.out.println("Virus Name: " + virusNameText);
        }
    }*/
}
   

