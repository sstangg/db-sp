import java.util.Scanner;

public class SlidingController extends Controller<SlidingBoard> {
    // GameController constructor
    public SlidingController(Scanner scan) {
        super(scan);
        this.scan = scan;
    }

    // PUBLIC METHODS

    // run groups the core game flow functions
    // .run() command starts the game
    public void run() {
        try {
            welcome();
            rules();

            boolean replay = true;
            while (replay) {
                startGame();
                boolean isSolved = playGame();
                if (isSolved){ win();
                } else { quit(); }

                replay = replay(); // user chooses whether to play again
            }
            end();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }

    // PRIVATE METHODS
    
    // welcome greets the player and asks for basic player information
    private void welcome() {
        System.out.println("Welcome to the Sliding Puzzle Game!\nWhat is your name?");
        String name = this.scan.next();
        System.out.println("Hello " + name + ". What are your pronouns? (she/he/they/etc.)");
        String pronouns = this.scan.next();
        this.addPlayer(new Player(name, pronouns));
    }  
    
    // rules prints game rules
    private void rules() {
        String boldOn = "\u001B[1m";
        String reset = "\u001B[0m";
        System.out.println(String.format("\nSLIDING PUZZLE GAME\n" + 
                             "In this game, a random shuffle puzzle will be generated for you to solve.\n\n"+ 
                           "How to play:\n"+ 
                            "- Each turn, type a tile value that is next to the blank space & press Enter to slide it into the blank space.\n"+ 
                            "- The puzzle is solved when all tiles have been moved around such that \n"+ 
                            "the ordering of the tiles are ordered from LEAST to GREATEST from top LEFT to bottom RIGHT.\n"+ 
                            "- When a tile is in the correct position, the number in terminal will be printed as bolded.\n\n"+   
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
                            "- Enter 'r' to view these rules again.\n", boldOn, reset)
        );
    }
    
    // startGame asks for user input to initialize Board
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
                    SlidingBoard boardDefault = new SlidingBoard();
                    boardDefault.init();
                    this.setBoard(boardDefault);
                    break;
                
                case "n":
                    boolean invalidDimensions;
                    String rows;
                    String cols; 
                    do {
                        System.out.println("How many rows would you like the board to have? (min: 2)");
                        rows = this.scan.next();  
                        System.out.println("How many columns would you like the board to have? (min: 2)");
                        cols = this.scan.next();
                        
                        try {
                            SlidingBoard boardCustom = new SlidingBoard(Integer.parseInt(rows), Integer.parseInt(cols));
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
    private boolean playGame() {
        boolean isSolved = false;

        breakAll:
        while(!isSolved) {
            System.out.println(this.getPlayerName() + ", which tile do you want to slide to the empty space?"); 

            if (this.scan.hasNextInt()) { // valid number
                int num = this.scan.nextInt();
                try {   
                    move(num);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try again!");
                    continue;
                }
                isSolved = this.board.checkSolved();
            } else {
                String input = this.scan.next();
                switch (input) {
                    case "q":
                        isSolved = false;
                        break breakAll;
                    case "r":
                        rules();
                        break;
                    default:
                        System.out.println("Non-integers not accepted. Try again!");
                }
            }
        }
        return isSolved;
    }
    
    // move communicates the user's sliding move to the Board object
    private void move(int num) {
        try {
            this.board.slideTile(num);
            System.out.println(this.board.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // replay asks whether user wants to play again
    private boolean replay() {
        System.out.println("Game ended.\nPlay Sliding Puzzle again? (y/n)"); 

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
    
    // win message
    private void win() {
        System.out.println("Congrats, you won!"); 
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
    private String getPlayerName() {
        return this.players.get(0).getName();
    }
}
