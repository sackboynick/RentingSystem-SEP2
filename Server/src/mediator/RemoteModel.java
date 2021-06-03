package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface of the RmiServer.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface RemoteModel extends RemoteSubject<String,Object>{
    /**
     * The method delegates to the model to add the Offer to the list, then checks the result of the action and returns a string
     * that contains it.
     * @param offer Offer to be added to the server.
     * @return It returns a String that confirms if the offer has been added or not.
     * @throws RemoteException if there are issues during the connection.
     */
    String addOffer(Offer offer) throws RemoteException;

    /**
     * The method delegates the model to get the user corresponding to the credentials given in the arguments if they are correct.
     * @param username Username for the login.
     * @param password Password for the login.
     * @return It returns the object of the requested user if the credentials are correct, if not the object can be null.
     * @throws RemoteException if there are issues during the connection.
     */
    User login(String username,String password) throws RemoteException;

    /**
     * The method delegates the model to add and register the account provided in the argument.
     * @param user The user object sent to the server to be stored and registered.
     * @return False, if an user with the same username already exists, true if not.
     * @throws RemoteException if there are issues during the connection.
     */
    boolean signUp(User user) throws RemoteException;

    /**
     * The method delegates the model to get the OnlineUserList object stored in it.
     * @return An OnlineUserList object containing the users online registered by the server.
     * @throws RemoteException if there are issues during the connection.
     */
    OnlineUserList getUsersOnline() throws RemoteException;

    /**
     * The method delegates the model to get the OfferList object stored in it.
     * @return An OfferList object containing the offers available registered in the server.
     * @throws RemoteException If there are any connection issues.
     * @throws RemoteException if there are issues during the connection.
     */
    OfferList getOffers() throws RemoteException;

    /**
     * The method delegates the model to send a message to the specified user.
     * @param sender User object of the sender of the message.
     * @param receiver String containing the username of the user who is supposed to receive to message.
     * @param body String object containing the body of the message sent.
     * @return A String containing the result of the action telling if it ended successfully or if it faced any errors.
     * @throws RemoteException if there are issues during the connection.
     */
    String sendMessage(User sender, String receiver,String body) throws RemoteException;

    /**
     * The method delegates the model to send a request to the landlord for a property.
     * @param offerer A String object of the username of the user who is making the offer for the landlord.
     * @param offer The Offer object of the offer interested.
     * @throws RemoteException if there are issues during the connection.
     */
    void sendRequest(String offerer, Offer offer) throws RemoteException;
    /**
     * The method delegates the model to get the RentingList object with the renting deals objects related to the user
     * passed in the argument.
     * @param username The username of the user who is requiring its list of renting deals.
     * @return the RentingList object stored in the server.
     * @throws RemoteException if there are issues during the connection.
     */
    RentingList getRentingList(String username) throws RemoteException;

    /**
     * The method delegates the model to publish a feedback in the Renting object.
     *
     * @param role     The role of the user who is publishing the feedback.
     * @param feedback The text of the feedback.
     * @param renting  The Renting object where the feedback will be published.
     * @throws RemoteException if there are issues during the connection.
     */
    void publishFeedback(String role, String feedback,Renting renting) throws RemoteException;

    /**
     * The method delegates the model to accept a request from a tenant for a property.
     *
     * @param usernameOfOfferer The username of the user who sent the request
     * @param offer             The Offer object for which the request was sent.
     * @throws RemoteException if there are issues during the connection.
     */
    void acceptRequest(String usernameOfOfferer, Offer offer) throws RemoteException;

    /**
     * The method delegates the model to refuse a request from a tenant for a property.
     *
     * @param offer The Offer object of the offer where the request was sent
     *
     * @throws RemoteException if there are issues during the connection.
     */
    void refuseRequest(Offer offer) throws RemoteException;

    /**
     * The method delegates the model to obtain an OfferList object of the offers which respect the conditions.
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
