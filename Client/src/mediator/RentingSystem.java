package mediator;

import javafx.collections.ObservableList;
import model.Offer;
import model.OfferList;
import model.OnlineUserList;
import model.User;

public interface RentingSystem {
    String addOffer(Offer offer);
    User login(String username,String password);
    void signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    void closeDeal(Offer offer,User landlord,User tenant);
}
