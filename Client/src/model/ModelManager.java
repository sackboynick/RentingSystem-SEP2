package model;

import mediator.RmiClient;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model,PropertyChangeListener {
    private OnlineUserList onlineUserList;
    private OfferList offerList;
    private final PropertyChangeSupport propertyChangeSupport;
    private final RmiClient rmiClient;


    public ModelManager(RmiClient rmiClient){
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        this.onlineUserList=new OnlineUserList();
        this.offerList=new OfferList();
        this.rmiClient=rmiClient;
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
        User user=rmiClient.login(username, password);
        ViewState.getInstance().setUser(user);
        updateOnlineUserList();
        updateOffersList();
        return user;
    }

    @Override public void updateOnlineUserList(){
        this.onlineUserList=rmiClient.getUsersOnline();
    }

    @Override public void updateOffersList(){
        this.offerList=rmiClient.getOffers();
    }

    @Override
    public void signUp(User user) {
        rmiClient.signUp(user);
    }


    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

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
        }
    }
}
