package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ModelManager implements Model{
    private final OnlineUserList onlineUserList;
    private final OfferList offerList;
    private final UserList userList;
    private final PropertyChangeSupport propertyChangeSupport;

    public ModelManager(){
        this.onlineUserList=new OnlineUserList(this);
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        this.offerList=new OfferList();
        this.userList=new UserList();
        this.offerList.addOffer(new Offer("title","description",345,345,"Horsens","room",453, 3,4,new User("dfdss","fds","","","",345246,"")));
    }

    @Override
    public String addOffer(Offer offer) {
        this.propertyChangeSupport.firePropertyChange("Offers",null,offer);
        return offerList.addOffer(offer);
    }

    @Override
    public User login(String username, String password) {
        User user=this.onlineUserList.loginInUser(username,password);
        this.propertyChangeSupport.firePropertyChange("OnlineUsers",null,user);
        return user;
    }
    @Override
    public OfferList getOffers(){
        return offerList;
    }

    @Override
    public UserList getUsers() {
        return userList;
    }

    @Override
    public String signUp(User user) {
        this.propertyChangeSupport.firePropertyChange("User",null,user);
        return userList.addUser(user);
    }

    @Override
    public OnlineUserList getUsersOnline() {
        return this.onlineUserList;
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

