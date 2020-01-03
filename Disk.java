 /*  Name: Natalie Ho
  *  PennKey: natabnho
  *  Recitation: 204
  *
  *  Creates a Connect 4 disk piece of either red or yellow at
  * a specific position
  *
  */
public class Disk {
   // The color, radius, and position fields for the disk
  private String color; 
  private double radius; 
  private double xPos, yPos; 
  
 /**
  * Initialize the disk's member variables
  * with the same names as the inputs to those values.
  */
  public Disk(String color, double radius, double xPos, double yPos) {
    this.color = color; 
    this.radius = radius;
    this.xPos = xPos;
    this.yPos = yPos;
  }
  
 /**
  * Draws disk depending on color initialized in
  * constructor
  */
  public void draw() {
    if (color.equals("YELLOW")) {
      PennDraw.setPenColor(PennDraw.YELLOW); 
    }
    if (color.equals("RED")) {
      PennDraw.setPenColor(PennDraw.RED); 
    }
    
    PennDraw.filledCircle(xPos, yPos, radius); 
  }
  
}