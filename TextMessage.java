public class TextMessage extends Message {

    public TextMessage(String id, User sender, String content) {
        super(id, sender, content);
    }

    public boolean validateLength(int max) {
        return content.length() <= max;
    }

    public String summarizeText() {
        if (content.length() <= 50) return content;
        return content.substring(0, 50) + "...";
    }

    @Override
    public void show() {
        System.out.println("[Text] " + sender + ": " + content);
    }
}