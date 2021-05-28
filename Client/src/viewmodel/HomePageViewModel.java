package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;
import model.Offer;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageViewModel implements PropertyChangeListener {
    private final Model model;
    private final ObservableList<User> onlineUsers;
    private final ObservableList<Offer> offers;
    private ObservableList<Message> messages;

    public HomePageViewModel(Model model){
        this.model=model;
        this.model.addListener("OnlineUsers",this);
        this.model.addListener("Offers",this);
        updateLists();
        this.onlineUsers=getOnlineUsersFromModel();
        this.offers=getOffersFromModel();
    }

    public void updateLists(){
        model.updateOffersList();
        model.updateOnlineUserList();
    }

    public void updateMessageList(){
        this.messages=FXCollections.observableArrayList(ViewState.getInstance().getUser().getMessagesAndRequests());
    }

    public ObservableList<User> getOnlineUsersFromModel(){
        return FXCollections.observableArrayList(model.getUsersOnline().getUsers());
    }

    public ObservableList<Offer> getOffersFromModel(){
        return FXCollections.observableArrayList(model.getOffers().getOffers());
    }

    public ObservableList<User> getOnlineUsers() {
        return this.onlineUsers;
    }

    public ObservableList<Offer> getOffers() {
        return offers;
    }

    public ObservableList<Message> getMessages() {
        return messages;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "OnlineUsers" -> Platform.runLater(() -> this.onlineUsers.add(0, (User) evt.getNewValue()));
            case "Offers" -> Platform.runLater(() -> this.offers.add(0, (Offer) evt.getNewValue()));
            case "Message" -> Platform.runLater(() -> this.messages.add(0, (Message) evt.getNewValue()));
        }

    }
}
