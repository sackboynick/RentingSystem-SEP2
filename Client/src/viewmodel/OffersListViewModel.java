package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Offer;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class OffersListViewModel implements PropertyChangeListener {
    private ObservableList<Offer> offers;
    private final Model model;
    private final StringProperty minPrice,maxPrice,minRooms,type,floor,maxDeposit;

    public OffersListViewModel(Model model){
        this.model=model;
        this.model.addListener("Offers",this);
        this.offers=FXCollections.observableArrayList();
        this.minPrice=new SimpleStringProperty();
        this.maxPrice=new SimpleStringProperty();
        this.minRooms=new SimpleStringProperty();
        this.type=new SimpleStringProperty();
        this.floor=new SimpleStringProperty();
        this.maxDeposit=new SimpleStringProperty();
    }

    public void updateOffersListFromModel(){
        this.offers=FXCollections.observableArrayList(model.getOffers().getOffers());
    }


    public ObservableList<Offer> getOffers() {
        return offers;
    }


    public void applyFilters(){
        if(!minPrice.get().equals(""))
            setMinPrice(Double.parseDouble(minPrice.get()));
        if(!maxPrice.get().equals("")){

        }
    }

    public void setMinPrice(double minPrice){
        ObservableList<Offer> filteredCopY=FXCollections.observableArrayList();
        for(Offer offer: offers){
            if(offer.getPricePerMonth()>=minPrice)
                filteredCopY.add(offer);
        }
        this.offers=filteredCopY;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            this.offers.add(0, (Offer) evt.getNewValue());
        });
    }
}
