/*  Name: Andre van de Ven
*  PennKey: andrev
*  Execution: N/A
*
*  A class that represents a brick to be hit in
*  BrickBreaker.
*/

public class Brick {
    //fields
    private double xPos, yPos;
    private double brickWidth, brickHeight; 
    private int hitPoints; 
    //stores maxHP to be able to reset HP
    private int maxHP;
    //stores the number of updates for each hit indicator to appear in
    private int numHitIndicatorUpdates; 
    private boolean hit;


    /**
    * Inputs: the brick's x position, y position, width, height, and hit points.
    * Outputs: none
    * Description: Constructs a brick object. 
    */
    public Brick(double xPos, double yPos, double brickWidth, double brickHeight, 
    int hitPoints) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
        this.hitPoints = hitPoints;
        this.maxHP = hitPoints;
        hit = false;
        //represents the number of updates that the hit indicator remains for
        numHitIndicatorUpdates = 5;  
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: draw a rectangle centered at the brick's position with a width  
    * equal to the brick's width and a height equal to the brick's height. Only draw
    * a brick if it has more than zero hit points. Draws a brick with no cracks if
    * the brick has 3 hitPoints or more. Draws a brick with two cracks if the brick
    * has 2 hitpoints. Draws a brick with four cracks if the brick has 1 hitpoint.
    * Highlights the edges of the brick if the brick is hit to indicate that the 
    * brick has been hit. 
    */
    public void draw() {
        double halfWidth = brickWidth / 2;
        double halfHeight = brickHeight / 2;
        if (hitPoints > 0) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledRectangle(xPos, yPos, halfWidth, halfHeight);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.rectangle(xPos, yPos, halfWidth, halfHeight);
        }
        if (hitPoints == 1) {
            //bottom left crack 
            PennDraw.line(xPos - halfWidth, yPos - halfHeight, 
            xPos - halfWidth / 2, yPos - halfHeight / 2);
            PennDraw.line(xPos - halfWidth / 2, yPos - halfHeight / 2,  
            xPos - halfWidth / 3, yPos - (7 * halfHeight / 10));
            //top right crack
            PennDraw.line(xPos + halfWidth, yPos + halfHeight, xPos + halfWidth / 2,
            yPos + halfHeight / 2);
            PennDraw.line(xPos + halfWidth / 2, yPos + halfHeight / 2,  
            xPos + halfWidth / 3, yPos + (7 * halfHeight / 10));
            //top left crack
            PennDraw.line(xPos - halfWidth, yPos + halfHeight, xPos - halfWidth / 2,
            yPos + halfHeight / 2);
            PennDraw.line(xPos - halfWidth / 2, yPos + halfHeight / 2,  
            xPos - halfWidth / 3, yPos + (7 * halfHeight / 10));
            //bottom right crack
            PennDraw.line(xPos + halfWidth, yPos - halfHeight, xPos + halfWidth / 2,
            yPos - halfHeight / 2);
            PennDraw.line(xPos + halfWidth / 2, yPos - halfHeight / 2,  
            xPos + halfWidth / 3, yPos - (7 * halfHeight / 10));
        } else if (hitPoints == 2) {
            //bottom left crack
            PennDraw.line(xPos - halfWidth, yPos - halfHeight, xPos - halfWidth / 2,
            yPos - halfHeight / 2);
            PennDraw.line(xPos - halfWidth / 2, yPos - halfHeight / 2,  
            xPos - halfWidth / 3, yPos - (7 * halfHeight / 10));
            //top right crack
            PennDraw.line(xPos + halfWidth, yPos + halfHeight, xPos + halfWidth / 2,
            yPos + halfHeight / 2);
            PennDraw.line(xPos + halfWidth / 2, yPos + halfHeight / 2,  
            xPos + halfWidth / 3, yPos + (7 * halfHeight / 10));
        }  
        if (hit) { // draws hit indicator if brick is hit 
            PennDraw.setPenRadius(0.01);
            PennDraw.setPenColor(PennDraw.GREEN);
            PennDraw.rectangle(xPos, yPos, halfWidth, halfHeight);
            PennDraw.setPenRadius(0.002);
        } 
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: updates the state of the brick, so if the brick is hit, 
    * numHitIndicatorUpdates decreases by one. This value will decrease by 1 until
    * it reaches 0. Once it reaches 0, hit is set to false, and 
    * numHitIndicatorUpdates is reset to 5. This allows for the hit indicator to 
    * show up for 5 updates. 
    */
    public void update() {
        if (hit) {
            numHitIndicatorUpdates--;
        }
        if (numHitIndicatorUpdates == 0) {
            hit = false;
            numHitIndicatorUpdates = 5;
        }
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: decreases the brick's HP by 1
    */
    public void decreaseHP() {
        hitPoints--;
    }

    /**
    * Inputs: a boolean value representing what to set the brick's hit value to
    * Outputs: none
    * Description: sets the brick's hit value to the inputted boolean value
    */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
    * Inputs: none
    * Outputs: a boolean value representing whether the brick is hit
    * Description: sets the brick's hit value to the inputted boolean value
    */
    public boolean isHit() {
        return hit;
    }

    /**
    * Inputs: none
    * Outputs: the hit points of the brick
    * Description: a getter function that returns the brick's HP
    */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
    * Inputs: none
    * Outputs: the hit points of the brick
    * Description: a getter function that returns the brick's original HP
    */
    public void resetBrick() {
        hitPoints = maxHP;
        hit = false;
    }

    /**
    * Inputs: none
    * Outputs: the x position of the brick
    * Description: a getter function that returns the brick's x position
    */
    public double getXpos() {
        return xPos;
    }

    /**
    * Inputs: none
    * Outputs: the y position of the brick
    * Description: a getter function that returns the brick's y position
    */
    public double getYpos() {
        return yPos;
    }

    /**
    * Inputs: none
    * Outputs: the width of the brick
    * Description: a getter function that returns the brick's width
    */
    public double getWidth() {
        return brickWidth;
    }

    /**
    * Inputs: none
    * Outputs: the height of the brick
    * Description: a getter function that returns the brick's height
    */
    public double getHeight() {
        return brickHeight;
    }
}