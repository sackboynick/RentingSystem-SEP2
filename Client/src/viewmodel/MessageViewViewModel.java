package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import javax.swing.text.View;

public class MessageViewViewModel {
    private final Model model;
    private final StringProperty sender,body,date,replyBox;

    public MessageViewViewModel(Model model){
        this.model=model;
        this.sender=new SimpleStringProperty();
        this.body=new SimpleStringProperty();
        this.date=new SimpleStringProperty();
        this.replyBox=new SimpleStringProperty();
    }

    public StringProperty getBody() {
        return body;
    }

    public StringProperty getReplyBox() {
        return replyBox;
    }

    public StringProperty getSender() {
        return sender;
    }

    public StringProperty getDate() {
        return date;
    }

    public void updateData(){
        this.sender.set(ViewState.getInstance().getDisplayedMessage().getSender().getUsername());
        this.body.set(ViewState.getInstance().getDisplayedMessage().getBody());
        this.replyBox.set("");
    }

    public void sendMessage(){
        this.model.sendMessage(ViewState.getInstance().getUser(), ViewState.getInstance().getDisplayedMessage().getSender().getUsername(), replyBox.get());
    }
}
