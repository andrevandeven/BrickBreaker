/*  Name: Andre van de Ven
*  PennKey: andrev
*  Execution: N/A
*
*  A class that represents the ball projectile in
*  Brick Breaker. Can update its own position based
*  on velocity and time, and can compute whethes
*  it overlaps a given brick or the platform.
*
*/

public class Ball {
    //fields
    private int width, height; // width and height of game
    private double xPos, yPos, xVel, yVel, radius; 
    private double initialXpos; //stores initial xPosition to be able to reset xPos
    private int numLivesRemaining;
    private int maxLives; //strores maxLives to be able to reset lives 

    /**
    * Inputs: the game's height and radius, the ball's radius and number of lives.
    * Outputs: none
    * Description: Constructs a ball object. 
    */
    public Ball(int width, int height, double radius, int numLives) {
        this.width = width;
        this.height = height;
        this.xPos = width / 2;
        this.initialXpos = xPos;
        this.yPos = 8.5 + radius;
        this.radius = radius;
        this.numLivesRemaining = numLives;
        this.maxLives = numLives;
        xVel = 0.0;
        yVel = 0.0;
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: draw a circle centered at the balls's position with a radius  
    * equal to the ball's radius.
    */
    public void draw() {
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledCircle(xPos, yPos, radius);
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: draws a heart in the bottom left corner of the screen with 
    * a counter that displays the number of lives remaining
    */
    public void drawLives() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(30);
        PennDraw.text(12, 4, "x" + numLivesRemaining);
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledPolygon(5, 2, 7, 5, 3, 5);
        PennDraw.filledArc(4, 5, 1, 0, 180);
        PennDraw.filledArc(6, 5, 1, 0, 180);
    }

    /**
    * Inputs: a double representing one time step
    * Outputs: none
    * Description: updates the x and y position of the ball using its velocity 
    * values.
    */
    public void update(double timeStep) {
        xPos = xPos + xVel * timeStep;
        yPos = yPos + yVel * timeStep;
        if (xPos <= radius) {
            xPos = radius;
            xVel = -xVel;
        } else if (xPos >= width - radius) {
            xPos = width - radius;
            xVel = -xVel;
        }
        if (yPos >= height - radius) {
            yPos = height - radius;
            yVel = -yVel;
        }
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: draws a line from the ball in the direction of the cursor, 
    * representing the direction the ball will be released in. The length of the 
    * line represents the position of the ball after one timestep. 
    */
    public void drawVelocity() {
        PennDraw.line(xPos, yPos, xPos + xVel, yPos + yVel);
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: sets the initial velocity of the ball based on the position of 
    * the cursor relative to the mouse. The magnitude of the ball's velocity will 
    * always remain the same, but the direction of the velocity varies depending on
    * the position of the cursor. 
    */
    public void setVelocityFromMousePos() {
        double dist = distance(PennDraw.mouseX(), PennDraw.mouseY(), xPos, yPos);
        xVel = 15 * (PennDraw.mouseX() - xPos) / dist;
        yVel = 15 * (PennDraw.mouseY() - yPos) / dist;
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: resets the position and velocity of the ball to their initial 
    * values. 
    */
    public void reset() {
        xPos = initialXpos; 
        yPos = 9 + radius; 
        xVel = 0;
        yVel = 0; 
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: a helper function used to find the distance between two 2D points
    */
    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }


    
    /**
    * Inputs: none
    * Outputs: none
    * Description: tests if the ball has collided with a brick and alters the ball's
    * velocity if the ball collides with a brick
    */
    public void testAndHandleBrickCollision(Brick b) {
        double rightEdge = b.getXpos() + (b.getWidth() / 2);
        double leftEdge = b.getXpos() - (b.getWidth() / 2);
        double topEdge = b.getYpos() + (b.getHeight() / 2);
        double bottomEdge = b.getYpos() - (b.getHeight() / 2);
        //tests for contact with right side
        if ((xPos <= rightEdge + radius) && (xPos >= rightEdge - 1) &&  
        (yPos <= topEdge) && (yPos >= bottomEdge) && b.getHitPoints() > 0) {
            xVel *= -2.0;
            //caps speed at 17.5
            if (xVel > 17.5) { 
                xVel = 17.5;
            } else if (xVel < -17.5) {
                xVel = -17.5;
            }
            //sets brick position to edge of brick 
            xPos = rightEdge + radius;
            brickCollisionHelper(b);
        }
        //tests for contact with left side
        if ((xPos <= leftEdge + 1) && (xPos >= leftEdge - radius) &&  
        (yPos <= topEdge) && (yPos >= bottomEdge) && b.getHitPoints() > 0) {
            xVel *= -2.0;
            //caps speed at 17.5
            if (xVel > 17.5) {
                xVel = 17.5;
            } else if (xVel < -17.5) {
                xVel = -17.5;
            }
            //sets brick position to edge of brick 
            xPos = leftEdge - radius;
            brickCollisionHelper(b);
        }
        //tests for contact with top
        if ((xPos <= rightEdge) && (xPos >= leftEdge) && (yPos <= 
        topEdge + radius) && (yPos >= topEdge - 1) && b.getHitPoints() > 0) {
            yVel *= -2.0;
            //caps speed at 17.5
            if (yVel > 17.5) {
                yVel = 17.5;
            } else if (yVel < -17.5) {
                yVel = -17.5;
            }
            //sets brick position to edge of brick 
            yPos = topEdge + radius;
            brickCollisionHelper(b);
        }
        //tests for contact with bottom
        if ((xPos <= rightEdge) && (xPos >= leftEdge) && (yPos <= bottomEdge + 1) &&
        (yPos >= bottomEdge - radius) && b.getHitPoints() > 0) {
            yVel *= -2.0;
            //caps speed at 17.5
            if (yVel > 17.5) {
                yVel = 17.5;
            } else if (yVel < -17.5) {
                yVel = -17.5;
            }
            //sets brick position to edge of brick 
            yPos = bottomEdge - radius;
            brickCollisionHelper(b);
        }
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: a helper function for testAndHandleBrickCollision that sets 
    * the brick to hit and dereases the brick's hp if the ball hits the brick. 
    * If the brick is already hit, the brick's hp does not decrease.  
    */
    private void brickCollisionHelper(Brick b) {
        if (!b.isHit()) {
                b.decreaseHP(); 
            }
        b.setHit(true);
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: tests if the ball has collided with the platform and alters the 
    * ball's velocity if the ball collides with a platform
    */
    public void testAndHandlePlatformCollision(Platform p) {
        double rightEdge = p.getXpos() + (p.getWidth() / 2);
        double leftEdge = p.getXpos() - (p.getWidth() / 2);
        double topEdge = p.getYpos() + (p.getHeight() / 2);
        double bottomEdge = p.getYpos() - (p.getHeight() / 2);
        //tests for contact with right side
        if ((xPos <= rightEdge + radius) && (xPos >= rightEdge - 1) &&  
        (yPos <= topEdge) && (yPos >= bottomEdge)) {
            xVel *= -0.75;
            //minimum speed is 5
            if (xVel < 5 && xVel >= 0) {
                xVel = 5;
            } else if (xVel > -5 && xVel < 0) {
                xVel = -5;
            }
            //sets brick position to edge of platform 
            xPos = rightEdge + radius;
        }
        //tests for contact with left side
        if ((xPos <= leftEdge + 1) && (xPos >= leftEdge - radius) &&  
        (yPos <= topEdge) && (yPos >= bottomEdge)) {
            xVel *= -0.75;
            //minimum speed is 5
            if (xVel < 5 && xVel >= 0) {
                xVel = 5;
            } else if (xVel > -5 && xVel < 0) {
                xVel = -5;
            }
            //sets brick position to edge of platform 
            xPos = leftEdge - radius;
        }
        //tests for contact with top
        if ((xPos <= rightEdge) && (xPos >= leftEdge) && 
        (yPos <= topEdge + radius) && (yPos >= topEdge - 1)) {
            yVel *= -0.75;
            //minimum speed is 5
            if (yVel < 5 && yVel >= 0) {
                yVel = 5;
            } else if (yVel > -5 && yVel < 0) {
                yVel = -5;
            }
            //sets brick position to edge of brick 
            yPos = topEdge + radius;
        }
        //tests for contact with bottom
        if ((xPos <= rightEdge) && (xPos >= leftEdge) && (yPos <= bottomEdge + 1) &&
        (yPos >= bottomEdge - radius)) {
            yVel *= -0.75;
            //minimum speed is 5
            if (yVel < 5 && yVel >= 0) {
                yVel = 5;
            } else if (yVel > -5 && yVel < 0) {
                yVel = -5;
            }
            //sets brick position to edge of brick 
            yPos = bottomEdge - radius;
        }
    }


    /**
    * Inputs: none
    * Outputs: none
    * Description: reduces the ball's live counter by one by reducing
    * numLivesRemaining
    */
    public void decrementLives() {
        numLivesRemaining--;
    }

    /**
    * Inputs: none
    * Outputs: the ball's x position
    * Description: a getter function that returns the x position of the ball
    */
    public double getXpos() {
        return xPos;
    }

    /**
    * Inputs: none
    * Outputs: the ball's y position
    * Description: a getter function that returns the y position of the ball
    */
    public double getYpos() {
        return yPos;
    }

    /**
    * Inputs: none
    * Outputs: the ball's radius
    * Description: a getter function that returns the radius of the ball
    */
    public double getRadius() {
        return radius;
    }

    /**
    * Inputs: none
    * Outputs: the ball's number of lives remaining
    * Description: a getter function that returns the ball's remaining lives
    */
    public int getNumLivesRemaining() {
        return numLivesRemaining;
    } 

    /**
    * Inputs: none
    * Outputs: none
    * Description: a setter function that resets the ball's remaining lives
    */
    public void resetLives() {
        numLivesRemaining = maxLives;
    } 
}