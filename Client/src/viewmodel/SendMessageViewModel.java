package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SendMessageViewModel implements PropertyChangeListener {
    private final ObservableList<User> onlineUsers;
    private final StringProperty receiver, body;
    private final Model model;

    public SendMessageViewModel(Model model){
        this.model=model;
        this.onlineUsers= FXCollections.observableArrayList(model.getUsersOnline().getUsers());
        model.addListener("OnlineUsers",this);
        this.receiver=new SimpleStringProperty();
        this.body=new SimpleStringProperty();
    }

    public ObservableList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public StringProperty getBody() {
        return body;
    }

    public StringProperty getReceiver() {
        return receiver;
    }

    public String sendMessage(){
        if(body.get().equals(""))
            return "Please write something in the body";
        else if(receiver.get().equals(""))
            return "Please insert an username";
        else
            return this.model.sendMessage(getReceiver().get(),getBody().get());

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> this.onlineUsers.add((User) evt.getNewValue()));
    }
}
