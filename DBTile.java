public class DBTile extends Tile<Integer> {
    private DBEdgeHorizontal top;
    private DBEdgeHorizontal bottom;
    private DBEdgeVertical left;
    private DBEdgeVertical right;
    private int playerId;

    public DBTile(int x, int y) {
        super(x,y);
        this.top = null;
        this.bottom = null;
        this.left = null;
        this.right = null;
    }

    // GETTERS
    public boolean checkComplete() {
        return this.top != null && this.bottom != null && this.left != null && this.right != null;
    }
    // for counting & displaying which player has won this tile
    public int checkPlayer() {
        return this.playerId;
    }
    public boolean hasTop() {
        return this.top != null;
    }
    public boolean hasBottom() {
        return this.bottom != null;
    }
    public boolean hasLeft() {
        return this.left != null;
    }
    public boolean hasRight() {
        return this.right != null;
    }
    
    // SETTERS
    public void setTop(DBEdgeHorizontal top) {
        this.top = top;
    }
    public void setBottom(DBEdgeHorizontal bottom) {
        this.bottom = bottom;
    }
    public void setLeft(DBEdgeVertical left) {
        this.left = left;
    }
    public void setRight(DBEdgeVertical right) {
        this.right = right;
    }
}
