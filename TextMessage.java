public class TextMessage extends Message {

    public TextMessage(String id, User sender, String content) {
        super(id, sender, content);
    }

    @Override
    public void show() {
        System.out.println("[Text] " + sender + ": " + content);
    }
}