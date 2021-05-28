package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757691L;
    private final String body;
    private LocalDateTime localDateTime;
    private User sender;
    private String senderIntText;

    public Message(String body, User sender){
        this.sender=sender;
        this.body=body;
    }

    public Message(String body){
        this.body=body;
        this.senderIntText="SERVER";
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getBody() {
        return body;
    }

    public User getSender() {
        return sender;
    }
}
