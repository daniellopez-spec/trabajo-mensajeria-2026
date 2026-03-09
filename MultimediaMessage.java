public class MultimediaMessage extends Message {

    private String type;
    private String url;

    public MultimediaMessage(String id, User sender, String content, String type, String url) {
        super(id, sender, content);
        this.type = type;
        this.url = url;
    }

    public void play() {
        System.out.println("Playing " + type + " from: " + url);
    }

    @Override
    public void show() {
        System.out.println("[Multimedia] " + sender + ": " + content + " (" + type + ")");
    }
}