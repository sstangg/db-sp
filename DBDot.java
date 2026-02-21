public class DBDot {
    private final int x;
    private final int y;
    private final int id;

    public DBDot(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    // GETTERS
    public int getRow() {
        return this.x;
    }
    public int getCol() {
        return this.y;
    }
    // String methods
    @Override
    public String toString() {
        return Integer.toString(id);
    }
    // for error checking
    public String dimString() {
        return "("+this.getRow()+", "+this.getCol()+")";
    }
}
