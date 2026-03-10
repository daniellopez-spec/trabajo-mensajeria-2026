public class User {

    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void registerUser() {
        System.out.println("User " + name + " registered successfully.");
    }

    public void sendMessage(Message message) {
        System.out.println(name + " sent a message.");
    }

    public void receiveMessage(Message message) {
        System.out.println(name + " received a message.");
    }

    public void participateInConversation() {
        System.out.println(name + " is participating in the conversation.");
    }

    @Override
    public String toString() {
        return name;
    }
}