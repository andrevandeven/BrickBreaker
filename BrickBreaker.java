/*  Name: Andre van de Ven
*  PennKey: andrev
*
*  Execution: java BrickBreaker [filename]
*
*  Represents the Brick Breaker game. Takes a level
*  description text file and initializes a game 
*  with that data, then runs the game until the player
*  wins or loses.
*
*/

public class BrickBreaker {
    
    public static void main(String[] args) {
        PennDraw.setCanvasSize(1000, 500);
        // The game runs at 30 frames per second
        PennDraw.enableAnimation(30);
        Game game = new Game(args[0]);
        final double TIME_STEP = 0.1;
        while (true) {
            if (!game.gameOver()) {
                game.update(TIME_STEP); //update game every 0.1 second
                game.draw();
            } else {
                game.updateGameCompleteScreen();
                game.drawGameCompleteScreen(); //draws victory or defeat screen
            }
        }
        
    }
}