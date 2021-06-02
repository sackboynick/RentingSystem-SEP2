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
    private final StringProperty username,firstName,middleName,lastName,phone,role,numberOfDeals,message;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public UserViewViewModel(Model model){
        this.model=model;
        this.username=new SimpleStringProperty();
        this.firstName=new SimpleStringProperty();
        this.middleName=new SimpleStringProperty();
        this.lastName=new SimpleStringProperty();
        this.phone=new SimpleStringProperty();
        this.role=new SimpleStringProperty();
        this.numberOfDeals=new SimpleStringProperty();
        this.message=new SimpleStringProperty();
        if(ViewState.getInstance().getDisplayedUser()!=null)
            setUserInfo();
    }

    /**
     * The method sets the data in the viewModel calling the ViewState instance.
     */
    public void setUserInfo(){
        this.username.set(ViewState.getInstance().getDisplayedUser().getUsername());
        this.firstName.set(ViewState.getInstance().getDisplayedUser().getFirstName());
        this.middleName.set(ViewState.getInstance().getDisplayedUser().getMiddleName());
        this.lastName.set(ViewState.getInstance().getDisplayedUser().getLastName());
        this.phone.set(Long.toString(ViewState.getInstance().getDisplayedUser().getPhone()));
        this.role.set(ViewState.getInstance().getDisplayedUser().getRole());
        this.numberOfDeals.set(Integer.toString(ViewState.getInstance().getDisplayedUser().getDealsClosed()));
        this.message.set("");
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


    /**
     * The method delegates the model to send a message using the message properties in this class.
     */
    public void sendMessage(){
        if(!message.get().equals(""))
            this.model.sendMessage(ViewState.getInstance().getUser(), ViewState.getInstance().getDisplayedUser().getUsername(), message.toString());
    }

}
