/*  Name: Andre van de Ven
*  PennKey: andrev
*  Execution: N/A
*
*  A class that represents the platform to be 
*  controlled by the player in BrickBreaker.
*/

public class Platform {
    //fields 
    private static final double Y_POS = 5; //y position is constant
    private static final double HEIGHT = 7; //height is constant
    private double xPos, width, gameWidth;

    /**
    * Inputs: the platform's x position
    * Outputs: none
    * Description: Constructs a platform object. 
    */
    public Platform(double gameWidth) {
        this.xPos = gameWidth / 2;
        this.gameWidth = gameWidth;
        width = 25;
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: draw a rectangle centered at the platform's position with a   
    * width equal to the platform's width and a height equal to the platform's 
    * height. 
    */
    public void draw() {
        PennDraw.setPenColor(PennDraw.BOOK_LIGHT_BLUE);
        PennDraw.filledRectangle(xPos, Y_POS, width / 2, HEIGHT / 2);
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: sets the x position of the platform to the x position of the 
    * mouse.
    */
    public void update() {
        xPos = PennDraw.mouseX();
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: resets the x position of the platform to its initial position
    */
    public void reset() {
        xPos = gameWidth / 2; 
    }

    /**
    * Inputs: none
    * Outputs: the x position of the platform
    * Description: a getter function that returns the platform's xPosition
    */
    public double getXpos() {
        return xPos;
    }

    /**
    * Inputs: none
    * Outputs: the y position of the platform
    * Description: a getter function that returns the platform's xPosition
    */
    public double getYpos() {
        return Y_POS;
    }

    /**
    * Inputs: none
    * Outputs: the width of the platform
    * Description: a getter function that returns the platform's width
    */
    public double getWidth() {
        return width;
    }

    /**
    * Inputs: none
    * Outputs: the height of the platform
    * Description: a getter function that returns the platform's width
    */
    public double getHeight() {
        return HEIGHT;
    }
}