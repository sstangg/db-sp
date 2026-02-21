import java.util.HashMap;

public class DBBoard extends Board<Integer, DBTile> {
    private final HashMap<Integer, DBDot> numMap; // change to diff type?

    public DBBoard() {
        super(3, 3, new DBTile[3][3]);
        this.numMap = new HashMap<>();
    }
    public DBBoard(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("Dimensions smaller than 1 not permitted.");
        }
        super(rows, cols, new DBTile[rows][cols]);
        this.numMap = new HashMap<>();
    }
    @Override
    public void init() {
        fillBoard();
    }
    // fillBoard assign correct values to board
    private void fillBoard() {
        int dot= 1;
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {
                DBTile t = new DBTile(r, c);
                this.addTile(t);
                this.numMap.put(dot, new DBDot(r,c, dot)); // check if ids are correct
                dot++; // change
            }

        }
    }
    // slideTile allows users to slide the board tiles
    public boolean placeEdge(int num1, int num2) {
        // check nums in board
        if (!this.numMap.containsKey(num1) || !this.numMap.containsKey(num2) || num1 == num2) {
            throw new IllegalArgumentException("Numbers invalid. Try again!"); 
        }

        // check nums next to each other & direcion
        DBDot point1 = this.numMap.get(num1);
        DBDot point2 = this.numMap.get(num2);

        if (point1.getRow() == point2.getRow()) {
            DBEdgeHorizontal edge;
            if (point1.getCol() > point2.getCol()) {
                edge = new DBEdgeHorizontal(point2, point1);
            } else {
                edge = new DBEdgeHorizontal(point1, point2);
            }
        } else if (point1.getCol() == point2.getCol()) {
            DBEdgeVertical edge;
            if (point1.getRow() > point2.getRow()) {
                edge = new DBEdgeVertical(point2, point1);
            } else {
                edge = new DBEdgeVertical(point1, point2);
            }
        } else {
            throw new IllegalArgumentException(num1 + " and " + num2 + " not adjacent");
        }

        // change to add edge to map & tile..
        /* 
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
        */
       return this.getTile(num1, num2).checkComplete();
    }
    // check whether the current board has the correct values
    public boolean checkComplete() {
        boolean isComplete = true;

        breakAll:
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {
                DBTile tile = this.getTile(r, c);

                if (!tile.checkComplete()) {
                    isComplete = false;
                    break breakAll;
                }
            }
        }
        return isComplete;
    }

    @Override
    public String toString() {
        String boardString = "";
            
        for (int r = 0; r <= this.getRows(); r++) {
            boardString += "\n *";
            // print dots
            for (int c = 0; c < this.getCols(); c++) {
                boardString += "    *";
                DBTile tile = this.getTile(r, c);
                // change: check if edge
            }
            /* 
            // print nums
            if (r < this.getRows()){
                boardString += "\n | ";
                for (int c = 0; c < this.getCols(); c++) {
                    DBTile tile = this.getTile(r, c);
                    int num = tile.getCurrentValue();
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
            }*/
        
        }
        boardString += "\n";

        return boardString;
    }
    // boldInt is a helper method for toString --> change to color smth
    // to bold numbers when the number is in the right place
    private static String boldInt(int n) {
        String boldOn = "\u001B[1m";
        String reset = "\u001B[0m";

        return boldOn + n + reset;
    }
}
