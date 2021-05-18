package model;

import mediator.RmiClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model {
    private OnlineUserList onlineUserList;
    private OfferList offerList;
    private final PropertyChangeSupport propertyChangeSupport;


    public ModelManager(){
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        this.onlineUserList=new OnlineUserList();
        this.offerList=new OfferList();
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
        return onlineUserList.(username, password);
    }

    @Override public void updateOnlineUserList(OnlineUserList onlineUserList){
        this.onlineUserList=onlineUserList;
    }

    @Override public void updateOffersList(OfferList offerList){
        this.offerList=offerList;
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
}
