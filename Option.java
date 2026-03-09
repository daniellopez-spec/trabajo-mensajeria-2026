public class Option {

    private int id;
    private String text;
    private int votes;

    public Option(int id, String text) {
        this.id = id;
        this.text = text;
        this.votes = 0;
    }

    public void vote() {
        votes++;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getVotes() {
        return votes;
    }
}