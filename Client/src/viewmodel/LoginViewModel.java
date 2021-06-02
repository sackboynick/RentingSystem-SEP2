package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.User;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class LoginViewModel {
    private final StringProperty username;
    private final StringProperty password;
    private final Model model;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public LoginViewModel(Model model){
        this.model=model;
        this.username=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
    }


    /**
     * Getter for the username property.
     * @return The StringProperty for the username.
     */
    public StringProperty getUsername(){
        return username;
    }

    /**
     * Getter for the password property.
     * @return The StringProperty for the password.
     */
    public StringProperty getPassword() {
        return password;
    }


    /**
     * The method delegates the model to log in the user using the StringProperty of the username and the password.
     * @return The user corresponding to those credentials if it exists, otherwise it returns null.
     */
    public User login() {
        return model.login(username.get(), password.get());
    }



}