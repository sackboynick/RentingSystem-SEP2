package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.Model;
import model.Offer;


public class PublishOfferViewModel {
    private final Model model;
    private StringProperty title, description, pricePerMonth, deposit, address, type, area, floor, numberOfRooms,error;


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


    public void publishOffer(){
        String result=model.addOffer(new Offer(title.get(), description.get(), Double.parseDouble(pricePerMonth.get()),Double.parseDouble(deposit.get()),address.get(), type.get(), Double.parseDouble(area.get()),Integer.parseInt(floor.get()),Integer.parseInt(numberOfRooms.get()),ViewState.getInstance().getUser()));
        if(!result.equals("Valid"))
            error.set(result);
        else
            error.set("");
        }

    public StringProperty getError() {
        return error;
    }
}
