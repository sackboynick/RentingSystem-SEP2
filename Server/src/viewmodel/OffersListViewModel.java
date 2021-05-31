package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Offer;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OffersListViewModel implements PropertyChangeListener {

    private final Model model;
    private ObservableList<Offer> offers;
    private final StringProperty title,landlordName,type,pricePerMonth,numberOfOffers;

    public OffersListViewModel(Model model){
        this.model=model;
        this.model.addListener("Offer",this);
        this.model.addListener("ReloadLists",this);
        this.offers= FXCollections.observableArrayList(model.getOffers().getOffers());
        this.title=new SimpleStringProperty();
        this.landlordName=new SimpleStringProperty();
        this.type=new SimpleStringProperty();
        this.pricePerMonth=new SimpleStringProperty();
        this.numberOfOffers=new SimpleStringProperty(Integer.toString(offers.size()));
    }


    public ObservableList<Offer> getOffers() {
        return offers;
    }

    public StringProperty getTitle(){
        return title;
    }
    public StringProperty getLandlordName(){
        return landlordName;
    }
    public StringProperty getType(){
        return type;
    }
    public StringProperty getPricePerMonth(){
        return pricePerMonth;
    }
    public StringProperty getNumberOfOffers(){
        return numberOfOffers;
    }

    public void removeOffer(Offer offer){
        this.model.getOffers().getOffers().remove(offer);
        this.offers.remove(offer);
        this.numberOfOffers.setValue(Integer.toString(offers.size()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
                case "Offers" -> {
                    this.offers.add(0, (Offer) evt.getNewValue());
                    this.numberOfOffers.setValue(Integer.toString(offers.size()));
                }
                case "ReloadLists" -> this.offers = FXCollections.observableArrayList(model.getOffers().getOffers());
            }

        });
    }
}
