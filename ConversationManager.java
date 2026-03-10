import java.util.ArrayList;
import java.util.List;

public class ConversationManager {

    private List<User> participants; 
    private List<Message> messages;
     
    public ConversationManager() {
        participants = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public void showMessages() {
        for (Message m : messages) {
            m.show();
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    // NUEVO
    public void showPolls() {
        boolean hayEncuestas = false;

        for (Message m : messages) {
            if (m instanceof PollMessage) {
                hayEncuestas = true;
                PollMessage pm = (PollMessage) m;
                pm.show();
                pm.showResults();
                System.out.println("----------------------------");
            }
        }

        if (!hayEncuestas) {
            System.out.println("No polls have been created yet.");
        }
    }
}