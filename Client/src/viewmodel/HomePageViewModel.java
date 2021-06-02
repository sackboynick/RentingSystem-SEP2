
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

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class HomePageViewModel implements PropertyChangeListener {
    private final Model model;
    private ObservableList<User> onlineUsers;
    private ObservableList<Offer> offers;
    private ObservableList<Message> messages;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public HomePageViewModel(Model model){
        this.model=model;
        this.model.addListener("OnlineUsers",this);
        this.model.addListener("Offers",this);
        this.model.addListener("Message",this);
        this.model.addListener("Reload",this);
        this.onlineUsers=FXCollections.observableArrayList();
        this.offers=FXCollections.observableArrayList();
        this.messages=FXCollections.observableArrayList();
    }

    /**
     * Method that updates the lists in the model (getting them from the server) and that passes these lists in the viewModel.
     */
    public void updateLists(){
        model.updateOffersList();
        model.updateOnlineUserList();
        this.onlineUsers=getOnlineUsersFromModel();
        this.offers=getOffersFromModel();
        updateMessageList();
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
     * This method updates the message list and gets a copy from the user profile.
     */
    public void updateMessageList(){
        this.messages=FXCollections.observableArrayList(ViewState.getInstance().getUser().getMessagesAndRequests());
    }

    /**
     * This method returns the list of the online users from the model.
     * @return An ObservableList of User objects.
     */
    public ObservableList<User> getOnlineUsersFromModel(){
        return FXCollections.observableArrayList(model.getUsersOnline().getUsers());
    }

    /**
     * This method returns the list of the offers from the model.
     * @return An ObservableList of the Offer objects.
     */
    public ObservableList<Offer> getOffersFromModel(){
        return FXCollections.observableArrayList(model.getOffers().getOffers());
    }

    /**
     * Getter for the online users list.
     * @return The OnlineUser observable list.
     */
    public ObservableList<User> getOnlineUsers() {
        return this.onlineUsers;
    }

    /**
     * Getter for the offers list.
     * @return The Offer observable list.
     */
    public ObservableList<Offer> getOffers() {
        return offers;
    }

    /**
     * Getter for the messages list.
     * @return The Message observable list.
     */
    public ObservableList<Message> getMessages() {
        return messages;
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "OnlineUsers" -> Platform.runLater(() -> addUser( (User) evt.getNewValue()));
            case "Offers" -> Platform.runLater(() -> this.offers.add(0, (Offer) evt.getNewValue()));
            case "Message" -> Platform.runLater(() -> this.messages.add(0, (Message) evt.getNewValue()));
            case "Reload" -> Platform.runLater(this::updateLists);
        }

    }
}
