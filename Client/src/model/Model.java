package model;

import utility.NamedPropertyChangeSubject;

import java.util.ArrayList;

/**
 * The interface of a class used to manage a set of objects, which are the resources of the client.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface Model extends NamedPropertyChangeSubject {
    /**
     * Method to add an offer to the system delegating the rmiClient object.
     *
     * @param offer The offer that needs to be added.
     * @return A String containing key words on the result of the action.
     */
    String addOffer(Offer offer);

    /**
     * Method to login into the system delegating the rmiClient object and set the user active in the client side.
     *
     * @param username String of the username for the login.
     * @param password String of the password for the login.
     * @return An User object of the user if the credentials are correct, null if they are not.
     */
    User login(String username, String password);

    /**
     * Method to refuse a request of a tenant for a property delegating the rmiClient object.
     *
     * @param offer Offer object of the offer interested.
     */
    void refuseRequest(Offer offer);


    /**
     * Method to sign up and register an account into the system delegating the rmiClient object.
     *
     * @param user User object of the user to be registered in the system
     * @return False, if an user with the same username already exists, true if not.
     */
    boolean signUp(User user);

    /**
     * Getter for the OnlineUserList object stored in the client side.
     *
     * @return An OnlineUserList object containing the details of the users online.
     */
    OnlineUserList getUsersOnline();

    /**
     * Getter for the OfferList object stored in the client side.
     *
     * @return An OfferList object containing the details of the offers available.
     */
    OfferList getOffers();

    /**
     * Method to empty the list of the users online to initialise to the one obtained from the server.
     */
    void updateOnlineUserList();

    /**
     * Method to empty the list of the offers online to initialise to the one obtained from the server.
     */
    void updateOffersList();


    /**
     * Method which delegates the rmiClient object to send a message to an another user.
     *
     * @param sender   The User object of the sender.
     * @param receiver The String of the username of the receiver of the message.
     * @param body     The String containing the body of the message.
     * @return A String containing the result of the action.
     */
    String sendMessage(User sender, String receiver, String body);

    /**
     * Method which delegates the rmiClient to send a request to a landlord for a property.
     *
     * @param offerer A String with the username of the offerer.
     * @param offer   An Offer object of the interested offer.
     */
    void sendRequest(String offerer, Offer offer);

    /**
     * Method which delegates the rmiClient to obtain the RentingList object from the server side.
     *
     * @return A RentingList object containing Renting objects.
     */
    RentingList getRentingList();

    /**
     * Method to publish a feedback on a deal delegating the rmiClient object.
     *
     * @param role             The role of the user publishing feedback
     * @param feedback         The text body of the feedback
     * @param displayedRenting The renting object interested.
     */
    void publishFeedback(String role, String feedback, Renting displayedRenting);

    /**
     * Method to accept a request of a tenant for a property delegating the rmiClient.
     *
     * @param usernameOfOfferer String for the name of the user who sent the request.
     * @param offer             Offer object of the offer interested.
     */
    void acceptRequest(String usernameOfOfferer, Offer offer);



    /**
     * Method invoked remotely from the server side to obtain the list of the Offer objects for filter search
     *
     * @param minPrice The minimum price for filter search.
     * @param maxPrice The maximum price for filter search.
     * @param noOfRooms The number of rooms in a Offer for filter search.
     * @param type The type of Offer for filter search.
     * @param floor The floor from Offer for filter search.
     * @param deposit The deposit from Offer for filter search.
     * @return the ArrayList with filtered offers stored in the database.
     */
    ArrayList<Offer> applyFilters(double minPrice,
                           double maxPrice, int noOfRooms, String type, int floor, double deposit);
}
