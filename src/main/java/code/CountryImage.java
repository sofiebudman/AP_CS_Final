package src.main.java.code;
import processing.core.PApplet;
import processing.core.PImage;

public class CountryImage  {
   
    private String openPath;
    private String closedPath;
    private boolean isOpen;
   
    
    public CountryImage(String openPath, String closedPath) {
    
        this.openPath = openPath;
        this.closedPath = closedPath;

        isOpen = true;
    }
    public String getPath(){
        return isOpen ? openPath : closedPath;
    }
    public boolean isOpen() {
        return isOpen;
    }
}
