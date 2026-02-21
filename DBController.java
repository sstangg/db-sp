import java.util.Scanner;

public class DBController extends Controller<DBBoard> {
    public DBController(Scanner scan) {
        super(scan);
    }

    // run groups the core game flow functions
    // .run() command starts the game
    public void run() {
        try {
            welcome();
            rules();

            boolean replay = true;
            while (replay) {
                startGame();
                Player player = playGame();
                if (player != null){ win(player);
                } else { quit(); }

                replay = replay(); // user chooses whether to play again
            }
            end();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    // welcome greets the player and asks for basic player information
    private void welcome() {
        System.out.println("Welcome to the Dots & Boxes Game!\nThis is a 2 player game. Player 1, what is your name?");
        String name1 = this.scan.next();
        System.out.println("Hello " + name1 + ". What are your pronouns? (she/he/they/etc.)");
        String pronouns1 = this.scan.next();
        this.addPlayer(new Player(name1, pronouns1));

        System.out.println("Player 2, what is your name?");
        boolean invalidInput;
        String name2;
        do {
            name2= this.scan.next();
            if (name2.equals(name1)) {
                System.out.println("Player 2, your name must be different thant Player 1's.");
                invalidInput = true;
            }
            else {
                invalidInput = false;
            }
        } while (invalidInput);
        System.out.println("Hello " + name2 + ". What are your pronouns? (she/he/they/etc.)");
        String pronouns2 = this.scan.next();
        this.addPlayer(new Player(name2, pronouns2));
    } 
    // rules prints game rules
    private void rules() {
        String boldOn = "\u001B[1m";
        String reset = "\u001B[0m";
        System.out.println(String.format("\nDOTS & BOXES GAME\n" + 
                             "In this game, a board of dots will be generated for you to place edges on.\n\n"+ 
                           "How to play:\n"+ 
                            "- Each turn, a player will type 2 numbers to place an edge between those dots & press Enter to confirm your choice.\n"+ 
                            "- Numbers must be entered from LEAST to GREATEST.\n"+ 
                            "- The object of the game is to place the 4th edge of a box and to close the box.\n\n"+  
                            "Whichever player has the most points after all boxes are claimed, wins.\n"+ 
                            "- For every box you close, you gain a point and get to go again. \n"+ 
                              
                             "Example:\n"+ 
                             "An initial 3x3 puzzle could be:\n"+ 
                             "+---+---+---+\n"+ 
                             "| 8 | 3 | 2 |\n"+ 
                             "+---+---+---+\n"+ 
                             "|%1$s 4 %2$s| 1 | 5 |\n"+ 
                             "+---+---+---+\n"+ 
                             "| 6 |   | 7 |\n"+ 
                             "+---+---+---+\n\n"+ 
                            "The solution for a 3x3 puzzle is:\n"+ 
                             "+---+---+---+\n"+ 
                             "|%1$s 1 %2$s|%1$s 2 %2$s|%1$s 3 %2$s|\n"+ 
                             "+---+---+---+\n"+ 
                             "|%1$s 4 %2$s|%1$s 5 %2$s|%1$s 6 %2$s|\n"+ 
                            "+---+---+---+\n"+ 
                            "|%1$s 7 %2$s|%1$s 8 %2$s|   |\n"+ 
                            "+---+---+---+\n\n"+ 
                            "- Enter 'q' to quit.\n"+ 
                            "- Enter 'r' to view these rules again.\n", boldOn, reset) // change example
        );
    }
    private void startGame() {
        System.out.println("The default board is 3x3.\n"+
                           "Would you like to use the default dimensions?\n"+
                           "If not, you can customize the dimensions. (y/n)");
       
        boolean invalidInput = false;
        do {
            String answer = this.scan.next();

            switch (answer) {
                case "y":
                    // question to confirm whether they want dimensions & option to exit?
                    System.out.println("Generating new board with dimensions 3 x 3"); 
                    DBBoard boardDefault = new DBBoard();
                    boardDefault.init();
                    this.setBoard(boardDefault);
                    break;
                
                case "n":
                    boolean invalidDimensions;
                    String rows;
                    String cols; 
                    do {
                        System.out.println("How many rows would you like the board to have? (min: 1)");
                        rows = this.scan.next();  
                        System.out.println("How many columns would you like the board to have? (min: 1)");
                        cols = this.scan.next();
                        
                        try {
                            DBBoard boardCustom = new DBBoard(Integer.parseInt(rows), Integer.parseInt(cols));
                            boardCustom.init();
                            this.setBoard(boardCustom);
                            invalidDimensions = false;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Board dimensions invalid. Try again!");
                            invalidDimensions = true;
                        }
                    } while (invalidDimensions);
                
                    System.out.println("Generating new board with dimensions " + rows + " x " + cols);
                    break;
                
                default: 
                    System.out.println("Enter 'y' or 'n' to proceed.");
                    invalidInput = true;
        }} while (invalidInput);

        System.out.println(this.toString()); 
    }
    // playGame asks users to play each turn until player quits or wins the game
    // returns whether the user won or lost the game when the game ends
    private Player playGame() {
        boolean hasEnded = false;
        Player winner = null;
        Player curPlayer = this.getPlayer1();

        breakAll:
        while(!hasEnded) {
            System.out.println(curPlayer.getName() + ", which 2 dots would you like to add a edge between?"); // need logic on player to pick

            if (this.scan.hasNextInt()) { // valid number
                int num1 = this.scan.nextInt();
                if (this.scan.hasNextInt()) {
                    int num2 = this.scan.nextInt();
                    boolean squareMade;
                    try {   
                        squareMade = move(num1, num2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Try again!");
                        continue;
                    }

                    // decide player next turn
                    if (curPlayer == this.getPlayer1() && !squareMade) {
                        curPlayer = this.getPlayer2();
                    } else {
                        curPlayer = this.getPlayer1();
                    }
                    
                    hasEnded = this.board.checkComplete();
                }
                else {
                  System.out.println("Non-integers not accepted. Try again!"); 
                }
            } else {
                String input = this.scan.next();
                switch (input) {
                    case "q":
                        hasEnded = false;
                        break breakAll;
                    case "r":
                        rules();
                        break;
                    default:
                        System.out.println("Non-integers not accepted. Try again!");
                }
            }
        }
        if (hasEnded) {
            winner = checkWinner(); // check on this
        }
        return winner;
    }
    // move communicates the user's sliding move to the Board object
    private boolean move(int num1, int num2) {
        boolean squareMade = false;
        try {
            squareMade = this.board.placeEdge(num1, num2);
            System.out.println(this.board.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return squareMade;
    }
    // TODO: change this
    private Player checkWinner() {
        return null;
    }

    // replay asks whether user wants to play again
    private boolean replay() {
        System.out.println("Game ended.\nPlay Dots & Boxes again? (y/n)"); 

        boolean replay = false;
        boolean invalidInput;
        do {
            String input = this.scan.next(); 
            switch (input) {
                case "y":
                    replay = true;
                    invalidInput = false;
                    break;
                case "n":
                    invalidInput = false;
                    break;
                default:
                    invalidInput = true;
                    System.out.println("Invalid input. Try again! (y/n)");
            }
        } while (invalidInput);
        return replay;
    }
    // PRINT MESSAGES
    // win message
    private void win(Player player) {
        System.out.println("Congrats, " + player.getName()+ "won!"); // change
    }
    
    // quit message
    private void quit() {
        System.out.println("You quit the Sliding Puzzle game."); 
    }
    
    // end message
    private void end() {
        this.scan.close();
        System.out.println("Sliding Puzzle game ended.");
    }
    // GETTER
    private Player getPlayer1() {
        return this.players.get(0); 
    }
    private Player getPlayer2() {
        return this.players.get(1); 
    }
}
