import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SlidingBoard extends Board<Integer, SlidingTile> {
    private final HashMap<Integer, Point> numMap;
    private final static int BLANK = 0;

    // default constructor
    public SlidingBoard() {
        super(3, 3, new SlidingTile[3][3]);
        this.numMap = new HashMap<>();
    }
    // custom constructors
    public SlidingBoard(int rows, int cols) {
        // min reqs for dimensions
        if (rows < 2 || cols < 2) {
            throw new IllegalArgumentException("Dimensions smaller than 2 not permitted.");
        }
        super(rows, cols, new SlidingTile[rows][cols]);
        this.numMap = new HashMap<>();
    }

    // initializes the board by filling it with tiles, then mixing the tiles up
    // ensures that the game is solvable
    @Override
    public void init() {
        fillBoard();
        randomize();
    }
    // fillBoard assign correct values to board
    private void fillBoard() {
        int num = 1;
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {
                if (c == this.getCols()-1 && r == this.getRows()-1) {
                    num = BLANK;
                }
                SlidingTile t = new SlidingTile(r, c, num, num);
                this.addTile(t);
                this.numMap.put(num, new Point(r,c));
                num++;
            }

        }
        System.out.println(this);
    }

    // randomize board moves tiles a random number of times around the board to mix the puzzle up
    private void randomize() {
        int num = (int) (Math.random()*(this.getRows()*this.getCols()));
        Point blankPoint;
        try {
            for (int i = 0; i < num; i++) {
                // add adjacent tiles to options to choose from
                blankPoint = this.numMap.get(BLANK);
                List<Point> options = new ArrayList<>();
            
                if (blankPoint.x > 0) {
                    options.add(new Point(blankPoint.x-1, blankPoint.y));
                } if (blankPoint.x < this.getRows()-1) {
                    options.add(new Point(blankPoint.x+1, blankPoint.y));
                } if (blankPoint.y > 0) {
                    options.add(new Point(blankPoint.x, blankPoint.y-1));
                } if (blankPoint.y < this.getCols()-1) {
                    options.add(new Point(blankPoint.x, blankPoint.y+1));
                }

                int optionNum = (int) (Math.random()*(options.size()));
                Point optionPoint = options.get(optionNum);
                int movedNum = this.getTile(optionPoint.x, optionPoint.y).getCurrentValue();

                this.slideTile(movedNum);
            }
                
        } catch (Exception e) {
            System.err.println("Randomization error: " + e);
        }
        
    }

    // slideTile allows users to slide the board tiles
    public void slideTile(int num) {
        // check num in board
        if (!this.numMap.containsKey(num)) {
            throw new IllegalArgumentException("Number out of bounds. Try again!"); 
        }

        // check num is next to blank tile
        Point numPoint = this.numMap.get(num);
        Point blankPoint = this.numMap.get(BLANK);
        if (!(numPoint.x == blankPoint.x && Math.abs(numPoint.y-blankPoint.y)==1) &&
            !(Math.abs(numPoint.x-blankPoint.x)==1 && numPoint.y == blankPoint.y)) {
            throw new IllegalArgumentException("Number "+ num+ " not adjacent to blank tile");
        }

        // change numMap
        Point temp = this.numMap.get(num);
        this.numMap.put(num, this.numMap.get(BLANK));
        this.numMap.put(BLANK, temp);

        // change board
        int numRow = this.numMap.get(num).x;
        int numCol = this.numMap.get(num).y;
        this.setTileValue(numRow, numCol,num);

        int blankRow = this.numMap.get(BLANK).x;
        int blankCol = this.numMap.get(BLANK).y;
        this.setTileValue(blankRow, blankCol,BLANK);
    }
    
    @Override
    public String toString() {
        String boardString = "";

        for (int r = 0; r <= this.getRows(); r++) {
            boardString += "\n +";
            // print border
            for (int c = 0; c < this.getCols(); c++) {
                boardString += "----+";
            }

            // print nums
            if (r < this.getRows()){
                boardString += "\n | ";
                for (int c = 0; c < this.getCols(); c++) {
                    SlidingTile tile = this.getTile(r, c);
                    int num = tile.getCurrentValue();

                    // make the number bold if the position is correct
                    String numStr = String.valueOf(num);
                    if (tile.checkCorrect()) {
                        numStr = boldInt(num);
                    }

                    if (num == 0) {
                        boardString += "   | ";
                    }else if (num >= 10){ // add less spaces for double digits
                        boardString += numStr +" | ";
                    }
                    else { // add more spaces for single digits
                        boardString += numStr +"  | ";
                    }
                }
            }
        
        }
        boardString += "\n";

        return boardString;
    }
    // boldInt is a helper method for toString
    // to bold numbers when the number is in the right place
    private static String boldInt(int n) {
        String boldOn = "\u001B[1m";
        String reset = "\u001B[0m";

        return boldOn + n + reset;
    }
    
    // check whether the current board has the correct values
    public boolean checkSolved() {
        boolean isSolved = true;

        breakAll:
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {

                SlidingTile tile = this.getTile(r, c);

                if (!tile.checkCorrect()) {
                    isSolved = false;
                    break breakAll;
                }
            }
        }
        return isSolved;
    }
}
