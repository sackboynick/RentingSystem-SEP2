package mediator;

import model.*;
import utility.NamedPropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer implements RemoteModel, PropertyChangeListener{
    private final PropertyChangeHandler<String,Object> propertyChangeHandler;
    private final Model model;

    public RmiServer(Model model){
        this.propertyChangeHandler =new PropertyChangeHandler<>(this,true);
        this.model=model;
        this.model.addListener("ReloadLists",this);
        try {
            startRegistry();
            UnicastRemoteObject.exportObject(this, 0);
            Naming.rebind("HousingSystem", this);
        }catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
    }

    public static void startRegistry() throws RemoteException{
        try {
            Registry registry= LocateRegistry.createRegistry(1099);
        }catch (java.rmi.server.ExportException e){
            System.out.println("Registry already started? "+e.getMessage());
        }
    }


    @Override
    public String addOffer(Offer offer) throws RemoteException {
        if(this.model.addOffer(offer)!=null) {
            this.propertyChangeHandler.firePropertyChange("Offers", null, offer);
            System.out.println(offer);
            return "Valid";
        }
        else
            return "An offer with the same name already exists";

    }

    @Override
    public User login(String username,String password) throws RemoteException {
        User user=model.login(username, password);
        if(user!=null)
            this.propertyChangeHandler.firePropertyChange("OnlineUsers",null,user);
        return user;
    }

    @Override
    public boolean signUp(User user) throws RemoteException {
        return model.signUp(user);
    }

    @Override
    public OnlineUserList getUsersOnline() throws RemoteException {
        return model.getUsersOnline();
    }

    @Override
    public OfferList getOffers() throws RemoteException {
        return model.getOffers();
    }


    @Override
    public String sendMessage(User sender, String receiver, String body) throws RemoteException {
        String result=this.model.sendMessage(sender,receiver,body);
        if(result.equals("Valid"))
            this.propertyChangeHandler.firePropertyChange("Message",receiver,new Message(body,sender));
        return result;
    }

    @Override
    public void sendRequest(String offerer, Offer offer) throws RemoteException {
        this.model.sendRequest(offerer,offer);
        this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }

    @Override
    public RentingList getRentingList(String username) throws RemoteException {
        return this.model.getUserRentingList(username);
    }

    @Override
    public void publishFeedback(String role, String s,Renting renting) throws RemoteException {
        this.model.publishFeedback(role,s,renting);
        this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }

    @Override
    public void acceptRequest(String usernameOfOfferer, Offer offer) {
        if(this.model.closeDeal(usernameOfOfferer,offer)!=null)
            this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }

    @Override
    public void refuseRequest(Offer offer) throws RemoteException {
        this.model.refuseRequest(offer);
        this.propertyChangeHandler.firePropertyChange("Reload",null,"Reload");
    }




    @Override
    public boolean addListener(GeneralListener<String, Object> listener, String... propertyNames) throws RemoteException {
        return this.propertyChangeHandler.addListener(listener,propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Object> listener, String... propertyNames) throws RemoteException {
        return this.propertyChangeHandler.removeListener(listener, propertyNames);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "ReloadLists" -> this.propertyChangeHandler.firePropertyChange("Reload",null,evt.getNewValue());
        }
    }
}
