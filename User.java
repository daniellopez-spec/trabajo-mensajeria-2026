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

    public void sendMessage(Message message) {
        System.out.println(name + " sent a message.");
    }

    public void receiveMessage(Message message) {
        System.out.println(name + " received a message.");
    }

    @Override
    public String toString() {
        return name;
    }
}