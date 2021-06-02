package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class MainViewViewModel implements PropertyChangeListener {
    private final Model model;
    private ObservableList<User> users;
    private ObservableList<Offer> offers;


    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public MainViewViewModel(Model model){
        this.model=model;
        this.users=getOnlineUsersFromModel();
        this.offers=getOffersFromModel();
        this.model.addListener("OnlineUsers",this);
        this.model.addListener("Offers",this);
        this.model.addListener("ReloadLists",this);

    }

    /**
     * The method returns a list of online users taken from the model.
     * @return An ObservableList of User objects.
     */
    public ObservableList<User> getOnlineUsersFromModel(){
        return FXCollections.observableArrayList(model.getUsersOnline().getUsers());
    }

    /**
     * The method returns a list of offers taken from the model.
     * @return An ObservableList of Offer objects.
     */
    public ObservableList<Offer> getOffersFromModel(){
        return FXCollections.observableArrayList(model.getOffers().getOffers());
    }

    /**
     * The method adds an user to the online users list.
     * @param user The User object to be added.
     */
    public void addUser(User user){
        boolean result=false;
        for(User x:users){
            if (x.getUsername().equals(user.getUsername())) {
                result = true;
                break;
            }
        }
        if(!result)
            this.users.add(0,user);
    }

    /**
     * Getter for the online users list.
     * @return The ObservableList of the online User objects.
     */
    public ObservableList<User> getUsers() {
        return users;
    }

    /**
     * Getter for the offers list.
     * @return the ObservableList of Offer objects.
     */
    public ObservableList<Offer> getOffers() {
        return offers;
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if(evt.getPropertyName().equals("OnlineUsers"))
                addUser((User) evt.getNewValue());
            else if(evt.getPropertyName().equals("Offers"))
                this.offers.add(0,(Offer) evt.getNewValue());
            if(evt.getPropertyName().equals("ReloadLists")){
                this.offers=getOffersFromModel();
                this.users=getOnlineUsersFromModel();
            }
        });
    }
}
