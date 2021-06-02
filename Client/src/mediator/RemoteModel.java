package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface that represents the methods that can be invoked from the stub registered on the registry by the server.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface RemoteModel extends RemoteSubject<String,Object>{
    /**
     * Method invoked remotely from the server side to add an offer to the shared offer list.
     *
     * @param offer Offer to be added to the server.
     * @return It returns a String that confirms if the offer has been added or not. It returns null if an exception is thrown
     * and it is not a RemoteException.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    String addOffer(Offer offer) throws RemoteException;
    /**
     * Method invoked remotely from the server side to log into the system.
     *
     * @param username Username for the login.
     * @param password Password for the login.
     * @return It returns the object of the requested user if the credentials are correct, otherwise it returns null.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    User login(String username,String password) throws RemoteException;
    /**
     * Method invoked remotely from the server side to register an account into the system.
     *
     * @param user The user object sent to the server to be stored and registered.
     * @return False, if an user with the same username already exists, true if not.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    boolean signUp(User user) throws RemoteException;
    /**
     * Method invoked remotely from the server side to get the list of the onlineUsers.
     *
     * @return An OnlineUserList object containing the users online registered by the server.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    OnlineUserList getUsersOnline() throws RemoteException;

    /**
     * Method invoked remotely from the server side to get the list of the offers available.
     *
     * @return An OfferList object containing the offers registered in the server.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    OfferList getOffers() throws RemoteException;

    /**
     * Method invoked remotely from the server side to send a message to an another user registered in the system.
     *
     * @param sender   User object of the sender of the message.
     * @param receiver String containing the username of the user who is supposed to receive to message.
     * @param body     String object containing the body of the message sent.
     * @return A String containing the result of the action telling if it ended successfully or if it faced any error.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    String sendMessage(User sender, String receiver,String body) throws RemoteException;

    /**
     * Method invoked remotely from the server side to send a request to a landlord for a property.
     *
     * @param offerer A String object of the username of the user who is making the offer for the landlord.
     * @param offer   The Offer object of the offer interested.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    void sendRequest(String offerer, Offer offer) throws RemoteException;

    /**
     * Method invoked remotely from the server side to obtain the list of the Renting objects related to user who has the same username
     * has the one passed as argument.
     *
     * @param username The username of the user who is requiring its list of renting deals.
     * @return the RentingList object stored in the server.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    RentingList getRentingList(String username) throws RemoteException;

    /**
     * Method invoked remotely from the server to publish a feedback related to a closed renting deal.
     *
     * @param role     The role of the user who is publishing the feedback.
     * @param feedback The text of the feedback.
     * @param renting  The Renting object where the feedback will be published.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    void publishFeedback(String role, String feedback,Renting renting) throws RemoteException;

    /**
     * Method invoked remotely from the server side to accept a request from a tenant for a property.
     *
     * @param usernameOfOfferer The username of the user who sent the request
     * @param offer             The Offer object for which the request was sent.
     * @throws RemoteException if there are issues during the connection to the server.
     */
    void acceptRequest(String usernameOfOfferer, Offer offer) throws RemoteException;

    /**
     * Method invoked remotely from the server side to refuse a request from a tenant for a property.
     *
     * @param offer The Offer object of the offer where the request was sent
     * @throws RemoteException if there are issues during the connection to the server.
     */
    void refuseRequest(Offer offer) throws RemoteException;

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
     * @throws RemoteException if there are issues during the connection to the server.
     */
    ArrayList<Offer> applyFilters(double minPrice,
                                  double maxPrice, int noOfRooms, String type, int floor, double deposit) throws RemoteException;
}
