package mediator;

import javafx.collections.ObservableList;
import model.Offer;
import model.OfferList;
import model.OnlineUserList;
import model.User;

import java.rmi.RemoteException;

public interface RentingSystem {
    String addOffer(Offer offer);
    User login(String username,String password);
    boolean signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    void closeDeal(Offer offer,User landlord,User tenant);
    String sendMessage(User sender, String receiver,String body);
    void sendRequest(String offerer, Offer offer);
}
