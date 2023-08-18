PennDraw (similar to std draw) functions are accessible in cis110.jar


How to run program in terminal:
java BrickBreaker brickFile1.txt 
                  (brickFile1.txt can be swapped for another file)

I added a hit indicator, so that the brick turns green for a few frames after it is 
hit. 

BrickBreaker.java : the file where the game is initialized and updated. The game 
                    is initialized based on the file inputted as a command argument.
Game.java : contains the game class. When a game object is initialized, it contains
            bricks, a ball, and a platform based on the file inputted into the 
            constructor. The game class contains methods that update the game, draw
            the game, control whether the game has ended, and a method that resets
            the game.
Ball.java : contains the ball class. When a ball object is initialized, it has a 
            set radius, live count, and position. The ball class contains methods 
            that update the ball, draw the ball, control the movement of the ball, 
            test for collisions with other objects, and alter the lives of the ball. 
Brick.java : contains the brick class. When a brick is initialized, it has a set
             height, width, and position and number of hit points. The brick class 
             contains methods to update a brick's hit indicator, draw a brick based
             on its life count, and alter its hit points. 
Platform.java : contains the platform class. When a platform is initialized, it has
                a set x position and width. It also has a y position and height that
                remain constant across all games, no matter the file inputted. The
                platform class contains methods to draw the platform, reset the 
                platform, and update it based on the mouse position of the player. 
brickFile.txt : a file containing the parameters of the game. The information in
                the file is aranged as follows:

                (number of bricks) (game width) (game height)
                (ball radius) (ball number of lives)
                (brick xPos) (brick yPos) (brick width) (brick height) (brick lives)
                (brick xPos) (brick yPos) (brick width) (brick height) (brick lives)
                (brick xPos) (brick yPos) (brick width) (brick height) (brick lives)
                ...
