public class Player {
    private final String name;
    private final String pronoun;

    public Player(String name, String pronoun) {
        this.name = name;
        this.pronoun = pronoun;
    }
    public String getName() {
        return this.name;
    }
    public String getPronoun() {
        return this.pronoun;
    }

}
