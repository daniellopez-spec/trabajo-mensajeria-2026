public class MultimediaMessage extends Message {

    private String type;
    private String url;

    private static final String[] VALID_FORMATS = {"video", "audio", "image"};

    public MultimediaMessage(String id, User sender, String content, String type, String url) {
        super(id, sender, content);
        this.type = type;
        this.url = url;
    }

    public boolean validateFormat() {
        for (String format : VALID_FORMATS) {
            if (format.equalsIgnoreCase(type)) return true;
        }
        System.out.println("Invalid format: " + type);
        return false;
    }

    public void play() {
        System.out.println("Playing " + type + " from: " + url);
    }

    public void viewImage() {
        if (type.equalsIgnoreCase("image")) {
            System.out.println("Viewing image from: " + url);
        } else {
            System.out.println("This file is not an image.");
        }
    }

    public void downloadFile() {
        System.out.println("Downloading " + type + " from: " + url);
    }

    @Override
    public void show() {
        System.out.println("[Multimedia] " + sender + ": " + content + " (" + type + ")");
    }
}