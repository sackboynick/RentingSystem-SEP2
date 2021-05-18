package model;

import javafx.collections.ObservableList;
import utility.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject {
    String addOffer(Offer offer);
    User login(String username,String password);
    void signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    void updateOnlineUserList(OnlineUserList usersOnline);
    void updateOffersList(OfferList offerList);
}
