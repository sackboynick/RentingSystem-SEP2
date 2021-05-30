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
    private final StringProperty receiver, body,error;
    private final Model model;

    public SendMessageViewModel(Model model){
        this.model=model;
        this.model.updateOnlineUserList();
        this.onlineUsers= FXCollections.observableArrayList(model.getUsersOnline().getUsers());
        this.model.addListener("OnlineUsers",this);
        this.receiver=new SimpleStringProperty("");
        this.body=new SimpleStringProperty("");
        this.error=new SimpleStringProperty("");
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

    public StringProperty getError() {
        return error;
    }

    public void sendMessage(){
        String result="";
        if(body.get().equals(""))
            error.set("Please write something in the body");
        else if(receiver.get().equals(""))
            error.set("Please insert an username");
        else {
            result = this.model.sendMessage(ViewState.getInstance().getUser(), getReceiver().get(), getBody().get());
            if(result.equals("Valid"))
                error.set("");
            else
                error.set(result);
        }

    }

    public void addUser(User user){
        boolean result=false;
        for(User x:onlineUsers){
            if (x.getUsername().equals(user.getUsername())) {
                result = true;
                break;
            }
        }
        if(!result)
            this.onlineUsers.add(0,user);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> addUser((User) evt.getNewValue()));
    }
}
