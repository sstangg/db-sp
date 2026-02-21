import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Controller<T extends Board<?, ?>> {
    protected Scanner scan;
    protected T board;
    protected final List<Player> players;
    
    public Controller(Scanner scan) {
        this.scan = scan;
        this.board = null;
        this.players = new ArrayList<>();
    }

    // getters
    protected T getBoard() {
        return this.board;
    }
    // setters
    protected void addPlayer(Player p) {
        this.players.add(p);
    }
    protected void setBoard(T b) {
        this.board = b;
    }

    @Override 
    public String toString() {
        return this.board.toString();
    }

}
