package model;

import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model, PropertyChangeListener {
    private final RmiClient rmiClient;
    private final OnlineUserList onlineUserList;
    private final OfferList offerList;
    private final PropertyChangeSupport propertyChangeSupport;


    public ModelManager(RmiClient rmiClient){
        this.rmiClient=rmiClient;
        this.rmiClient.addListener("OnlineUser",this);
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        this.onlineUserList=new OnlineUserList();
        this.offerList=rmiClient.getOffers();
    }

    @Override
    public OnlineUserList getUsersOnline() {
        return onlineUserList;
    }

    @Override
    public OfferList getOffers() {
        return offerList;
    }

    @Override
    public String addOffer(Offer offer) {
        return rmiClient.addOffer(offer);
    }

    @Override
    public User login(String username, String password) {
        User client=rmiClient.login(username, password);
        this.onlineUserList.getUsers().clear();
        this.onlineUserList.getUsers().addAll(this.rmiClient.getUsersOnline().getUsers());
        return client;
    }

    @Override
    public void signUp(User user) {
        rmiClient.signUp(user);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case "OnlineUsers":
                this.propertyChangeSupport.firePropertyChange("OnlineUser",null,evt.getNewValue());
                break;
            case "Offers":
                this.propertyChangeSupport.firePropertyChange("Offers",null,evt.getNewValue());
                break;
        }

    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
}
