package src.main.java.code;

import static src.main.java.code.Constants.Scale.*;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Slider;
import controlP5.Textfield;
import processing.core.PApplet;
import processing.core.PImage;

public class WelcomeScreen {

    private PApplet p;

    PImage titleImage;
    PImage backgroundImage;
    PImage virusControlImage;
    private ControlP5 cp5;
    //private boolean showWelcome;
    private boolean controlsCreated;

    private int currentPage = 0; // 0 for welcome, 1 for instructions, 2 for set up


    ///buttons
    /// 
    private Button nextButton;
    private Textfield virusName;
    private Slider deathRateSlider;
    private Slider recoveryRateSlider;
    private Slider transmissionRateSlider; 
    private DropdownList countryStart;
    public WelcomeScreen(PApplet p) {
        this.p = p;
    
        cp5 = new ControlP5(p);
        //showWelcome = true;
        titleImage = p.loadImage("src/main/resources/elements/title.png");
        backgroundImage = p.loadImage("src/main/resources/elements/background.png");
        virusControlImage = p.loadImage("src/main/resources/elements/virusControl.png");
        titleImage.resize(WIDTH_SCALE,0);
        backgroundImage.resize(WIDTH_SCALE-100,0);
        virusControlImage.resize(WIDTH_SCALE-100,0);

        controlsCreated = false;


        currentPage = 0;

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
    public void addButtons(){
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
                .setSize(200, 100)  // dropdown height; each item height is set below
                .setItemHeight(20)  // height of each dropdown item
                .setBarHeight(30) ;
        controlsCreated = true;

    }
    public void title(){ 

        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
       
        p.image(titleImage,80, -100);

    }
    public void background(){
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(backgroundImage, 150,0);

    }

    public void virusControlScreen(){
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.image(virusControlImage, 150,0);

        if(!controlsCreated){
            addButtons();
            

        }
         
        
    }

    public void display(){
        
        if(currentPage == 0){
            title();
        }
        if(currentPage == 1){
            background();
        }
        if (currentPage == 2){
            virusControlScreen();
        }
        if(currentPage == 3){
            cp5.remove("virusName");
            cp5.remove("deathRateSlider");
            cp5.remove("recoveryRateSlider");
            cp5.remove("transmissionRateSlider");
            cp5.remove("countryStart");
            cp5.remove("Next");
            //showWelcome = false;
        }
        //title();
         
        
        
        /* 
        p.fill(255,255,255);
        p.rect(0, 0, p.width, p.height);
        p.fill(200, 200, 200);
        p.rect(80,80,1300,550);

        p.fill(230,230,230);
        p.rect(90,90,1280,530);
        
        p.fill(0, 0, 0);

        p.text("Welcome to the Virus Simulation Game!", 400, 150);

        p.textSize(18);
        p.text("This simulation models the spread of an infectious disease and its potential impact on public health, global infrastructure, and society.\n" + //
                        "You will have the ability to modify key virus parameters and observe how changes affect transmission dynamics across regions.\n" + //
                        "As part of your role, you can influence international policy — including the opening and closing of borders and trade routes — to mitigate the spread of infection.\n" + //
                        "Every decision affects the outcome. Monitor closely, act strategically.\n" + //
                        "", 120,180);*/
    }
}
   

