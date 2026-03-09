import java.util.ArrayList;
import java.util.List;

public class PollMessage extends Message {

    private String question;
    private List<Option> options;

    public PollMessage(String id, User sender, String question) {
        super(id, sender, question);
        this.question = question;
        this.options = new ArrayList<>();
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void vote(int optionId) {
        for (Option o : options) {
            if (o.getId() == optionId) {
                o.vote();
            }
        }
    }

    public void showResults() {
        System.out.println("Results:");
        for (Option o : options) {
            System.out.println(o.getText() + " -> " + o.getVotes() + " votes");
        }
    }

    @Override
    public void show() {
        System.out.println("[Poll] " + question);
        for (Option o : options) {
            System.out.println(o.getId() + ". " + o.getText());
        }
    }
}