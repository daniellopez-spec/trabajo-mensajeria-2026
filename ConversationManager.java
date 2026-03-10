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
}