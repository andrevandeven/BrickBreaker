/*  Name: Andre van de Ven
*  PennKey: andrev
*  Execution: N/A
*
*  A class representing the arena in which the BrickBreaker
*  game takes place. Keeps track of the game's ball, platform and
*  bricks and receives the player's input to control the platform.
*
*/

public class Game {
    // fields
    private int width, height; 
    private Brick[] bricks;
    private Ball ball; 
    private Platform platform;
    //keeps track of whether the player is currently playing a round
    private boolean roundStarted;


    /**
    * Inputs: a string representing a filename
    * Outputs: none
    * Description: Constructs a game object. 
    */
    public Game(String filename) {
        In in = new In(filename);
        //setting size of target array
        int numBricks = in.readInt();
        bricks = new Brick[numBricks];
        //setting game height and width
        width = in.readInt();
        height = in.readInt();
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);
        //initializing ball
        double radius = in.readDouble();
        int numLives = in.readInt();
        ball = new Ball(width, height, radius, numLives);
        platform = new Platform(width);
        //loop to allocate values to target array
        for (int i = 0; i < bricks.length; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double brickWidth = in.readDouble();
            double brickHeight = in.readDouble();
            int hitPoints = in.readInt();
            bricks[i] = new Brick(xPos, yPos, brickWidth, brickHeight, hitPoints);
        }
        in.close();
        roundStarted = false;
    }

    /**
    * Inputs: none
    * Outputs: a boolean representing whether the player won
    * Description: Returns true when all bricks' hit points are 0.
    * Returns false in any other scenario. 
    */
    private boolean didPlayerWin() {
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i].getHitPoints() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
    * Inputs: none
    * Outputs: a boolean representing whether the player lost
    * Description: Returns true when the ball has less than 0 lives remaining. 
    */
    private boolean didPlayerLose() {
        if (ball.getNumLivesRemaining() < 0) {
            return true;
        }
        return false;
    }

    /**
    * Inputs: none
    * Outputs: a boolean representing whether the the game is over
    * Description: Returns true if the player has either won or lost.  
    */
    public boolean gameOver() {
        return didPlayerWin() || didPlayerLose();
    }

    /**
    * Inputs: none
    * Outputs: a boolean representing whether the the game is over
    * Description: Updates each of the entities within the arena.
    */
    public void update(double timeStep) {
        if (!roundStarted) { //handles state of game before start
            ball.setVelocityFromMousePos();
            if (PennDraw.mousePressed()) {
                roundStarted = true;
            }
        } else { //handles state of game after start
            for (int i = 0; i < bricks.length; i++) {
                bricks[i].update();
            }
            ball.update(timeStep);
            platform.update();
            ball.testAndHandlePlatformCollision(platform);
            for (int i = 0; i < bricks.length; i++) {
                ball.testAndHandleBrickCollision(bricks[i]);
            }
            if (ballIsOffscreen()) {
                ball.decrementLives();
                ball.reset();
                platform.reset();
                roundStarted = false;
            }
        }
        //processes any unprocessed keyboard input
        while (PennDraw.hasNextKeyTyped()) {  
            PennDraw.nextKeyTyped(); 
        }   
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: calls the draw functions of each of the objects contained by 
    * the game and advances to the next frame. 
    */
    public void draw() {
        PennDraw.clear();
        ball.drawLives();
        for (int i = 0; i < bricks.length; i++) { //drawing each brick
            bricks[i].draw();
        }
        platform.draw();
        ball.draw();
        if (!roundStarted) { //draws velocity if game has not started
            ball.drawVelocity();
        }
        PennDraw.advance();
    }

    /**
    * Inputs: none
    * Outputs: a boolean value representing whether the ball is offscreen
    * Description: A helper function for the Game class that lets it know when to
    * reset the ball's position and velocity along with its game state. Returns true
    * when the ball is below the screen. 
    */
    private boolean ballIsOffscreen() {
        return ball.getYpos() + ball.getRadius() < 0;
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: Draws the game complete screen. Called in the view BrickBreaker  
    * class when the game is over. 
    */
    public void drawGameCompleteScreen() {
        PennDraw.clear();
        if (didPlayerLose()) {
            PennDraw.text(width / 2, height / 2, "You have lost...");
        } else if (didPlayerWin()) {
            PennDraw.text(width / 2, height / 2, "You Win!");
        }
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledRectangle(width / 2, height * 0.3, width / 5, height / 20);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.rectangle(width / 2, height * 0.3, width / 5, height / 20);
        PennDraw.text(width / 2, height * 0.3, "Type 's' to restart");
        PennDraw.advance();
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: Updates the game complete screen. Called in the BrickBreaker   
    * class when the game is over. If the key 's' is typed, the game is reset.
    */
    public void updateGameCompleteScreen() {
        if (PennDraw.hasNextKeyTyped() && PennDraw.nextKeyTyped() == 's') {
            resetGame();
        }
    }

    /**
    * Inputs: none
    * Outputs: none
    * Description: Resets the game by reseting the bricks, ball, and platform. 
    */
    private void resetGame() {
        for (int i = 0; i < bricks.length; i++) {
            bricks[i].resetBrick();
        }
        ball.resetLives();
        ball.reset();
        roundStarted = false;
        platform.reset();
    }
}