package mediator;

import model.*;
import utility.NamedPropertyChangeSubject;
import utility.observer.event.ObserverEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The client program obtains a stub for the registry on the server's host and looks up the remote object's stub by name in the registry.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class RmiClient implements RentingSystem, utility.observer.listener.RemoteListener<String, Object>, NamedPropertyChangeSubject {
    private final PropertyChangeSupport propertyChangeSupport;
    private RemoteModel server;

    /**
     * Zero-argument constructor. It obtains and sets the stub from the registry.
     */
    public RmiClient() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        try {
            this.server = (RemoteModel) Naming.lookup("rmi://localhost:1099/HousingSystem");
            UnicastRemoteObject.exportObject(this, 0);
            server.addListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method invoked remotely from the server side to add an offer to the shared offer list.
     *
     * @param offer Offer to be added to the server.
     * @return It returns a String that confirms if the offer has been added or not. It returns null if an exception is thrown
     * and it is not a RemoteException.
     */
    @Override
    public String addOffer(Offer offer) {
        try {
            return server.addOffer(offer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method invoked remotely from the server side to log into the system.
     *
     * @param username Username for the login.
     * @param password Password for the login.
     * @return It returns the object of the requested user if the credentials are correct, otherwise it returns null.
     */
    @Override
    public User login(String username, String password) {
        try {
            return server.login(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method invoked remotely from the server side to register an account into the system.
     *
     * @param user The user object sent to the server to be stored and registered.
     * @return False, if an user with the same username already exists, true if not.
     */
    @Override
    public boolean signUp(User user) {
        try {
            return server.signUp(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method invoked remotely from the server side to get the list of the onlineUsers.
     *
     * @return An OnlineUserList object containing the users online registered by the server.
     */
    @Override
    public OnlineUserList getUsersOnline() {
        try {
            return server.getUsersOnline();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method invoked remotely from the server side to get the list of the offers available.
     *
     * @return An OfferList object containing the offers registered in the server.
     */
    @Override
    public OfferList getOffers() {
        try {
            return server.getOffers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method invoked remotely from the server side to send a message to an another user registered in the system.
     *
     * @param sender   User object of the sender of the message.
     * @param receiver String containing the username of the user who is supposed to receive to message.
     * @param body     String object containing the body of the message sent.
     * @return A String containing the result of the action telling if it ended successfully or if it faced any error.
     */
    @Override
    public String sendMessage(User sender, String receiver, String body) {
        try {
            return server.sendMessage(sender,receiver, body);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "Problem during the connection";
    }

    /**
     * Method invoked remotely from the server side to sendARequest toa landlord for a property.
     *
     * @param offerer A String object of the username of the user who is making the offer for the landlord.
     * @param offer   The Offer object of the offer interested.
     */
    @Override
    public void sendRequest(String offerer, Offer offer) {
        try{
            this.server.sendRequest(offerer, offer);
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * Method invoked remotely from the server side to obtain the list of the Renting objects related to user who has the same username
     * has the one passed as argument.
     *
     * @param username The username of the user who is requiring its list of renting deals.
     * @return the RentingList object stored in the server.
     */
    @Override
    public RentingList getRentingList(String username) {
        try {
            return this.server.getRentingList(username);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method invoked remotely from the server to publish a feedback related to a closed renting deal.
     *
     * @param role     The role of the user who is publishing the feedback.
     * @param feedback The text of the feedback.
     * @param renting  The Renting object where the feedback will be published.
     */
    @Override
    public void publishFeedback(String role, String feedback, Renting renting) {
        try {
            this.server.publishFeedback(role, feedback, renting);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method invoked remotely from the server side to accept a request from a tenant for a property.
     *
     * @param usernameOfOfferer The username of the user who sent the request
     * @param offer             The Offer object for which the request was sent.
     */
    @Override
    public void acceptRequest(String usernameOfOfferer, Offer offer) {
        try {
            this.server.acceptRequest(usernameOfOfferer, offer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method invoked remotely from the server side to refuse a request from a tenant for a property.
     *
     * @param offer The Offer object of the offer where the request was sent
     */
    @Override
    public void refuseRequest(Offer offer) {
        try {
            this.server.refuseRequest(offer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
     * Method called whenever a change in the properties of the RemoteModel server happens, because the RmiClient is its listener.
     *
     * @param event ObserverEvent object which contains details about the event.
     * @throws RemoteException If there are errors in the communication.
     */
    @Override
    public void propertyChange(ObserverEvent<String, Object> event) throws RemoteException {
        switch (event.getPropertyName()) {
            case "OnlineUsers" -> this.propertyChangeSupport.firePropertyChange("OnlineUsers", null, event.getValue2());
            case "Offers" -> this.propertyChangeSupport.firePropertyChange("Offers", null, event.getValue2());
            case "Message" -> this.propertyChangeSupport.firePropertyChange("Message",event.getValue1(),event.getValue2());
            case "Renting" -> this.propertyChangeSupport.firePropertyChange("Renting",event.getValue1(),event.getValue2());
            case "Reload" -> this.propertyChangeSupport.firePropertyChange("Reload",null,event.getValue2());
        }
    }
}
