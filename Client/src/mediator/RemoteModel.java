package mediator;

import model.Offer;
import model.OfferList;
import model.OnlineUserList;
import model.User;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,Object>{
    String addOffer(Offer offer) throws RemoteException;
    User login(String username,String password) throws RemoteException;
    boolean signUp(User user) throws RemoteException;
    OnlineUserList getUsersOnline() throws RemoteException;
    OfferList getOffers() throws RemoteException;
    void closeDeal(Offer offer,User landlord,User tenant) throws RemoteException;
    String sendMessage(User sender, String receiver,String body) throws RemoteException;
    void sendRequest(String offerer, Offer offer) throws RemoteException;
}
