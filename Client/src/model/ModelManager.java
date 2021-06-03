package model;

import mediator.RmiClient;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * A class  used to manage a set of objects, which are the resources of the client.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ModelManager implements Model, PropertyChangeListener {
    private final OnlineUserList onlineUserList;
    private OfferList offerList;
    private final PropertyChangeSupport propertyChangeSupport;
    private final RmiClient rmiClient;


    /**
     * One-argument constructor.
     *
     * @param rmiClient RmiClient object called when there is the need to communicate with the server.
     */
    public ModelManager(RmiClient rmiClient) {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.rmiClient = rmiClient;
        this.onlineUserList = new OnlineUserList();
        this.offerList = new OfferList();
        this.rmiClient.addListener("OnlineUsers", this);
        this.rmiClient.addListener("Offers", this);
        this.rmiClient.addListener("Message", this);
        this.rmiClient.addListener("Renting", this);
        this.rmiClient.addListener("Reload", this);
    }

    /**
     * Getter for the OnlineUserList object stored in the client side.
     *
     * @return An OnlineUserList object containing the details of the users online.
     */
    @Override
    public OnlineUserList getUsersOnline() {
        return onlineUserList;
    }

    /**
     * Getter for the OfferList object stored in the client side.
     *
     * @return An OfferList object containing the details of the offers available.
     */
    @Override
    public OfferList getOffers() {
        return offerList;
    }

    /**
     * Method to add an offer to the system delegating the rmiClient object.
     *
     * @param offer The offer that needs to be added.
     * @return A String containing key words on the result of the action.
     */
    @Override
    public String addOffer(Offer offer) {
        return rmiClient.addOffer(offer);
    }

    /**
     * Method to login into the system delegating the rmiClient object and set the user active in the client side.
     *
     * @param username String of the username for the login.
     * @param password String of the password for the login.
     * @return An User object of the user if the credentials are correct, null if they are not.
     */
    @Override
    public User login(String username, String password) {
        User user = rmiClient.login(username, password);
        ViewState.getInstance().setUser(user);
        return user;
    }

    /**
     * Method to empty the list of the users online to initialise to the one obtained from the server.
     */
    @Override
    public void updateOnlineUserList() {
        this.onlineUserList.getUsers().clear();
        this.onlineUserList.getUsers().addAll(rmiClient.getUsersOnline().getUsers());
    }

    /**
     * Method to empty the list of the offers online to initialise to the one obtained from the server.
     */
    @Override
    public void updateOffersList() {
        this.offerList.getOffers().clear();
        this.offerList.getOffers().addAll(rmiClient.getOffers().getOffers());
    }

    /**
     * Method which delegates the rmiClient object to send a message to an another user.
     *
     * @param sender   The User object of the sender.
     * @param receiver The String of the username of the receiver of the message.
     * @param body     The String containing the body of the message.
     * @return A String containing the result of the action.
     */
    @Override
    public String sendMessage(User sender, String receiver, String body) {
        return rmiClient.sendMessage(sender, receiver, body);
    }

    /**
     * Method which delegates the rmiClient to send a request to a landlord for a property.
     *
     * @param offerer A String with the username of the offerer.
     * @param offer   An Offer object of the interested offer.
     */
    @Override
    public void sendRequest(String offerer, Offer offer) {
        this.rmiClient.sendRequest(offerer, offer);
    }

    /**
     * Method which delegates the rmiClient to obtain the RentingList object from the server side.
     *
     * @return A RentingList object containing Renting objects.
     */
    @Override
    public RentingList getRentingList() {
        return this.rmiClient.getRentingList(ViewState.getInstance().getUser().getUsername());
    }

    /**
     * Method to publish a feedback on a deal delegating the rmiClient object.
     *
     * @param role             The role of the user publishing feedback
     * @param feedback         The text body of the feedback
     * @param displayedRenting The renting object interested.
     */
    @Override
    public void publishFeedback(String role, String feedback, Renting displayedRenting) {
        this.rmiClient.publishFeedback(role, feedback, displayedRenting);
    }

    /**
     * Method to accept a request of a tenant for a property delegating the rmiClient.
     *
     * @param usernameOfOfferer String for the name of the user who sent the request.
     * @param offer             Offer object of the offer interested.
     */
    @Override
    public void acceptRequest(String usernameOfOfferer, Offer offer) {
        this.rmiClient.acceptRequest(usernameOfOfferer, offer);
    }

    /**
     * Method to refuse a request of a tenant for a property delegating the rmiClient object.
     *
     * @param offer Offer object of the offer interested.
     */
    @Override
    public void refuseRequest(Offer offer) {
        this.rmiClient.refuseRequest(offer);
    }

    /**
     * Method to sign up and register an account into the system delegating the rmiClient object.
     *
     * @param user User object of the user to be registered in the system
     * @return False, if an user with the same username already exists, true if not.
     */
    @Override
    public boolean signUp(User user) {
        return rmiClient.signUp(user);
    }


    /**
     * Method invoked remotely from the server side to obtain the list of the Offer objects for filter search
     *
     * @param minPrice The minimum price for filter search.
     * @param maxPrice The maximum price for filter search.
     * @param noOfRooms The number of rooms in a Offer for filter search.
     * @param type The type of Offer for filter search.
     * @param floor The floor from Offer for filter search.
     * @param deposit The deposit from Offer for filter search.
     * @return the ArrayList with filtered offers stored in the database.
     */
    @Override
    public ArrayList<Offer> applyFilters(double minPrice,
                                         double maxPrice, int noOfRooms, String type, int floor, double deposit) {
        return rmiClient.applyFilters(minPrice, maxPrice, noOfRooms, type, floor, deposit);

    }

    /**
     * Method to add a listener to the PropertyChangeSupport object.
     *
     * @param propertyName String containing the name of the property the listener will listen to.
     * @param listener     PropertyChangeListener object which represents the listener to the property.
     */
    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Method to remove a listener to the PropertyChangeSupport object.
     *
     * @param propertyName String containing the name of the property the listener will stop to listen to.
     * @param listener     PropertyChangeListener object which represents the listener to the property.
     */
    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }



    /**
     * Method called whenever a change in the properties of the rmiClient happens, because the Model is its listener.
     *
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "OnlineUsers" -> {
                this.onlineUserList.getUsers().add((User) evt.getNewValue());
                this.propertyChangeSupport.firePropertyChange("OnlineUsers", null, evt.getNewValue());
            }
            case "Offers" -> {
                this.offerList.addOffer((Offer) evt.getNewValue());
                this.propertyChangeSupport.firePropertyChange("Offers", null, evt.getNewValue());
            }
            case "Message" -> {
                if (evt.getOldValue().toString().equals(ViewState.getInstance().getUser().getUsername())) {
                    Message message = (Message) evt.getNewValue();
                    ViewState.getInstance().getUser().addMessageOrRequest(message);
                    System.out.println(message.toString());
                    this.propertyChangeSupport.firePropertyChange("Message", null, evt.getNewValue());
                }
            }
            case "Renting" -> {
                if (evt.getOldValue().toString().equals(ViewState.getInstance().getUser().getUsername())) {
                    Renting renting = (Renting) evt.getNewValue();
                    if (renting != null)
                        this.propertyChangeSupport.firePropertyChange("Renting", null, evt.getNewValue());
                }
            }
            case "Reload" -> {
                this.offerList = this.rmiClient.getOffers();
                this.propertyChangeSupport.firePropertyChange("Reload", null, evt.getNewValue());
            }
        }
    }
}
