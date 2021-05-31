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
    public Offer addOffer(Offer offer) {
        for(Offer x: offerList.getOffers()){
                if (x.getTitle().equals(offer.getTitle())) {
                    return null;
                }
        }
        this.offerList.getOffers().add(0, offer);
        this.propertyChangeSupport.firePropertyChange("Offers", null, offer);
        return offer;
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
            if(user.getUsername().equals(receiver)) {
                user.addMessageOrRequest(new Message(body, sender));
                return "Valid";
            }
        }
        return "The username you inserted does not exist, try again.";
    }

    @Override
    public void sendRequest(String offerer, Offer offer) {
        for(Offer off: offerList.getOffers()){
            if(off.getTitle().equals(offer.getTitle()))
                off.makeOffer(offerer);
        }
    }

    @Override
    public RentingList getUserRentingList(String username) {
        RentingList rentings=new RentingList();
        for(Renting renting: this.rentingList.getRentingArrayList()){
            if(renting.getLandlord().getUsername().equals(username) || renting.getTenant().getUsername().equals(username))
                rentings.getRentingArrayList().add(renting);
        }
        return rentings;
    }

    @Override
    public void publishFeedback(String role, String s,Renting renting) {
        for(Renting renting1:rentingList.getRentingArrayList()){
            if(renting1.toString().equals(renting.toString())) {
                if(role.equals("Landlord")) {
                    renting1.setLandlordFeedback(s);
                }
                else
                    renting1.setTenantFeedback(s);

                this.propertyChangeSupport.firePropertyChange("ReloadLists",null,"reload");
            }
        }
    }

    @Override
    public Offer closeDeal(String usernameOfOfferer, Offer offer) {
        offerList.getOffers().removeIf(offer1 -> offer.toString().equals(offer1.toString()));
        User tenant;
        for(User user:userList.getUsersArraylist()){
            if(user.getUsername().equals(usernameOfOfferer)) {
                tenant = user;
                rentingList.getRentingArrayList().add(new Renting(tenant, offer.getLandlord(), offer));
                this.propertyChangeSupport.firePropertyChange("ReloadLists",null,"reload");
                return offer;
            }
        }
        return null;
    }

    @Override
    public void refuseRequest(Offer offer) {
        for(Offer x: offerList.getOffers()){
            if(x.getTitle().equals(offer.getTitle())) {
                x.makeOffer("");
                this.propertyChangeSupport.firePropertyChange("ReloadLists",null,"reload");
            }
        }
    }

    @Override
    public boolean signUp(User user) {
        for(User x: userList.getUsersArraylist())
        {
            if(x.getUsername().equals(user.getUsername()))
                return false;
        }
        this.propertyChangeSupport.firePropertyChange("User",null,user);
        userList.addUser(user);
        return true;
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

