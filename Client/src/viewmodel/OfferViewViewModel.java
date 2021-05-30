package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import javax.swing.text.View;

public class OfferViewViewModel {
    private final Model model;
    private StringProperty title, description, pricePerMonth, deposit, address, type, area, floor, numberOfRooms,landlordName,password,messageToLandlord,error;

    public OfferViewViewModel(Model model){
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
        this.landlordName=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
        this.messageToLandlord=new SimpleStringProperty();
        this.error=new SimpleStringProperty();
        if(ViewState.getInstance().getOffer()!=null)
            setInterface();
    }

    public void setInterface(){
        this.title.set(ViewState.getInstance().getOffer().getTitle());
        this.description.set(ViewState.getInstance().getOffer().getDescription());
        this.pricePerMonth.set(Double.toString(ViewState.getInstance().getOffer().getPricePerMonth()));
        this.deposit.set(Double.toString(ViewState.getInstance().getOffer().getDeposit()));
        this.address.set(ViewState.getInstance().getOffer().getLocation());
        this.type.set(ViewState.getInstance().getOffer().getLocation());
        this.area.set(ViewState.getInstance().getOffer().getType());
        this.floor.set(Double.toString(ViewState.getInstance().getOffer().getArea()));
        this.numberOfRooms.set(Integer.toString(ViewState.getInstance().getOffer().getFloor()));
        this.landlordName.set(Integer.toString(ViewState.getInstance().getOffer().getRoomsNumber()));
        this.password.set("");
        this.messageToLandlord.set("");
        this.error.set("");

    }
    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getDescription() {
        return description;
    }

    public StringProperty getPricePerMonth() {
        return pricePerMonth;
    }

    public StringProperty getDeposit() {
        return deposit;
    }

    public StringProperty getAddress() {
        return address;
    }

    public StringProperty getArea() {
        return area;
    }

    public StringProperty getFloor() {
        return floor;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getNumberOfRooms() {
        return numberOfRooms;
    }

    public StringProperty getType() {
        return type;
    }

    public StringProperty getLandlordName(){
        return landlordName;
    }


    public StringProperty getMessageToLandlord() {
        return messageToLandlord;
    }

    public StringProperty getError() {
        return error;
    }

    public void sendRequest(){
        if(ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("") && ViewState.getInstance().getUser().getPassword().equals(password.get()))
            this.model.sendRequest(ViewState.getInstance().getUser().getUsername(),ViewState.getInstance().getOffer());
        else
            this.error.set("Please insert the correct password");
    }

    public void acceptRequest(){
        if(!ViewState.getInstance().getOffer().getUsernameOfOfferer().equals(""))
            this.model.acceptRequest(ViewState.getInstance().getOffer().getUsernameOfOfferer(),ViewState.getInstance().getOffer());

    }

    public void refuseRequest(){
        if(!ViewState.getInstance().getOffer().getUsernameOfOfferer().equals(""))
            this.model.refuseRequest(ViewState.getInstance().getOffer());
    }


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
}
