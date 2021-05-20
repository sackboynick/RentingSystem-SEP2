package mediator;

import model.*;
import utility.NamedPropertyChangeSubject;
import utility.observer.event.ObserverEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient implements RentingSystem, utility.observer.listener.RemoteListener<String, Object>, NamedPropertyChangeSubject {
    private final PropertyChangeSupport propertyChangeSupport;
    private RemoteModel server;

    public RmiClient(){
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        try {
            this.server = (RemoteModel) Naming.lookup("rmi://localhost:1099/HousingSystem");
            UnicastRemoteObject.exportObject(this, 0);
            server.addListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String addOffer(Offer offer) {
        try {
            return server.addOffer(offer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "Error adding the offer.";
    }

    @Override
    public User login(String username, String password) {
        try {
            return server.login(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void signUp(User user) {
        try {
            server.signUp(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OnlineUserList getUsersOnline() {
        try {
            return server.getUsersOnline();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OfferList getOffers() {
        try {
            return server.getOfferList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void closeDeal(Offer offer,User landlord,User tenant) {
        try {
            server.closeDeal(offer, landlord, tenant);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void propertyChange(ObserverEvent<String, Object> event) throws RemoteException {
        switch (event.getPropertyName()) {
            case "OnlineUsers" -> this.propertyChangeSupport.firePropertyChange("OnlineUsers", null, event.getValue2());
            case "Offers" -> this.propertyChangeSupport.firePropertyChange("Offers", null, event.getValue2());
        }
    }
}
