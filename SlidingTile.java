public class SlidingTile extends Tile<Integer> {
    private int correctValue;

    // Tile constructor
    public SlidingTile(int x, int y, int currentValue) {
        super(x, y, currentValue);
    }
    public SlidingTile(int x, int y, int currentValue, int correctValue) {
        super(x, y, currentValue);
    }

    public boolean checkCorrect() {
        return this.getCurrentValue() == this.correctValue;
    }

    // GETTERS
    public int getCorrectValue() {
        return this.correctValue;
    }

}
