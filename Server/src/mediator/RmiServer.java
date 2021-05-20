package mediator;

import model.*;
import utility.NamedPropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer implements RemoteModel, NamedPropertyChangeSubject{
    private final PropertyChangeHandler<String,Object> propertyChangeHandler;
    private final PropertyChangeSupport propertyChangeSupport;
    private int usersOnline;
    private final Model model;

    public RmiServer(Model model){
        this.propertyChangeHandler =new PropertyChangeHandler<>(this,true);
        this.propertyChangeSupport=new PropertyChangeSupport(this);
        this.usersOnline=0;
        this.model=model;
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
        this.propertyChangeHandler.firePropertyChange("Offers",null,offer);
        return this.model.addOffer(offer);
    }

    @Override
    public User login(String username,String password) throws RemoteException {
        User user=model.login(username, password);
        this.propertyChangeHandler.firePropertyChange("OnlineUsers",null,user);
        return user;
    }

    @Override
    public void signUp(User user) throws RemoteException {
        model.signUp(user);
    }

    @Override
    public OnlineUserList getUsersOnline() throws RemoteException {
        return model.getUsersOnline();
    }

    @Override
    public OfferList getOfferList() throws RemoteException {
        return model.getOffers();
    }

    @Override
    public void closeDeal(Offer offer,User landlord,User tenant) throws RemoteException {
        this.model.getDealsList().addDeal(new Deal(offer,landlord,tenant));
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
    public boolean addListener(GeneralListener<String, Object> listener, String... propertyNames) throws RemoteException {
        return this.propertyChangeHandler.addListener(listener,propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Object> listener, String... propertyNames) throws RemoteException {
        return this.propertyChangeHandler.removeListener(listener, propertyNames);
    }
}
