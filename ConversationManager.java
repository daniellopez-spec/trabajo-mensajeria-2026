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

    public void removeParticipant(User user) {
        participants.remove(user);
        System.out.println(user.getName() + " removed from conversation.");
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public Message getMessage(String id) {
        for (Message m : messages) {
            if (m.id.equals(id)) return m;
        }
        return null;
    }

    public void editMessage(String id, String newContent) {
        System.out.println("Message " + id + " edited.");
    }

    public void showMessages() {
        for (Message m : messages) {
            m.show();
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void showPolls() {
        boolean hasPolls = false;

        for (Message m : messages) {
            if (m instanceof PollMessage) {
                hasPolls = true;
                PollMessage pm = (PollMessage) m;
                pm.show();
                pm.showResults();
                System.out.println("----------------------------");
            }
        }

        if (!hasPolls) {
            System.out.println("No polls have been created yet.");
        }
    }
}