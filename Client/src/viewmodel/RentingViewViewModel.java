package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Offer;
import model.User;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class RentingViewViewModel{
    private final Model model;
    private final ObservableList<User> landlord;
    private final ObservableList<User> tenant;
    private final ObservableList<Offer> offer;
    private final StringProperty date;
    private final StringProperty feedback;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public RentingViewViewModel(Model model){
        this.model=model;
        this.landlord= FXCollections.observableArrayList();
        this.tenant=FXCollections.observableArrayList();
        this.offer=FXCollections.observableArrayList();
        this.date=new SimpleStringProperty();
        this.feedback=new SimpleStringProperty();
    }

    /**
     * Getter for the offer list.
     * @return An ObservableList of Offer objects.
     */
    public ObservableList<Offer> getOffer() {
        return offer;
    }

    /**
     * Getter for the list of landlords.
     * @return An ObservableList of User objects.
     */
    public ObservableList<User> getLandlord() {
        return landlord;
    }

    /**
     * Getter for the list of tenants.
     * @return An ObservableList of User objects.
     */
    public ObservableList<User> getTenant() {
        return tenant;
    }

    /**
     * Getter for the date property.
     * @return The StringProperty for the date of the renting.
     */
    public StringProperty getDate() {
        return date;
    }

    /**
     * Getter for the feedback property.
     * @return The StringProperty for the feedback of the renting.
     */
    public StringProperty getFeedback() {
        return feedback;
    }

    /**
     * The method sets the data for the viewModel.
     */
    public void setRentingInfo(){
        if(ViewState.getInstance().getDisplayedRenting()!=null) {
            this.landlord.removeAll();
            this.tenant.removeAll();
            this.offer.removeAll();
            this.landlord.add(ViewState.getInstance().getDisplayedRenting().getLandlord());
            this.tenant.add(ViewState.getInstance().getDisplayedRenting().getTenant());
            this.offer.add(ViewState.getInstance().getDisplayedRenting().getOffer());
            this.date.set(ViewState.getInstance().getDisplayedRenting().getDate().toString());
        }
    }

    /**
     * The method publishes a feedback for the deal delegating the model.
     */
    public void sendFeedback(){
        if(!feedback.get().equals("") && feedback.get()!=null) {
            if(ViewState.getInstance().getDisplayedRenting().getTenant().getUsername().equals(ViewState.getInstance().getUser().getUsername()))
                this.model.publishFeedback("Tenant",feedback.get(),ViewState.getInstance().getDisplayedRenting());
            else
                this.model.publishFeedback("Landlord",feedback.get(),ViewState.getInstance().getDisplayedRenting());
        }
    }


}
