package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class OfferViewViewModel {
    private final Model model;
    private StringProperty title, description, pricePerMonth, deposit, address, type, area, floor, numberOfRooms,landlordName;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public OfferViewViewModel(Model model){
        this.model=model;
        if(ViewState.getInstance().getOffer()!=null)
            setInterface();
    }

    /**
     * The method sets the data to display in the view model.
     */
    public void setInterface(){
        this.title=new SimpleStringProperty(ViewState.getInstance().getOffer().getTitle());
        this.description=new SimpleStringProperty(ViewState.getInstance().getOffer().getDescription());
        this.pricePerMonth=new SimpleStringProperty(Double.toString(ViewState.getInstance().getOffer().getPricePerMonth()));
        this.deposit=new SimpleStringProperty(Double.toString(ViewState.getInstance().getOffer().getDeposit()));
        this.address=new SimpleStringProperty(ViewState.getInstance().getOffer().getLocation());
        this.type=new SimpleStringProperty(ViewState.getInstance().getOffer().getType());
        this.area=new SimpleStringProperty(Double.toString(ViewState.getInstance().getOffer().getArea()));
        this.floor=new SimpleStringProperty(Integer.toString(ViewState.getInstance().getOffer().getFloor()));
        this.numberOfRooms=new SimpleStringProperty(Integer.toString(ViewState.getInstance().getOffer().getRoomsNumber()));
        this.landlordName=new SimpleStringProperty(ViewState.getInstance().getOffer().getLandlord().getUsername());
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
     * Getter for the landlord name property.
     * @return The StringProperty for the name of the landlord.
     */
    public StringProperty getLandlordName(){
        return landlordName;
    }



}
