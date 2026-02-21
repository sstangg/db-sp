By: Sophia Tang

# SLIDING PUZZLE GAME

### To run this game in the terminal:
1. navigate to the "sliding-puzzle" directory using cd commands after unzipping the files
2. Enter these commands in order in the terminal:
- javac *.java
- java Main

### File information:
1. Main.java
    - controls the game flow & control
    - contains the main() method

2. Board.java
    - creates & stores the game board data
    - offers method interface that allows Main to manipulate the data

3. Player.java
    - represent Player information (name, pronouns)

## Notes
Extra Features
- quit option available during every turn
- ability to view rules during every turn
- default board dimensions available for the player

### Input / Output Example
**Output:**\
Welcome! \
What is your name?

**Input:** Player\
**Output:**\
Hello Player. \
What are your pronouns? (she/he/they/etc.)

**Input:** they \
**Output:** \
SLIDING PUZZLE GAME \
In this game, a random shuffle puzzle will be generated for you to solve.

How to play:
 - Each turn, type a tile value that is next to the blank space & press Enter to slide it into the blank space.
 - The puzzle is solved when all tiles have been moved around such that \
 the ordering of the tiles are ordered from LEAST to GREATEST from top LEFT to bottom RIGHT.

 Example: \
 An initial 3x3 puzzle could be: \
 +---+---+---+  
|&nbsp;&nbsp; 8 &nbsp;|&nbsp; 3 &nbsp;|&nbsp; 2 &nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 1 &nbsp;|&nbsp; 5 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;|&nbsp; 7 &nbsp;|  
+---+---+---+  

The solution for a 3x3 puzzle is:\
+---+---+---+  
|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 2 &nbsp;|&nbsp; 3 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 6 &nbsp;|&nbsp; 5 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 7 &nbsp;|&nbsp; 8 &nbsp;| &nbsp; &nbsp;&nbsp;&nbsp; |  
+---+---+---+ 
 
 - Enter 'q' to quit.
 - Enter 'r' to view these rules again.

The default board is 3x3.

Would you like to use the default dimensions? \
If not, you can customize the dimensions. (y/n) 

**Input:** y \
**Output:**
Generating new board with dimensions 3 x 3

+---+---+---+  
|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 2 &nbsp;&nbsp;|&nbsp; 3 &nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 5 &nbsp;|&nbsp; 6 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;|&nbsp; 7 &nbsp;|  
+---+---+---+  

Player, which tile do you want to slide to the empty space?  \
**Input:** q \
**Output:** \
You quit the game. \
Game ended. \
Play again? (y/n)

**Input:** y \
**Output:** \
The default board is 3x3.

Would you like to use the default dimensions? \
If not, you can customize the dimensions. (y/n)

**Input:** y \
**Output:** \
Generating new board with dimensions 3 x 3 

+---+---+---+  
|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 2 &nbsp;&nbsp;|&nbsp; 3 &nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 5 &nbsp;|&nbsp; 6 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;|&nbsp; 8 &nbsp;|  
+---+---+---+  

Player, which tile do you want to slide to the empty space?  \
**Input:** 8 \
**Output:**  \
+---+---+---+  
|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 2 &nbsp;|&nbsp; 3 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 6 &nbsp;|&nbsp; 5 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 7 &nbsp;|&nbsp; 8 &nbsp;| &nbsp; &nbsp;&nbsp;&nbsp; |  
+---+---+---+  
Congrats, you won!  \
Game ended. Play again? (y/n)  

**Input:** n  

**Output:**
Game ended. Bye Player!