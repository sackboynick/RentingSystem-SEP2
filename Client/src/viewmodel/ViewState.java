package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Offer;
import model.Renting;
import model.User;
import utility.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewState {
    private static ViewState instance;
    private User user;
    private Offer offer;
    private User displayedUser;
    private Renting displayedRenting;
    private Message displayedMessage;

    private ViewState(){}

    public static ViewState getInstance(){
        if(instance==null)
            instance=new ViewState();
        return instance;
    }

    public void setDisplayedRenting(Renting displayedRenting) {
        this.displayedRenting = displayedRenting;
    }

    public Renting getDisplayedRenting() {
        return displayedRenting;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setDisplayedUser(User displayedUser) {
        this.displayedUser = displayedUser;
    }

    public void setDisplayedMessage(Message displayedMessage) {
        this.displayedMessage = displayedMessage;
    }

    public User getUser() {
        return user;
    }

    public Offer getOffer() {
        return offer;
    }

    public User getDisplayedUser() {
        return displayedUser;
    }

    public Message getDisplayedMessage() {
        return displayedMessage;
    }

}
