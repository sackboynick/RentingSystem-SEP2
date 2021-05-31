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
        return "Remote Exception";
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
    public boolean signUp(User user) {
        try {
            return server.signUp(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
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
            return server.getOffers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String sendMessage(User sender, String receiver, String body) {
        try {
            return server.sendMessage(sender,receiver, body);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "Problem during the connection";
    }

    @Override
    public void sendRequest(String offerer, Offer offer) {
        try{
            this.server.sendRequest(offerer, offer);
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public RentingList getRentingList(String username) {
        try {
            return this.server.getRentingList(username);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void publishFeedback(String role, String s,Renting renting) {
        try {
            this.server.publishFeedback(role,s,renting);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptRequest(String usernameOfOfferer, Offer offer) {
        try {
            this.server.acceptRequest(usernameOfOfferer, offer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refuseRequest(Offer offer) {
        try {
            this.server.refuseRequest(offer);
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
            case "Message" -> this.propertyChangeSupport.firePropertyChange("Message",event.getValue1(),event.getValue2());
            case "Renting" -> this.propertyChangeSupport.firePropertyChange("Renting",event.getValue1(),event.getValue2());
            case "Reload" -> this.propertyChangeSupport.firePropertyChange("Reload",null,event.getValue2());
        }
    }
}
