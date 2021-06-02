package mediator;

import model.*;
import utility.NamedPropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * An RMI server makes remote objects accessible to clients by registering them in the registry; clients then obtain access via that same registry.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class RmiServer implements RemoteModel, PropertyChangeListener{
    private final PropertyChangeHandler<String,Object> propertyChangeHandler;
    private final Model model;

    /**
     * Zero-argument constructor. It registers the stub on the registry.
     */
    public RmiServer(Model model){
        this.propertyChangeHandler =new PropertyChangeHandler<>(this,true);
        this.model=model;
        this.model.addListener("ReloadLists",this);
        try {
            startRegistry();
            UnicastRemoteObject.exportObject(this, 0);
            Naming.rebind("HousingSystem", this);
        }catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
    }

    /**
     * This method starts a registry on the port 1099
     * @throws RemoteException If there are any connection issues
     */
    public static void startRegistry() throws RemoteException{
        try {
            Registry registry= LocateRegistry.createRegistry(1099);
        }catch (java.rmi.server.ExportException e){
            System.out.println("Registry already started? "+e.getMessage());
        }
    }

    /**
     * The method delegates to the model to add the Offer to the list, then checks the result of the action and returns a string
     * that contains it.
     * @param offer Offer to be added to the server.
     * @return It returns a String that confirms if the offer has been added or not.
     */

    @Override
    public String addOffer(Offer offer) throws RemoteException {
        if(this.model.addOffer(offer)!=null) {
            this.propertyChangeHandler.firePropertyChange("Offers", null, offer);
            System.out.println(offer);
            return "Valid";
        }
        else
            return "An offer with the same name already exists";

    }

    /**
     * The method delegates the model to get the user corresponding to the credentials given in the arguments if they are correct.
     * @param username Username for the login.
     * @param password Password for the login.
     * @return It returns the object of the requested user if the credentials are correct, if not the object can be null.
     */
    @Override
    public User login(String username,String password) throws RemoteException {
        User user=model.login(username, password);
        if(user!=null)
            this.propertyChangeHandler.firePropertyChange("OnlineUsers",null,user);
        return user;
    }

    /**
     * The method delegates the model to add and register the account provided in the argument.
     * @param user The user object sent to the server to be stored and registered.
     * @return False, if an user with the same username already exists, true if not.
     */
    @Override
    public boolean signUp(User user) throws RemoteException {
        return model.signUp(user);
    }

    /**
     * The method delegates the model to get the OnlineUserList object stored in it.
     * @return An OnlineUserList object containing the users online registered by the server.
     */
    @Override
    public OnlineUserList getUsersOnline() throws RemoteException {
        return model.getUsersOnline();
    }

    /**
     * The method delegates the model to get the OfferList object stored in it.
     * @return An OfferList object containing the offers available registered in the server.
     * @throws RemoteException If there are any connection issues.
     */
    @Override
    public OfferList getOffers() throws RemoteException {
        return model.getOffers();
    }


    /**
     * The method delegates the model to send a message to the specified user.
     * @param sender User object of the sender of the message.
     * @param receiver String containing the username of the user who is supposed to receive to message.
     * @param body String object containing the body of the message sent.
     * @return A String containing the result of the action telling if it ended successfully or if it faced any errors.
     */
    @Override
    public String sendMessage(User sender, String receiver, String body) throws RemoteException {
        String result=this.model.sendMessage(sender,receiver,body);
        if(result.equals("Valid"))
            this.propertyChangeHandler.firePropertyChange("Message",receiver,new Message(body,sender));
        return result;
    }

    /**
     * The method delegates the model to send a request to the landlord for a property.
     * @param offerer A String object of the username of the user who is making the offer for the landlord.
     * @param offer The Offer object of the offer interested.
     */
    @Override
    public void sendRequest(String offerer, Offer offer) throws RemoteException {
        this.model.sendRequest(offerer,offer);
        this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }

    /**
     * The method delegates the model to get the RentingList object with the renting deals objects related to the user
     * passed in the argument.
     * @param username The username of the user who is requiring its list of renting deals.
     * @return the RentingList object stored in the server.
     */
    @Override
    public RentingList getRentingList(String username) throws RemoteException {
        return this.model.getUserRentingList(username);
    }


    /**
     * The method delegates the model to publish a feedback in the Renting object.
     *
     * @param role     The role of the user who is publishing the feedback.
     * @param feedback The text of the feedback.
     * @param renting  The Renting object where the feedback will be published.
     */
    @Override
    public void publishFeedback(String role, String feedback,Renting renting) throws RemoteException {
        this.model.publishFeedback(role,feedback,renting);
        this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }

    /**
     * The method delegates the model to accept a request from a tenant for a property.
     *
     * @param usernameOfOfferer The username of the user who sent the request
     * @param offer             The Offer object for which the request was sent.
     */
    @Override
    public void acceptRequest(String usernameOfOfferer, Offer offer) {
        if(this.model.closeDeal(usernameOfOfferer,offer)!=null)
            this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }

    /**
     * The method delegates the model to refuse a request from a tenant for a property.
     *
     * @param offer The Offer object of the offer where the request was sent
     */
    @Override
    public void refuseRequest(Offer offer) throws RemoteException {
        this.model.refuseRequest(offer);
        this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }



    /**
     * Method to add a listener to the PropertyChangeHandler object.
     *
     * @param propertyNames String containing the name of the property the listener will listen to.
     * @param listener     GeneralListener object which represents the listener to the property.
     */
    @Override
    public boolean addListener(GeneralListener<String, Object> listener, String... propertyNames) throws RemoteException {
        return this.propertyChangeHandler.addListener(listener,propertyNames);
    }

    /**
     * Method to remove a listener to the PropertyChangeHandler object.
     *
     * @param propertyNames String containing the name of the property the listener will stop to listen to.
     * @param listener     GeneralListener object which represents the listener to the property.
     */
    @Override
    public boolean removeListener(GeneralListener<String, Object> listener, String... propertyNames) throws RemoteException {
        return this.propertyChangeHandler.removeListener(listener, propertyNames);
    }

    /**
     * Method called whenever a change in the properties of the model happens, because the RmiServer is its listener.
     *
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "ReloadLists" -> this.propertyChangeHandler.firePropertyChange("Reload",null,evt.getNewValue());
        }
    }
}
