package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ModelManager implements Model{
    private final OnlineUserList onlineUserList;
    private final OfferList offerList;
    private final UserList userList;
    private final RentingList rentingList;
    private final PropertyChangeSupport propertyChangeSupport;

    public ModelManager(){
        this.onlineUserList=new OnlineUserList();
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        this.offerList=new OfferList();
        this.userList=new UserList();
        this.rentingList=new RentingList();
        this.offerList.addOffer(new Offer("title","description",345,1000,"Horsens","room",453, 3,4,new User("dfdss","fds","","","",345246,"")));
    }

    @Override
    public String addOffer(Offer offer) {
        this.propertyChangeSupport.firePropertyChange("Offers",null,offer);
        return offerList.addOffer(offer);
    }

    @Override
    public User login(String username, String password) {
        User user=this.onlineUserList.loginInUser(username,password,userList);
        if(user!=null) {
            this.propertyChangeSupport.firePropertyChange("OnlineUsers", null, user);
            return user;
        }
        return null;
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
    public RentingList getRentingList() {
        return rentingList;
    }

    @Override
    public String sendMessage(User sender,String receiver, String body) {
        for(User user: userList.getUsersArraylist()){
            if(user.getUsername().equals(receiver))
                user.addMessageOrRequest(new Message(body, sender));
        }
        return null;
    }

    @Override
    public void sendRequest(String offerer, Offer offer) {
        for(Offer off: offerList.getOffers()){
            if(off.getTitle().equals(offer.getTitle()))
                off.makeOffer(offerer);
        }
    }

    @Override
    public boolean signUp(User user) {
        if(!userList.getUsersArraylist().contains(user)) {
            this.propertyChangeSupport.firePropertyChange("User",null,user);
            userList.addUser(user);
            return true;
        }
        return false;
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

