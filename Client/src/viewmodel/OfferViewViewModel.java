package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class OfferViewViewModel implements PropertyChangeListener {
    private final Model model;
    private final StringProperty title, description, pricePerMonth, deposit, address, type, area, floor, numberOfRooms,landlordName,password,messageToLandlord,error;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public OfferViewViewModel(Model model){
        this.model=model;
        this.model.addListener("Reload",this);
        this.title=new SimpleStringProperty();
        this.description=new SimpleStringProperty();
        this.pricePerMonth=new SimpleStringProperty();
        this.deposit=new SimpleStringProperty();
        this.address=new SimpleStringProperty();
        this.type=new SimpleStringProperty();
        this.area=new SimpleStringProperty();
        this.floor=new SimpleStringProperty();
        this.numberOfRooms=new SimpleStringProperty();
        this.landlordName=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
        this.messageToLandlord=new SimpleStringProperty();
        this.error=new SimpleStringProperty();
        if(ViewState.getInstance().getOffer()!=null)
            setInterface();
    }

    /**
     * The method sets the data to display the view model.
     */
    public void setInterface(){
        this.title.set(ViewState.getInstance().getOffer().getTitle());
        this.description.set(ViewState.getInstance().getOffer().getDescription());
        this.pricePerMonth.set(Double.toString(ViewState.getInstance().getOffer().getPricePerMonth()));
        this.deposit.set(Double.toString(ViewState.getInstance().getOffer().getDeposit()));
        this.address.set(ViewState.getInstance().getOffer().getLocation());
        this.type.set(ViewState.getInstance().getOffer().getType());
        this.area.set(Double.toString(ViewState.getInstance().getOffer().getArea()));
        this.floor.set(Integer.toString(ViewState.getInstance().getOffer().getFloor()));
        this.numberOfRooms.set(Integer.toString(ViewState.getInstance().getOffer().getRoomsNumber()));
        this.landlordName.set(ViewState.getInstance().getOffer().getLandlord().getUsername());
        this.password.set("");
        this.messageToLandlord.set("");
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
     * Getter for the password property.
     * @return The StringProperty for the password to insert.
     */
    public StringProperty getPassword() {
        return password;
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


    /**
     * Getter for the message to the landlord property.
     * @return The StringProperty for the message to the landlord.
     */
    public StringProperty getMessageToLandlord() {
        return messageToLandlord;
    }

    /**
     * Getter for the error property.
     * @return The StringProperty for error in the actions.
     */
    public StringProperty getError() {
        return error;
    }

    /**
     * The method delegates the model to send a request to the landlord for the property. If the password property results empty,
     * an error would be set in the Error property.
     */
    public void sendRequest(){
        if(ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("") && ViewState.getInstance().getUser().getPassword().equals(password.get()))
            this.model.sendRequest(ViewState.getInstance().getUser().getUsername(),ViewState.getInstance().getOffer());
        else
            this.error.set("Please insert the correct password");
    }

    /**
     * The method delegates the model to accept a request for the property. If the password property results empty,
     * an error would be set in the Error property.
     */
    public void acceptRequest(){
        if(!ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("") && ViewState.getInstance().getUser().getPassword().equals(password.get()))
            this.model.acceptRequest(ViewState.getInstance().getOffer().getUsernameOfOfferer(),ViewState.getInstance().getOffer());

    }

    /**
     * The method delegates the model to refuse a request for the property. If the password property results empty,
     * an error would be set in the Error property.
     */
    public void refuseRequest(){
        if(!ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("")  && ViewState.getInstance().getUser().getPassword().equals(password.get()))
            this.model.refuseRequest(ViewState.getInstance().getOffer());
    }


    /**
     * The method delegates the model send a message to the landlord. If the body of the message property results empty,
     * an error would be set in the Error property.
     */
    public void sendMessage(){
        String result;
        if(messageToLandlord.get().equals(""))
            error.set("Please write something in the body");
        else {
            result = this.model.sendMessage(ViewState.getInstance().getUser(), ViewState.getInstance().getOffer().getLandlord().getUsername(), messageToLandlord.get());
            if(result.equals("Valid"))
                error.set("");
            else
                error.set(result);
        }
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setInterface();
    }
}
