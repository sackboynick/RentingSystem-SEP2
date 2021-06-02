package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Offer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class OffersListViewModel implements PropertyChangeListener {

    private final Model model;
    private ObservableList<Offer> offers;
    private final StringProperty title,type,numberOfOffers;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public OffersListViewModel(Model model){
        this.model=model;
        this.model.addListener("Offer",this);
        this.model.addListener("ReloadLists",this);
        this.offers= FXCollections.observableArrayList(model.getOffers().getOffers());
        this.title=new SimpleStringProperty();
        this.type=new SimpleStringProperty();
        this.numberOfOffers=new SimpleStringProperty(Integer.toString(offers.size()));
    }


    /**
     * Getter for the offers list.
     * @return the ObservableList of Offer objects.
     */
    public ObservableList<Offer> getOffers() {
        return offers;
    }

    /**
     * Getter for the title property.
     * @return The StringProperty for the title of the offer.
     */
    public StringProperty getTitle(){
        return title;
    }
    /**
     * Getter for the type property.
     * @return The StringProperty for the type of the offer.
     */
    public StringProperty getType(){
        return type;
    }

    /**
     * Getter for the number of offers property.
     * @return The StringProperty for the number of offers in the system.
     */
    public StringProperty getNumberOfOffers(){
        return numberOfOffers;
    }

    /**
     * The method removes the offer in the argument from every list in the system.
     * @param offer The Offer object to be removed.
     */
    public void removeOffer(Offer offer){
        this.model.getOffers().getOffers().remove(offer);
        this.offers.remove(offer);
        this.numberOfOffers.setValue(Integer.toString(offers.size()));
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
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
