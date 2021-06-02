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
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class OffersListViewModel implements PropertyChangeListener {
    private ObservableList<Offer> offers;
    private final Model model;
    private final StringProperty type,minPrice, maxPrice, maxDeposit,minRooms, floor;


    /**
     * One-argument constructor.
     *
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public OffersListViewModel(Model model) {
        this.model = model;
        this.model.addListener("Offers", this);
        this.model.addListener("Reload", this);
        this.offers = FXCollections.observableArrayList();
        this.minPrice = new SimpleStringProperty();
        this.maxPrice = new SimpleStringProperty();
        this.minRooms = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.floor = new SimpleStringProperty();
        this.maxDeposit = new SimpleStringProperty();
    }


    /**
     * Getter for the type property.
     * @return The StringProperty for the type of the offer.
     */
    public StringProperty getType(){
        return type;
    }


    /**
     * Getter for the min price property.
     * @return StringProperty for the minimum price of the property.
     */
    public StringProperty getMinPrice() {
        return minPrice;
    }

    /**
     * Getter for the max price property.
     * @return StringProperty for the maximum price of the property.
     */
    public StringProperty getMaxPrice() {
        return maxPrice;
    }

    /**
     * Getter for the max price of the deposit.
     * @return StringProperty for the maximum price of the deposit.
     */
    public StringProperty getMaxDeposit() {
        return maxDeposit;
    }

    /**
     * Getter for the number of rooms property.
     * @return StringProperty for the number of rooms in the property.
     */
    public StringProperty getMinRooms() {
        return minRooms;
    }

    /**
     * Getter for the number of the floor of the property.
     * @return StringProperty for the number of the floor of the property.
     */
    public StringProperty getFloor() {
        return floor;
    }

    /**
     * This method updates the offer list in the viewModel using the data obtained from the model.
     */
    public void updateOffersListFromModel() {
        this.offers = FXCollections.observableArrayList(model.getOffers().getOffers());
    }


    /**
     * Getter for the offer list.
     *
     * @return An ObservableList of the offers.
     */
    public ObservableList<Offer> getOffers() {
        return offers;
    }


    /**
     * This method uses the properties to set some conditions in the offer list.
     */
    public void applyFilters() {
//        if (!minPrice.get().equals(""))
//            setMinPrice(Double.parseDouble(minPrice.get()));
//        if (!maxPrice.get().equals("")) {
//
//        }
        this.offers = FXCollections.observableArrayList(model.applyFilters( Double.parseDouble(minPrice.get()),Double.parseDouble(maxPrice.get()), Integer.parseInt(minRooms.get()), type.get(), Integer.parseInt( floor.get()), Double.parseDouble(maxDeposit.get())));

    }

    /**
     * This method sets a minimum price in the offers of the offer list.
     *
     * @param minPrice The minimum price to set.
     */
    public void setMinPrice(double minPrice) {
        ObservableList<Offer> filteredCopY = FXCollections.observableArrayList();
        for (Offer offer : offers) {
            if (offer.getPricePerMonth() >= minPrice)
                filteredCopY.add(offer);
        }
        this.offers = filteredCopY;
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     *
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Offers"))
            Platform.runLater(() -> this.offers.add(0, (Offer) evt.getNewValue()));
        else if (evt.getPropertyName().equals("Reload")) {
            Platform.runLater(this::updateOffersListFromModel);
        }
    }
}
