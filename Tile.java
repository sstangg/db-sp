public abstract class Tile<T> {
    private final int x;
    private final int y;
    private T currentValue;
    
    // constructors
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentValue = null;
    }
    public Tile(int x, int y, T currentValue) {
        this.x = x;
        this.y = y;
        this.currentValue = currentValue;
    }

    // getters
    public T getCurrentValue() {
        return this.currentValue;
    }
    public int getRow() {
        return this.x;
    }
    public int getCol() {
        return this.y;
    }
    // setters
    public void setCurrentValue(T val) {
        this.currentValue = val;
    }

}