package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ViewState(){
    }


    public static ViewState getInstance(){
        if(instance==null)
            instance=new ViewState();
        return instance;
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

    public User getUser() {
        return user;
    }

    public Offer getOffer() {
        return offer;
    }

    public User getDisplayedUser() {
        return displayedUser;
    }

}
