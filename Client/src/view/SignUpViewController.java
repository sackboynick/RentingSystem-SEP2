package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SignUpViewController extends ViewController{
    @FXML private TextField username, password,firstName,middleName,lastName,phone;
    @FXML private ChoiceBox<String> role;

    public void reset(){
        getViewModelFactory().getSignUpViewModel().getUsername().set("");
        getViewModelFactory().getSignUpViewModel().getPassword().set("");
        getViewModelFactory().getSignUpViewModel().getFirstName().set("");
        getViewModelFactory().getSignUpViewModel().getMiddleName().set("");
        getViewModelFactory().getSignUpViewModel().getLastName().set("");
        getViewModelFactory().getSignUpViewModel().getPhone().set("");
        getViewModelFactory().getSignUpViewModel().getRole().set("");
    }
    @Override
    protected void init() {
        username.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getUsername());
        password.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getPassword());
        firstName.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getFirstName());
        middleName.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getMiddleName());
        lastName.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getLastName());
        phone.textProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getPhone());
        role.accessibleTextProperty().bindBidirectional(getViewModelFactory().getSignUpViewModel().getRole());
        role.setItems(FXCollections.observableArrayList("Landlord","Tenant"));
        reset();
    }
    @FXML
    public void signUp(){
        getViewModelFactory().getSignUpViewModel().signUp();
        getViewHandler().openView("Login");
    }

    @FXML public void onBack(){
        getViewHandler().openView("Login");
    }
}
