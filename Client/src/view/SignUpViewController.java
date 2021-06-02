package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * JavaFX controller class for the signUpView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class SignUpViewController extends ViewController{
    @FXML private TextField username, password,firstName,middleName,lastName,phone;
    @FXML private ChoiceBox<String> role;
    @FXML private Label error;

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        getViewModelFactory().getSignUpViewModel().getUsername().set("nick");
        getViewModelFactory().getSignUpViewModel().getPassword().set("password");
        getViewModelFactory().getSignUpViewModel().getFirstName().set("Nicola");
        getViewModelFactory().getSignUpViewModel().getMiddleName().set("");
        getViewModelFactory().getSignUpViewModel().getLastName().set("Santolini");
        getViewModelFactory().getSignUpViewModel().getPhone().set("456435");
        getViewModelFactory().getSignUpViewModel().getRole().set("Tenant");
        error.setText("");
    }
    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        username.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getUsername());
        password.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getPassword());
        firstName.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getFirstName());
        middleName.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getMiddleName());
        lastName.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getLastName());
        phone.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getPhone());
        role.setItems(FXCollections.observableArrayList("Landlord","Tenant"));
        role.accessibleTextProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getRole());
        reset();
    }

    /**
     * The method delegates the ViewModel class to register the user account with the details provided by the user.
     */
    @FXML
    public void signUp(){
        String result=getViewModelFactory().getSignUpViewModel().signUp(role.getValue());
        if(result.equals("Valid"))
            getViewHandler().openView("Login");
        else
            this.error.setText(result);
    }

    /**
     * The method changes the view and displays the log in interface.
     */
    @FXML public void onBack(){
        getViewHandler().openView("Login");
    }
}
