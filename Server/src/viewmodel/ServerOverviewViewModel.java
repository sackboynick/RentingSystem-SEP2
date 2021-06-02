package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class ServerOverviewViewModel implements PropertyChangeListener {
    private final StringProperty numberOfUsers,numberOfOffers,numberOfClosedDeals,numberOfTenants,numberOfLandlords;
    private final Model model;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public ServerOverviewViewModel(Model model){
        this.model=model;
        this.model.addListener("Offers",this);
        this.model.addListener("User",this);
        this.model.addListener("Renting",this);
        this.numberOfUsers=new SimpleStringProperty();
        this.numberOfOffers=new SimpleStringProperty();
        this.numberOfClosedDeals=new SimpleStringProperty();
        this.numberOfTenants=new SimpleStringProperty();
        this.numberOfLandlords=new SimpleStringProperty();
        update();
    }

    /**
     * Getter for the number of users property.
     * @return StringProperty for the number of users.
     */
    public StringProperty getNumberOfUsers(){
        return numberOfUsers;
    }
    /**
     * Getter for the number of offers property.
     * @return StringProperty for the number of offers.
     */
    public StringProperty getNumberOfOffers(){
        return numberOfOffers;
    }
    /**
     * Getter for the number of closed deals property.
     * @return StringProperty for the number of closed deals.
     */
    public StringProperty getNumberOfClosedDeals(){
        return numberOfClosedDeals;
    }
    /**
     * Getter for the number of tenants property.
     * @return StringProperty for the number of tenants.
     */
    public StringProperty getNumberOfTenants(){
        return numberOfTenants;
    }
    /**
     * Getter for the number of landlords property.
     * @return StringProperty for the number of landlords.
     */
    public StringProperty getNumberOfLandlords(){
        return numberOfLandlords;
    }

    /**
     * The method sets the data to display in the view model.
     */
    public void update(){
        this.numberOfUsers.setValue(Integer.toString(this.model.getUsers().getUsersArraylist().size()));
        this.numberOfOffers.setValue(Integer.toString(this.model.getOffers().getOffers().size()));
        this.numberOfClosedDeals.setValue(Integer.toString(this.model.getRentingList().getRentingArrayList().size()));
        this.numberOfTenants.setValue(getTenantsSize());
        this.numberOfLandlords.setValue(getLandlordSize());
    }

    /**
     * The method gets the number of the tenants in the system from the model.
     * @return A String with the number of tenants in the system.
     */
    public String getTenantsSize(){
        int numberTenants=0;
        for(User user:this.model.getUsers().getUsersArraylist()){
            if(user.getRole().equals("Tenant"))
                numberTenants++;
        }
        return Integer.toString(numberTenants);
    }

    /**
     * The method gets the number of the landlords in the system from the model.
     * @return A String with the number of landlords in the system.
     */
    public String getLandlordSize(){
        int numberLandlords=0;
        for(User user:this.model.getUsers().getUsersArraylist()){
            if(user.getRole().equals("Landlord"))
                numberLandlords++;
        }
        return Integer.toString(numberLandlords);
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(this::update);
    }
}
