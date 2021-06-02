package model;

import javafx.collections.ObservableList;
import utility.NamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject {
    Offer addOffer(Offer offer);
    User login(String username,String password);
    boolean signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    UserList getUsers();
    RentingList getRentingList();
    String sendMessage(User sender,String receiver, String body);
    void sendRequest(String offerer, Offer offer);
    RentingList getUserRentingList(String username);
    void publishFeedback(String role, String s,Renting renting);
    Offer closeDeal(String usernameOfOfferer, Offer offer);
    void refuseRequest(Offer offer);
    ArrayList<Offer> applyFilters(double minPrice,
                                  double maxPrice, int noOfRooms, String type, int floor, double deposit);
}
