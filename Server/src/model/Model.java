package model;

import javafx.collections.ObservableList;
import utility.NamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject {
    String addOffer(Offer offer);
    User login(String username,String password);
    boolean signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    UserList getUsers();
    RentingList getRentingList();
    String sendMessage(User sender,String receiver, String body);
    void sendRequest(String offerer, Offer offer);
}
