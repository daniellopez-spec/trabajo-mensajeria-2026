import java.time.LocalDateTime;

public abstract class Message {

    protected String id;
    protected User sender;
    protected String content;
    protected LocalDateTime date;
    protected MessageStatus status;

    public Message(String id, User sender, String content) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.date = LocalDateTime.now();
        this.status = MessageStatus.SENT;
    }

    public void markAsRead() {
        status = MessageStatus.READ;
    }

    public abstract void show();
}