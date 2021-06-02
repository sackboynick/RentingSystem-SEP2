package model;

import utility.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject {
    String addOffer(Offer offer);

    User login(String username, String password);

    void refuseRequest(Offer offer);

    boolean signUp(User user);

    OnlineUserList getUsersOnline();

    OfferList getOffers();

    void updateOnlineUserList();

    void updateOffersList();

    String sendMessage(User sender, String receiver, String body);

    void sendRequest(String offerer, Offer offer);

    RentingList getRentingList();

    void publishFeedback(String tenant, String s, Renting displayedRenting);

    void acceptRequest(String usernameOfOfferer, Offer offer);

    ArrayList<Offer> applyFilters(double minPrice,
                           double maxPrice, int noOfRooms, String type, int floor, double deposit);
}
