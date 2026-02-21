public class DBEdgeVertical extends DBEdge{
    private DBDot top;
    private DBDot bottom;

    public DBEdgeVertical(DBDot top, DBDot bottom) {
        if (top.getRow() != bottom.getRow() || Math.abs(top.getCol() - bottom.getCol()) != 1) {
            throw new IllegalArgumentException("Error: Attempted to create vertical edge between " + top + ": "+ top.dimString() + " and " + bottom + ": "+  bottom.dimString());
        }
        this.top = top;
        this.bottom = bottom;
    }
    @Override
    public String toString() {
        return "("+this.top + ", " + this.bottom + ")";
    }

}