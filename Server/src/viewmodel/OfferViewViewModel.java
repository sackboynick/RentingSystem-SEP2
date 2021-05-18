package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import javax.swing.text.View;

public class OfferViewViewModel {
    private final Model model;
    private StringProperty title, description, pricePerMonth, deposit, address, type, area, floor, numberOfRooms,landlordName;
    private BooleanProperty pets,smoking,balcony,freeWifi;

    public OfferViewViewModel(Model model){
        this.model=model;
        if(ViewState.getInstance().getOffer()!=null)
            setInterface();
    }

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
        this.pets=new SimpleBooleanProperty();
        this.smoking=new SimpleBooleanProperty();
        this.balcony=new SimpleBooleanProperty();
        this.freeWifi=new SimpleBooleanProperty();
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

    public StringProperty getNumberOfRooms() {
        return numberOfRooms;
    }

    public StringProperty getType() {
        return type;
    }

    public StringProperty getLandlordName(){
        return landlordName;
    }

    public BooleanProperty isBalcony() {
        return balcony;
    }

    public BooleanProperty isFreeWifi() {
        return freeWifi;
    }

    public BooleanProperty isPets() {
        return pets;
    }

    public BooleanProperty isSmoking() {
        return smoking;
    }
}
