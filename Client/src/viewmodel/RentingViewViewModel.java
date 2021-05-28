package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Offer;
import model.User;

import javax.swing.text.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RentingViewViewModel{
    private final Model model;
    private ObservableList<User> landlord,tenant;
    private ObservableList<Offer> offer;
    private StringProperty date,feedback;

    public RentingViewViewModel(Model model){
        this.model=model;
        this.landlord= FXCollections.observableArrayList();
        this.tenant=FXCollections.observableArrayList();
        this.offer=FXCollections.observableArrayList();
        this.date=new SimpleStringProperty();
        this.feedback=new SimpleStringProperty();
    }

    public ObservableList<Offer> getOffer() {
        return offer;
    }

    public ObservableList<User> getLandlord() {
        return landlord;
    }

    public ObservableList<User> getTenant() {
        return tenant;
    }

    public StringProperty getDate() {
        return date;
    }

    public StringProperty getFeedback() {
        return feedback;
    }

    public void setRentingInfo(){
        if(ViewState.getInstance().getDisplayedRenting()!=null) {
            this.landlord.clear();
            this.tenant.clear();
            this.offer.clear();
            this.landlord.setAll(ViewState.getInstance().getDisplayedRenting().getLandlord());
            this.tenant.setAll(ViewState.getInstance().getDisplayedRenting().getTenant());
            this.offer.setAll(ViewState.getInstance().getDisplayedRenting().getOffer());
            this.date.set(ViewState.getInstance().getDisplayedRenting().getDate().toString());
        }
    }

    public void sendFeedback(){
        if(!feedback.get().equals("") && feedback.get()!=null) {
            if(ViewState.getInstance().getDisplayedRenting().getTenant().getUsername().equals(ViewState.getInstance().getUser().getUsername()))
                this.model.publishFeedback("Tenant",feedback.get(),ViewState.getInstance().getDisplayedRenting());
            else
                this.model.publishFeedback("Landlord",feedback.get(),ViewState.getInstance().getDisplayedRenting());
        }
    }


}
