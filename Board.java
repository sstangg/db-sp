public abstract class Board<T, TILE extends Tile<T>>{
    private final int rows;
    private final int cols;
    private TILE [][] board; // needs to be initialized in child constructors

    public Board(int rows, int cols, TILE [][] b) {
        this.rows = rows;
        this.cols = cols;
        if (b.length != rows || b[0].length != cols){
            throw new IllegalArgumentException("Rows & columns in Tile array don't match rows & cols parameters");
        }
        this.board = b;
    }
    // abstract methods to be implemented
    // init should add tiles to the board
    protected abstract void init(); 

    @Override
    public abstract String toString();

    // GETTERS
    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.cols;
    }
    public TILE getTile(int r, int c) {
        return this.board[r][c];
    }
    // SETTERS
    public void setTileValue(int r, int c, T val) {
        this.getTile(r, c).setCurrentValue(val);

    }
    public void addTile(TILE tile) {
        this.board[tile.getRow()][tile.getCol()] = tile;
    }
}
