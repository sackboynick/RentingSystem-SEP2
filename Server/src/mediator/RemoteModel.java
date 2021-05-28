package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,Object>{
    void addOffer(Offer offer) throws RemoteException;
    User login(String username,String password) throws RemoteException;
    boolean signUp(User user) throws RemoteException;
    OnlineUserList getUsersOnline() throws RemoteException;
    OfferList getOffers() throws RemoteException;
    void closeDeal(Offer offer,User landlord,User tenant) throws RemoteException;
    String sendMessage(User sender, String receiver,String body) throws RemoteException;
    void sendRequest(String offerer, Offer offer) throws RemoteException;
    RentingList getRentingList(String username) throws RemoteException;
    void publishFeedback(String role, String s,Renting renting) throws RemoteException;
    void acceptRequest(String usernameOfOfferer, Offer offer) throws RemoteException;
}
