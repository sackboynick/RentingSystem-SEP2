package model;

import javafx.collections.ObservableList;
import utility.NamedPropertyChangeSubject;

import java.rmi.RemoteException;

public interface Model extends NamedPropertyChangeSubject {
    String addOffer(Offer offer);
    User login(String username,String password);
    boolean signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    void updateOnlineUserList();
    void updateOffersList();
    String sendMessage(User sender, String receiver,String body);
    void sendRequest(String offerer,Offer offer);
}
