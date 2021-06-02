package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class UserViewViewModel {
    private final Model model;
    private StringProperty username,firstName,middleName,lastName,phone,role,numberOfDeals;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public UserViewViewModel(Model model){
        this.model=model;
        if(ViewState.getInstance().getDisplayedUser()!=null)
            setUserInfo();
    }

    /**
     * The method sets the data to display in the view model.
     */
    public void setUserInfo(){
        this.username=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getUsername());
        this.firstName=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getFirstName());
        this.middleName=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getMiddleName());
        this.lastName=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getLastName());
        this.phone=new SimpleStringProperty(Long.toString(ViewState.getInstance().getDisplayedUser().getPhone()));
        this.role=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getRole());
        this.numberOfDeals=new SimpleStringProperty(Integer.toString(ViewState.getInstance().getDisplayedUser().getDealsClosed()));
    }

    /**
     * Getter for the username property.
     * @return The StringProperty for the username of the user.
     */
    public StringProperty getUsername(){
        return username;
    }

    /**
     * Getter for the first name property.
     * @return The StringProperty for the first name of the user.
     */
    public StringProperty getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name property.
     * @return The StringProperty for the last name of the user.
     */
    public StringProperty getLastName() {
        return lastName;
    }

    /**
     * Getter for the middle name property.
     * @return The StringProperty for the middle name of the user.
     */
    public StringProperty getMiddleName() {
        return middleName;
    }

    /**
     * Getter for the phone number property.
     * @return The StringProperty for the phone number of the user.
     */
    public StringProperty getPhone() {
        return phone;
    }

    /**
     * Getter for the role property.
     * @return The StringProperty for the role of the user.
     */
    public StringProperty getRole() {
        return role;
    }

    /**
     * Getter for the number of deals property.
     * @return The StringProperty for the number of closed deals of the user.
     */
    public StringProperty getNumberOfDeals() {
        return numberOfDeals;
    }



}
