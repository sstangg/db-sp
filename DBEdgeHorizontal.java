public class DBEdgeHorizontal extends DBEdge {
    private DBDot left;
    private DBDot right;

    public DBEdgeHorizontal(DBDot left, DBDot right) {
        if (left.getRow() != right.getRow() || Math.abs(left.getCol() - right.getCol()) != 1) {
            throw new IllegalArgumentException("Error: Attempted to create vertical edge between " + left + ": "+ left.dimString() + " and " + right + ": "+ right.dimString());
        }
        this.left = left;
        this.right = right;
    }
    @Override
    public String toString() {
        return "("+this.left + ", " + this.right + ")";
    }
}