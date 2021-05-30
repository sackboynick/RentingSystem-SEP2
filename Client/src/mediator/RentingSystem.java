package mediator;

import model.*;

public interface RentingSystem {
    String addOffer(Offer offer);
    User login(String username,String password);
    boolean signUp(User user);
    OnlineUserList getUsersOnline();
    OfferList getOffers();
    String sendMessage(User sender, String receiver,String body);
    void sendRequest(String offerer, Offer offer);
    RentingList getRentingList(String username);
    void publishFeedback(String role, String s,Renting renting);
    void acceptRequest(String usernameOfOfferer, Offer offer);
    void refuseRequest(Offer offer);
}
