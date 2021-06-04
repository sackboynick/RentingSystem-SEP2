package model;

import databasemodel.modelinterfaces.*;
import databasemodel.models.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A class  used to manage a set of objects, which are the resources of the server.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ModelManager implements Model {
    private final OnlineUserList onlineUserList;
    private final OfferList offerList;
    private final UserList userList;
    private final RentingList rentingList;
    private final MessageInterface databaseMessageModel;
    private final OfferModelInterface databaseOfferModel;
    private final RentingModelInterface databaseRentingModel;
    private final UserModelInterface databaseUserModel;
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
        this.offerList.addOffer(new Offer("OfferSample", "DescriptionSample", 1000, 10000, "Location", "Room", 50, 3, 2, new User("Nick", "Nicola", "", "Santolini", "", 54385246, "Landlord")));
    }


    /**
     * The method adds the Offer object to the offer list if no offers with the same title exist.
     *
     * @param offer The offer to be added.
     * @return The Offer if the action is done successfully, null if not.
     */
    @Override
    public Offer addOffer(Offer offer) {
        try {
            for (Offer x : databaseOfferModel.getAllOffers().getOffers()) {
                if (x.getTitle().equals(offer.getTitle())) {
                    return null;
                }
            }
            this.databaseOfferModel.createOffer(offer);//Adding offer to the database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        try {
            databaseUserModel.getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        User user = this.onlineUserList.loginInUser(username, password);
        if (user != null) {
            this.propertyChangeSupport.firePropertyChange("OnlineUsers", null, user);
            return user;
        }
        return null;
    }

    /**
     * Getter for the list of offers.
     *
     * @return OfferList object of the offer list.
     */
    @Override
    public OfferList getOffers() {
        try {
            return databaseOfferModel.getAllOffers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return offerList;

    }

    /**
     * Getter for the list of users.
     *
     * @return UserList object of the user list.
     */
    @Override
    public UserList getUsers() {
        try {
            return databaseUserModel.getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    /**
     * Getter for the list of renting deals.
     *
     * @return RentingList object of the renting deals list.
     */
    @Override
    public RentingList getRentingList() {
        try {
            databaseRentingModel.getAllRentings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public String sendMessage(User sender, String receiver, String body) {
        for (User user : userList.getUsersArraylist()) {
            if (user.getUsername().equals(receiver)) {
                //  user.addMessageOrRequest(new Message(body, sender)); old version
                try {
                    databaseMessageModel.createMessage(user, receiver, body);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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

        try {
            for (Offer off : databaseOfferModel.getAllOffers().getOffers()) {
                if (off.getTitle().equals(offer.getTitle()))
                    off.makeOffer(offerer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
//        RentingList rentings = new RentingList(); old version
//        databaseRentingModel.getAllRentingsByUsername(username);
//        for (Renting renting : this.rentingList.getRentingArrayList()) {
//            if (renting.getLandlord().getUsername().equals(username) || renting.getTenant().getUsername().equals(username))
//               rentings.getRentingArrayList().add(renting); old version
//               }
        try {
            return databaseRentingModel.getAllRentingsByUsername(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rentingList;
    }

    /**
     * The method adds a feedback on the renting deal object passed as argument.
     *
     * @param role     The role of the user publishing feedback
     * @param feedback The text body of the feedback
     * @param renting  The renting object interested.
     */
    @Override
    public void publishFeedback(String role, String feedback, Renting renting) {
//        for (Renting renting1 : rentingList.getRentingArrayList()) { old version
//            if (renting1.toString().equals(renting.toString())) {
//                if (role.equals("Landlord")) {
//                    renting1.setLandlordFeedback(s);
//                } else
//                    renting1.setTenantFeedback(s);
        try {
            databaseRentingModel.publishFeedBack(role, feedback, renting);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.propertyChangeSupport.firePropertyChange("ReloadLists", null, "reload");
    }


    /**
     * The method accept a request from a tenant and creates a Renting object and it passes it to the list.
     *
     * @param usernameOfOfferer String for the name of the user who sent the request.
     * @param offer             Offer object of the offer interested.
     */
    @Override
    public Offer closeDeal(String usernameOfOfferer, Offer offer) {
        try {
            databaseOfferModel.getAllOffers().getOffers().removeIf(offer1 -> offer.toString().equals(offer1.toString()));
            databaseOfferModel.deleteOffer(offer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        User tenant;
        try {
            for (User user : databaseUserModel.getAllUsers().getUsersArraylist()) {
                if (user.getUsername().equals(usernameOfOfferer)) {
                    tenant = user;
                    //                rentingList.getRentingArrayList().add(new Renting(tenant, offer.getLandlord(), offer)); old version
                    databaseRentingModel.createRentingOffer(new Renting(tenant, offer.getLandlord(), offer));
                    this.propertyChangeSupport.firePropertyChange("ReloadLists", null, "reload");
                    return offer;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * The method refuse the request sent by the tenant for the property.
     * @param offer The offer where he request has to be refused.
     */
    @Override
    public void refuseRequest(Offer offer) {
        try {
            databaseOfferModel.getAllOffers();
            for (Offer x : databaseOfferModel.getAllOffers().getOffers()) {
                if (x.getTitle().equals(offer.getTitle())) {
                    x.makeOffer("");
                    this.propertyChangeSupport.firePropertyChange("ReloadLists", null, "reload");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        try {
            for (User x : databaseUserModel.getAllUsers().getUsersArraylist()) {
                if (x.getUsername().equals(user.getUsername()))
                    return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.propertyChangeSupport.firePropertyChange("User", null, user);
        try {
            databaseUserModel.createUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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


    /**
     * The method delegates the model to obtain an OfferList object of the offers .
     *
     * @param minPrice  The minimum price for filter search.
     * @param maxPrice  The maximum price for filter search.
     * @param noOfRooms The number of rooms in a Offer for filter search.
     * @param type      The type of Offer for filter search.
     * @param floor     The floor from Offer for filter search.
     * @param deposit   The deposit from Offer for filter search.
     * @return the ArrayList with filtered offers stored in the database.
     */
    @Override
    public ArrayList<Offer> applyFilters(double minPrice,
                                         double maxPrice, int noOfRooms, String type, int floor, double deposit) {
        try {
            return databaseOfferModel.returnCombinedFilter(minPrice, maxPrice, noOfRooms, type, floor, deposit);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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

