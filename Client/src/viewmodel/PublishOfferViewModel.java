package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Offer;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class PublishOfferViewModel {
    private final Model model;
    private final StringProperty title, description, pricePerMonth, deposit, address, type, area, floor, numberOfRooms,error;


    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public PublishOfferViewModel(Model model){
        this.model=model;
        this.title=new SimpleStringProperty();
        this.description=new SimpleStringProperty();
        this.pricePerMonth=new SimpleStringProperty();
        this.deposit=new SimpleStringProperty();
        this.address=new SimpleStringProperty();
        this.type=new SimpleStringProperty();
        this.area=new SimpleStringProperty();
        this.floor=new SimpleStringProperty();
        this.numberOfRooms=new SimpleStringProperty();
        this.error=new SimpleStringProperty();
    }

    /**
     * Getter for the title property.
     * @return The StringProperty for the title of the offer.
     */
    public StringProperty getTitle() {
        return title;
    }

    /**
     * Getter for the description property.
     * @return The StringProperty for the description of the offer.
     */
    public StringProperty getDescription() {
        return description;
    }

    /**
     * Getter for the price per month property.
     * @return The StringProperty for the price per month of the offer.
     */
    public StringProperty getPricePerMonth() {
        return pricePerMonth;
    }

    /**
     * Getter for the deposit property.
     * @return The StringProperty for the deposit of the offer.
     */
    public StringProperty getDeposit() {
        return deposit;
    }

    /**
     * Getter for the address property.
     * @return The StringProperty for the address of the property.
     */
    public StringProperty getAddress() {
        return address;
    }

    /**
     * Getter for the area property.
     * @return The StringProperty for the area of the property.
     */
    public StringProperty getArea() {
        return area;
    }

    /**
     * Getter for the floor property.
     * @return The StringProperty for the floor of the property.
     */
    public StringProperty getFloor() {
        return floor;
    }


    /**
     * Getter for the number of rooms property.
     * @return The StringProperty for the number of rooms of the property.
     */
    public StringProperty getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Getter for the type property.
     * @return The StringProperty for the type of the property.
     */
    public StringProperty getType() {
        return type;
    }


    /**
     * Getter for the error property.
     * @return The StringProperty for error in the actions.
     */
    public StringProperty getError() {
        return error;
    }

    /**
     * The method delegates the model to publish an offer with the data provided by the user.
     * @param value The String for the type of the property.
     *
     *
     */
    public void publishOffer(String value){
        String result=model.addOffer(new Offer(title.get(), description.get(), Double.parseDouble(pricePerMonth.get()),Double.parseDouble(deposit.get()),address.get(), value, Double.parseDouble(area.get()),Integer.parseInt(floor.get()),Integer.parseInt(numberOfRooms.get()),ViewState.getInstance().getUser()));
        if(!result.equals("Valid"))
            error.set(result);
        else
            error.set("");
        }

}
