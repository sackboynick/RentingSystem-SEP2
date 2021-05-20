package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,Object>{
    String addOffer(Offer offer) throws RemoteException;
    User login(String username,String password) throws RemoteException;
    void signUp(User user) throws RemoteException;
    OnlineUserList getUsersOnline() throws RemoteException;
    OfferList getOfferList() throws RemoteException;
    void closeDeal(Offer offer,User landlord,User tenant) throws RemoteException;
}
