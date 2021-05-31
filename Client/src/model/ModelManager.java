package model;

import mediator.RmiClient;
import viewmodel.ViewState;

import javax.swing.text.View;
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
        this.rmiClient=rmiClient;
        this.onlineUserList=new OnlineUserList();
        this.offerList=new OfferList();
        this.rmiClient.addListener("OnlineUsers",this);
        this.rmiClient.addListener("Offers",this);
        this.rmiClient.addListener("Message",this);
        this.rmiClient.addListener("Renting",this);
        this.rmiClient.addListener("Reload",this);
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
        return user;
    }

    @Override public void updateOnlineUserList(){
        this.onlineUserList.getUsers().clear();
        this.onlineUserList.getUsers().addAll(rmiClient.getUsersOnline().getUsers());
    }

    @Override public void updateOffersList(){
        this.offerList.getOffers().clear();
        this.offerList.getOffers().addAll(rmiClient.getOffers().getOffers());
    }

    @Override
    public String sendMessage(User sender,String receiver, String body) {
        return rmiClient.sendMessage(sender,receiver,body);
    }

    @Override
    public void sendRequest(String offerer, Offer offer) {
        this.rmiClient.sendRequest(offerer,offer);
    }

    @Override
    public RentingList getRentingList(String username) {
        return this.rmiClient.getRentingList(username);
    }

    @Override
    public void publishFeedback(String role, String s, Renting displayedRenting) {
        this.rmiClient.publishFeedback(role,s,displayedRenting);
    }

    @Override
    public void acceptRequest(String usernameOfOfferer, Offer offer) {
        this.rmiClient.acceptRequest(usernameOfOfferer,offer);
    }

    @Override public void refuseRequest(Offer offer){
        this.rmiClient.refuseRequest(offer);
    }

    @Override
    public boolean signUp(User user) {
        return rmiClient.signUp(user);
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
            case "Message" -> {
                if(evt.getOldValue().toString().equals(ViewState.getInstance().getUser().getUsername())) {
                    Message message=(Message)  evt.getNewValue();
                    ViewState.getInstance().getUser().addMessageOrRequest(message);
                    System.out.println(message.toString());
                    this.propertyChangeSupport.firePropertyChange("Message",null,evt.getNewValue());
                }
            }
            case "Renting" -> {
                if(evt.getOldValue().toString().equals(ViewState.getInstance().getUser().getUsername())){
                    Renting renting=(Renting) evt.getNewValue();
                    if(renting!=null)
                       this.propertyChangeSupport.firePropertyChange("Renting",null,evt.getNewValue());
                }
            }
            case "Reload"->{
                this.onlineUserList=this.rmiClient.getUsersOnline();
                this.offerList=this.rmiClient.getOffers();
                this.propertyChangeSupport.firePropertyChange("Reload",null,evt.getNewValue());
            }
        }
    }
}
