package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A class representing a message.
 *
 *
 * @author Group 8-SEP2
 * @version 1.0.0 2021
 */

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757691L;

    /**
     * Body of the message.
     */
    private final String body;
    /**
     * Sender of the message.
     */
    private final User sender;
    /**
     * Two-argument constructor. The body and the sender do not
     * need to respect any specific rule.
     * @param body the body of the message
     * @param sender the sender of the message
     */
    public Message(String body,User sender){
        this.sender=sender;
        this.body=body;
    }

    /**
     * Getter method to get the body of the message.
     * @return the body of the message.
     */
    public String getBody() {
        return body;
    }

    /**
     * Getter method to get the sender User object of the message.
     * @return a User object of the sender.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Method toString overridden to be appropriate for the system.
     * @return the message in a string form that specifies its details
     */
    @Override
    public String toString(){
        return "From "+sender.getUsername()+": "+body;
    }
}
