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

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class SendMessageViewModel implements PropertyChangeListener {
    private final ObservableList<User> onlineUsers;
    private final StringProperty receiver, body,error;
    private final Model model;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public SendMessageViewModel(Model model){
        this.model=model;
        this.model.updateOnlineUserList();
        this.onlineUsers= FXCollections.observableArrayList(model.getUsersOnline().getUsers());
        this.model.addListener("OnlineUsers",this);
        this.receiver=new SimpleStringProperty("");
        this.body=new SimpleStringProperty("");
        this.error=new SimpleStringProperty("");
    }

    /**
     * Getter for the online users list.
     * @return The OnlineUser observable list.
     */
    public ObservableList<User> getOnlineUsers() {
        return onlineUsers;
    }

    /**
     * Getter for the body property.
     * @return The StringProperty for the body of the message.
     */
    public StringProperty getBody() {
        return body;
    }

    /**
     * Getter for the body property.
     * @return The StringProperty for the receiver of the message.
     */
    public StringProperty getReceiver() {
        return receiver;
    }

    /**
     * Getter for the error property.
     * @return The StringProperty for errors in the system.
     */
    public StringProperty getError() {
        return error;
    }

    /**
     * The method delegates the model send a message to the landlord. If the body of the message property results empty,
     * an error would be set in the Error property.
     */
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

    /**
     * The method is used to add an User object in the User list, an user with the same username as an already existing user
     * will not be accepted.
     * @param user The User object to be added to the list.
     */
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

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> addUser((User) evt.getNewValue()));
    }
}
