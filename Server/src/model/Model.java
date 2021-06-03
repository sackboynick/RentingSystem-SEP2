package model;

import utility.NamedPropertyChangeSubject;

import java.util.ArrayList;

/**
 * Interface of the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface Model extends NamedPropertyChangeSubject {
    /**
     * The method adds the Offer object to the offer list if no offers with the same title exist.
     *
     * @param offer The offer to be added.
     * @return The Offer if the action is done successfully, null if not.
     */
    Offer addOffer(Offer offer);


    /**
     * The method returns an User object of the user which corresponds to the credentials provided in the argument and it returns null if
     * the credentials are wrong.
     *
     * @param username String of the username for the login.
     * @param password String of the password for the login.
     * @return An User object of the user if the credentials are correct, null if they are not.
     */
    User login(String username,String password);


    /**
     * The method creates a new account and it adds the User object to the list of users, if the user does not have the
     * same username as an already existing user.
     * @param user The User object to be added
     * @return True if offer can be added, false if not.
     */
    boolean signUp(User user);

    /**
     * Getter for the online users list.
     * @return The OnlineUserList object.
     */
    OnlineUserList getUsersOnline();

    /**
     * Getter for the list of offers.
     *
     * @return OfferList object of the offer list.
     */
    OfferList getOffers();

    /**
     * Getter for the list of users.
     *
     * @return UserList object of the user list.
     */
    UserList getUsers();

    /**
     * Getter for the list of renting deals.
     *
     * @return RentingList object of the renting deals list.
     */
    RentingList getRentingList();

    /**
     * The method adds a message to the list of messages of the user whose username is passed as one of the arguments.
     *
     * @param sender   User object of the sender of the message.
     * @param receiver String containing the username of the user who is supposed to receive to message.
     * @param body     String object containing the body of the message sent.
     * @return A String containing the result of the action telling if it ended successfully or if it faced any error.
     */
    String sendMessage(User sender,String receiver, String body);

    /**
     * The method sets an active request on the offer.
     *
     * @param offerer A String with the username of the offerer.
     * @param offer   An Offer object of the interested offer.
     */
    void sendRequest(String offerer, Offer offer);

    /**
     * Getter for the renting list of a specific user.
     *
     * @param username Of the user whose renting deals have to be in the list.
     * @return A RentingList object of the renting deals list of the user.
     */
    RentingList getUserRentingList(String username);

    /**
     * The method adds a feedback on the renting deal object passed as argument.
     *
     * @param role     The role of the user publishing feedback
     * @param feedback The text body of the feedback
     * @param renting  The renting object interested.
     */
    void publishFeedback(String role, String feedback,Renting renting);

    /**
     * The method accept a request from a tenant and creates a Renting object and it passes it to the list.
     *
     * @param usernameOfOfferer String for the name of the user who sent the request.
     * @param offer Offer object of the offer interested.
     * @return offer The Offer object.
     */
    Offer closeDeal(String usernameOfOfferer, Offer offer);

    /**
     * The method refuse the request sent by the tenant for the property.
     * @param offer The offer where he request has to be refused.
     */
    void refuseRequest(Offer offer);

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
    ArrayList<Offer> applyFilters(double minPrice,
                                  double maxPrice, int noOfRooms, String type, int floor, double deposit);
}
