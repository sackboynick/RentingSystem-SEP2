package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ServerOverviewViewModel implements PropertyChangeListener {
    private final StringProperty numberOfUsers,numberOfOffers,numberOfClosedDeals,numberOfTenants,numberOfLandlords;
    private final Model model;

    public ServerOverviewViewModel(Model model){
        this.model=model;
        this.model.addListener("Offers",this);
        this.model.addListener("User",this);
        this.numberOfUsers=new SimpleStringProperty();
        this.numberOfOffers=new SimpleStringProperty();
        this.numberOfClosedDeals=new SimpleStringProperty();
        this.numberOfTenants=new SimpleStringProperty();
        this.numberOfLandlords=new SimpleStringProperty();
        update();
    }

    public StringProperty getNumberOfUsers(){
        return numberOfUsers;
    }
    public StringProperty getNumberOfOffers(){
        return numberOfOffers;
    }
    public StringProperty getNumberOfClosedDeals(){
        return numberOfClosedDeals;
    }
    public StringProperty getNumberOfTenants(){
        return numberOfTenants;
    }
    public StringProperty getNumberOfLandlords(){
        return numberOfLandlords;
    }

    public void update(){
        this.numberOfUsers.setValue(Integer.toString(this.model.getUsers().getUsersArraylist().size()));
        this.numberOfOffers.setValue(Integer.toString(this.model.getOffers().getOffers().size()));
        this.numberOfTenants.setValue(getTenantsSize());
        this.numberOfLandlords.setValue(getLandlordSize());
    }

    public String getTenantsSize(){
        int numberTenants=0;
        for(User user:this.model.getUsers().getUsersArraylist()){
            if(user.getRole().equals("Tenant"))
                numberTenants++;
        }
        return Integer.toString(numberTenants);
    }

    public String getLandlordSize(){
        int numberLandlords=0;
        for(User user:this.model.getUsers().getUsersArraylist()){
            if(user.getRole().equals("Landlord"))
                numberLandlords++;
        }
        return Integer.toString(numberLandlords);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(this::update);
    }
}
