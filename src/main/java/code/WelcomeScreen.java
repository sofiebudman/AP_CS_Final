package main.java.code;

import static main.java.code.Constants.Scale.*;
import static main.java.code.Constants.FilePaths.BACKGROUND_IMAGE_PATH;
import static main.java.code.Constants.FilePaths.RULES_IMAGE_PATH;
import static main.java.code.Constants.FilePaths.TITLE_IMAGE_PATH;
import static main.java.code.Constants.FilePaths.VIRUS_CONTROL_IMAGE_PATH;
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
    private PImage titleImage;
    private PImage backgroundImage;
    private PImage virusControlImage;
    private PImage rulesImage;
    private ControlP5 cp5;
    private boolean controlsCreated;
    private int currentPage = 0;

   ///animation variables
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
    private Slider mutationRateSlider;
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
    private String[] countryOptions;
    //private static final String VIRUS_NAME_TEXT = "Virus Name: ";

   //private String virusNameText;

    public WelcomeScreen(PApplet p) {
        this.p = p;
        cp5 = new ControlP5(p);

        //creates font
        mainFont = p.createFont(FARRO_REGULAR_FONT_PATH, 16);

        //sets up the images
        titleImage = p.loadImage(TITLE_IMAGE_PATH);
        backgroundImage = p.loadImage(BACKGROUND_IMAGE_PATH);
        virusControlImage = p.loadImage(VIRUS_CONTROL_IMAGE_PATH);
        rulesImage = p.loadImage(RULES_IMAGE_PATH);


        titleImage.resize(WIDTH_SCALE,0);
        backgroundImage.resize(WIDTH_SCALE-100,0);
        virusControlImage.resize(WIDTH_SCALE-100,0);
        rulesImage.resize(WIDTH_SCALE-100,0);
        
        controlsCreated = false; // allows it so we can only create controls once
        currentPage = 0; //stores currnet page - 0 is the home page, 1 is the background page, 2 is the virus control screen, and 3 is the actual simulation screen
        
        setupNextButton(); //creates the next button
        nextButton.hide(); // hides the next button until the animation is complete


        countryOptions = new String[] {
            "North America",
            "South America",
            "Eurasia",
            "Africa",
            "Australia"
        };
    }
    

    // title screen aniation 
    public void title() { 
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        
        // Only animate if we're on the first page and animation isn't complete
        if (currentPage == 0 && !animationComplete) {
           
            titleScale = Math.min(1.0, titleScale + ZOOM_SPEED);
            titleAlpha = Math.min(255, titleAlpha + ANIMATION_SPEED * 255);
            titleY = PApplet.lerp(-100, 0, (float)titleScale);
            
            
            if (titleScale >= 1.0 && titleAlpha >= 255) {
                animationComplete = true;
                nextButton.show();
            }
        }
        
      
        p.pushMatrix();
        p.translate(p.width/2, p.height/2);
        p.scale((float)titleScale);
        p.tint(255, (int)titleAlpha);
        p.imageMode(p.CENTER);
        p.image(titleImage, 0, (float)titleY);
        p.popMatrix();
        
        
        p.imageMode(PConstants.CORNER);
        p.noTint();
    }

    private void setupNextButton() {
        nextButton = cp5.addButton("Next")
                .setPosition(600, 570)
                .setSize(200, 50)
                .setLabel("Next")
                .setColorBackground(p.color(0, 0, 0))
                .setColorForeground(p.color(100, 100, 100))
                .setColorActive(p.color(150, 150, 150))
                .onPress((event) -> {
                    if (currentPage == 3) {
                        // Store all values when moving to the next page
                        /*storedDeathRate = (int) deathRateSlider.getValue();
                        storedRecoveryRate = (int) recoveryRateSlider.getValue();
                        storedTransmissionRate = (int) transmissionRateSlider.getValue();
                        storedCountry = countryOptions[(int) countryStart.getId()];
                        
                        // Set the values in the Virus class
                        Virus.setDeathRate(storedDeathRate);
                        Virus.setRecoveryRate(storedRecoveryRate);
                        Virus.setTransmissionRate(storedTransmissionRate);
                        Virus.setStartingCountry(storedCountry);*/
                         if( !virusNameValid) {
                           
                           feedbackMessage = "Please enter a valid virus name";
                            return; // Prevent moving to the next page if the name is invalid
                        }
                       
                    }
                    
                    currentPage++;
                    if (currentPage == 4) {
                        cp5.remove("virusName");
                        cp5.remove("deathRateSlider");
                        cp5.remove("recoveryRateSlider");
                        cp5.remove("transmissionRateSlider");
                        cp5.remove("mutationRateSlider");
                        cp5.remove("countryStart");
                        cp5.remove("Next");
                    }
                });
    }

    public void addButtons() {
        // Add labels for the controls with improved styling
       
        feedbackMessage = "Please enter a virus name";
        
        virusName = cp5.addTextfield("virusName")
                .setPosition(286, 135)
                .setSize(300, 40)
                .setFont(p.createFont(FARRO_REGULAR_FONT_PATH, 16))
                .setColorBackground(p.color(255, 255, 255))
                .setColorForeground(p.color(0, 0, 0))
                .setColorLabel(0)
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
        p.fill(0);

        
        transmissionRateSlider = cp5.addSlider("transmissionRateSlider")
                .setPosition(286, 205)
                .setSize(300, 30)
                .setRange((float) 0.1, (float) 1)
                .setValue((float)0.625)
                .setColorLabel(0)
                .setDecimalPrecision(2)
               
                .setLabel("Transmission Rate");



        deathRateSlider = cp5.addSlider("deathRateSlider")
                .setPosition(286, 265)
                .setSize(300, 30)
                .setRange((float) 0.01, (float)0.025)
                .setValue((float)0.015)
                .setColorLabel(0)
                .setDecimalPrecision(2)
                .setLabel("Death Rate");
               

        p.text("Recovery Rate (%):", 540, 180);
        recoveryRateSlider = cp5.addSlider("recoveryRateSlider")
                .setPosition(286, 325)
                .setSize(300, 30)
                .setRange((float) 0.01, (float) 0.03)
                .setValue((float) 0.02)
                .setColorLabel(0)
                .setDecimalPrecision(2)
                .setLabel("Recovery Rate");
             

        

        mutationRateSlider = cp5.addSlider("mutationRateSlider")
                .setPosition(286, 385)
                .setSize(300, 30)
                .setRange((float) 0 ,(float) 10)
                .setValue((float) 2)
                .setColorLabel(0)
                .setNumberOfTickMarks(10)
                .setDecimalPrecision(2)
                .setLabel("Mutation Rate");
                

        p.text("Starting Country:", 540, 280);
        countryStart = cp5.addDropdownList("countryStart")
                .setPosition(286, 445)
                .setItems(countryOptions)
                .setSize(300, 120)
                .setItemHeight(30)
                .setBarHeight(30)
                .setDecimalPrecision(2)
                .setLabel("Select Country");
        controlsCreated = true;
    }

    public void background() {
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(backgroundImage, 150,0);
    }

    public void rules() {
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(rulesImage, 150,0);
    }
    public String percentages (double p) {
       
      
        return ""+Math.round(p * 1000.0) / 1000.0;
        
        
    }

    public void virusControlScreen() {
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(virusControlImage, 150,0);

        if(!controlsCreated) {
            addButtons();
        }

        p.fill(0);
        p.textFont(mainFont, FEEDBACK_SIZE);
        p.textAlign(PConstants.LEFT);
        p.text(feedbackMessage, 698, 134);

        
        p.textFont(mainFont, BODY_SIZE);
      
        p.textFont(mainFont, FEEDBACK_SIZE);
        p.text("Death Rate: " + percentages((double)deathRateSlider.getValue()) + "%", 696, 180);
        p.text("Recovery Rate: " + percentages((double)recoveryRateSlider.getValue()) + "%", 696, 210);
        p.text("Transmission Rate: " +  percentages((double)transmissionRateSlider.getValue()) + "%" , 696, 240);
        p.text("Mutation Rate: " + percentages((double)mutationRateSlider.getValue()) + "%", 696, 270);
        p.text("Starting Country: " + countryOptions[(int) countryStart.getValue()], 696, 300);
    }

    public void display() {
        if(currentPage == 0) {
            title();
        }
        else if(currentPage == 1) {
            background();
        }
        else if(currentPage == 2){
            rules();
        }
        else if (currentPage == 3) {
           
            virusControlScreen();
            
              
            Virus.setDeathRate((double) deathRateSlider.getValue()); 
            Virus.setRecoveryRate((double) recoveryRateSlider.getValue());
            Virus.setTransmissionRate((double) transmissionRateSlider.getValue());
            Virus.setMutationRate((double) mutationRateSlider.getValue() / 100);
            Virus.setStartingCountry(countryOptions[(int) countryStart.getValue()]);
            
            Virus.setName(virusName.getStringValue());
            
            

        }
        else if(currentPage == 4) {
       
            // Remove controls
            cp5.remove("virusName");
            cp5.remove("deathRateSlider");
            cp5.remove("recoveryRateSlider");
            cp5.remove("transmissionRateSlider");
            cp5.remove("mutationRateSlider");
            cp5.remove("countryStart");
            cp5.remove("Next");

            
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }


}
   

