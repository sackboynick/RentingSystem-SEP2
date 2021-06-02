package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import javax.swing.text.View;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class MessageViewViewModel {
    private final Model model;
    private final StringProperty sender,body,replyBox;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public MessageViewViewModel(Model model){
        this.model=model;
        this.sender=new SimpleStringProperty();
        this.body=new SimpleStringProperty();
        this.replyBox=new SimpleStringProperty();
    }

    /**
     * Getter for the body property.
     * @return The StringProperty for the body of the message.
     */
    public StringProperty getBody() {
        return body;
    }

    /**
     * Getter for the replyBox property.
     * @return The StringProperty for the replyBox where to reply to the message.
     */
    public StringProperty getReplyBox() {
        return replyBox;
    }

    /**
     * Getter for the sender property.
     * @return The StringProperty for the sender of the message.
     */
    public StringProperty getSender() {
        return sender;
    }

    /**
     * This method updates the value of the properties to set a new message in the viewModel.
     */

    public void updateData(){
        this.sender.set(ViewState.getInstance().getDisplayedMessage().getSender().getUsername());
        this.body.set(ViewState.getInstance().getDisplayedMessage().getBody());
        this.replyBox.set("");
    }

    /**
     * This method delegates the model to send a message using the properties of the ViewModel.
     */
    public void sendMessage(){
        this.model.sendMessage(ViewState.getInstance().getUser(), ViewState.getInstance().getDisplayedMessage().getSender().getUsername(), replyBox.get());
    }
}
