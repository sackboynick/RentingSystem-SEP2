package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import model.Model;
import model.User;
/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class SignUpViewModel {
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty firstName,middleName,lastName,phone,role;
    private final Model model;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public SignUpViewModel(Model model){
        this.model=model;
        this.username=new SimpleStringProperty("");
        this.password=new SimpleStringProperty("");
        this.firstName=new SimpleStringProperty("");
        this.middleName=new SimpleStringProperty("");
        this.lastName=new SimpleStringProperty("");
        this.phone=new SimpleStringProperty("");
        this.role=new SimpleStringProperty("");
    }


    /**
     * Getter for the username property.
     * @return The StringProperty for the username of the user.
     */
    public StringProperty getUsername(){
        return username;
    }

    /**
     * Getter for the password property.
     * @return The StringProperty for the password of the user.
     */
    public StringProperty getPassword() {
        return password;
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
     * The methods delegate the model to create and register an account in the system.
     * @param role The role of the created user.
     * @return A String containing the result of the action.
     */
    public String signUp(String role){
        if(username.get().equals("") || password.get().equals("") || firstName.get().equals("")  || lastName.get().equals("") || phone.get().equals("") || role.equals("")) {
            {
                return "Please do not leave any blank field.";
            }
        }
        else if(password.get().length()<5) {
            this.password.set("");
            return "The password should be at least 5 characters long.";
        }
        else if(model.signUp(new User(username.get(), firstName.get(), middleName.get(), lastName.get(), password.get(), Long.parseLong(phone.get()), role)))
            return "Valid";
        else
            return "The user already exists.";
    }
}
