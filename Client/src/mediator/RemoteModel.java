package mediator;

import model.Offer;
import model.OfferList;
import model.OnlineUserList;
import model.User;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,User>{
    String addOffer(Offer offer) throws RemoteException;
    User login(String username,String password) throws RemoteException;
    void signUp(User user) throws RemoteException;
    OnlineUserList getUsersOnline() throws RemoteException;
    OfferList getOfferList() throws RemoteException;
}
