package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Offer;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewViewModel implements PropertyChangeListener {
    private final Model model;
    private final ObservableList<User> users;
    private final ObservableList<Offer> offers;


    public MainViewViewModel(Model model){
        this.model=model;
        this.users=getOnlineUsersFromModel();
        this.offers=getOffersFromModel();
        this.model.addListener("OnlineUsers",this);
        this.model.addListener("Offers",this);

    }

    public ObservableList<User> getOnlineUsersFromModel(){
        return FXCollections.observableArrayList(model.getUsersOnline().getUsers());
    }
    public ObservableList<Offer> getOffersFromModel(){
        return FXCollections.observableArrayList(model.getOffers().getOffers());
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public ObservableList<Offer> getOffers() {
        return offers;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if(evt.getPropertyName().equals("OnlineUsers"))
                this.users.add(0,(User) evt.getNewValue());
            else if(evt.getPropertyName().equals("Offers"))
                this.offers.add(0,(Offer) evt.getNewValue());
        });
    }
}
