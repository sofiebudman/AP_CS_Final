package src.main.java.code;

import static src.main.java.code.Constants.Scale.*;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Slider;
import controlP5.Textfield;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

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

    public WelcomeScreen(PApplet p) {
        this.p = p;
        cp5 = new ControlP5(p);
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
                .setColorBackground(p.color(0, 0, 0))
                .setColorForeground(p.color(100, 100, 100))
                .setColorActive(p.color(150, 150, 150))
                .onPress((event) -> {
                    System.out.println("Button pressed - current page: " + currentPage);
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
        virusName = cp5.addTextfield("virusName")
                .setPosition(540, 60)
                .setSize(200, 40)
                .setFont(p.createFont("Arial", 20))
                .setColorBackground(p.color(255, 255, 255))
                .setColorForeground(p.color(0, 0, 0))
                .setColorActive(p.color(0, 0, 0))
                .setColorValue(p.color(0, 0, 0));

        deathRateSlider = cp5.addSlider("deathRateSlider")
                .setPosition(540,120)
                .setSize(200,20)
                .setRange(0,100)
                .setValue(50);

        recoveryRateSlider = cp5.addSlider("recoveryRateSlider")
                .setPosition(540,150)
                .setSize(200,20)
                .setRange(0,100)
                .setValue(50);

        transmissionRateSlider = cp5.addSlider("transmissionRateSlider")
                .setPosition(540,180)
                .setSize(200,20)
                .setRange(0,100)
                .setValue(50);

        countryStart = cp5.addDropdownList("countryStart")
                .setPosition(540,210)
                .setItems(new String[]{"North America", "South America", "Europe", "Africa", "Asia", "Australia"})
                .setSize(200, 100)
                .setItemHeight(20)
                .setBarHeight(30);
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
}
   

