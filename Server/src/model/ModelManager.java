package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ModelManager implements Model{
    private final OnlineUserList onlineUserList;
    private final OfferList offerList;
    private final UserList userList;
    private final RentingList rentingList;
    private final PropertyChangeSupport propertyChangeSupport;

    /**
     * Zero-argument constructor.
     */
    public ModelManager() {
        this.onlineUserList = new OnlineUserList();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.offerList = new OfferList();
        this.userList = new UserList();
        this.rentingList = new RentingList();
        this.databaseMessageModel = new MessageModel();
        this.databaseOfferModel = new OfferModel();
        this.databaseRentingModel = new RentingModel();
        this.databaseUserModel = new UserModel();
        this.offerList.addOffer(new Offer("title", "description", 345, 1000, "Horsens", "room", 453, 3, 4, new User("nick", "fds", "", "", "", 345246, "")));
    }


    /**
     * The method adds the Offer object to the offer list if no offers with the same title exist.
     *
     * @param offer The offer to be added.
     * @return The Offer if the action is done successfully, null if not.
     */
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

    /**
     * The method returns an User object of the user which corresponds to the credentials provided in the argument and it returns null if
     * the credentials are wrong.
     *
     * @param username String of the username for the login.
     * @param password String of the password for the login.
     * @return An User object of the user if the credentials are correct, null if they are not.
     */
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

    /**
     * Getter for the list of users.
     *
     * @return UserList object of the user list.
     */
    @Override
    public UserList getUsers() {
        return userList;
    }

    /**
     * Getter for the list of renting deals.
     *
     * @return RentingList object of the renting deals list.
     */
    @Override
    public RentingList getRentingList() {
        return rentingList;
    }

    /**
     * The method adds a message to the list of messages of the user whose username is passed as one of the arguments.
     *
     * @param sender   User object of the sender of the message.
     * @param receiver String containing the username of the user who is supposed to receive to message.
     * @param body     String object containing the body of the message sent.
     * @return A String containing the result of the action telling if it ended successfully or if it faced any error.
     */
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

    /**
     * The method sets an active request on the offer.
     *
     * @param offerer A String with the username of the offerer.
     * @param offer   An Offer object of the interested offer.
     */
    @Override
    public void sendRequest(String offerer, Offer offer) {
        for(Offer off: offerList.getOffers()){
            if(off.getTitle().equals(offer.getTitle()))
                off.makeOffer(offerer);
        }
    }

    /**
     * Getter for the renting list of a specific user.
     *
     * @param username Of the user whose renting deals have to be in the list.
     * @return A RentingList object of the renting deals list of the user.
     */
    @Override
    public RentingList getUserRentingList(String username) {
        RentingList rentings=new RentingList();
        for(Renting renting: this.rentingList.getRentingArrayList()){
            if(renting.getLandlord().getUsername().equals(username) || renting.getTenant().getUsername().equals(username))
                rentings.getRentingArrayList().add(renting);
        }
        return rentings;
    }

    /**
     * The method adds a feedback on the renting deal object passed as argument.
     *
     * @param role     The role of the user publishing feedback
     * @param feedback The text body of the feedback
     * @param renting  The renting object interested.
     */
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

    /**
     * The method refuse the request sent by the tenant for the property.
     * @param offer The offer where he request has to be refused.
     */
    @Override
    public void refuseRequest(Offer offer) {
        for(Offer x: offerList.getOffers()){
            if(x.getTitle().equals(offer.getTitle())) {
                x.makeOffer("");
                this.propertyChangeSupport.firePropertyChange("ReloadLists",null,"reload");
            }
        }
    }

    /**
     * The method creates a new account and it adds the User object to the list of users, if the user does not have the
     * same username as an already existing user.
     * @param user The User object to be added
     * @return True if offer can be added, false if not.
     */
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

    /**
     * Getter for the online users list.
     * @return The OnlineUserList object.
     */
    @Override
    public OnlineUserList getUsersOnline() {
        return this.onlineUserList;
    }


    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Method to refuse a listener to the PropertyChangeSupport object.
     *
     * @param propertyName String containing the name of the property the listener will stop to listen to.
     * @param listener     PropertyChangeListener object which represents the listener to the property.
     */
    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
}

