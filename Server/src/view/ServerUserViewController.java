package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * JavaFX controller class for the ServerUserView view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class ServerUserViewController extends ViewController {
    @FXML
    private Label username,firstName,middleName,lastName,phone,deals,role,usernameLeft;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        reset();
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        getViewModelFactory().getUserViewViewModel().setUserInfo();
        this.username.textProperty().bind(getViewModelFactory().getUserViewViewModel().getUsername());
        this.firstName.textProperty().bind(getViewModelFactory().getUserViewViewModel().getFirstName());
        this.middleName.textProperty().bind(getViewModelFactory().getUserViewViewModel().getMiddleName());
        this.lastName.textProperty().bind(getViewModelFactory().getUserViewViewModel().getLastName());
        this.phone.textProperty().bind(getViewModelFactory().getUserViewViewModel().getPhone());
        this.deals.textProperty().bind(getViewModelFactory().getUserViewViewModel().getNumberOfDeals());
        this.role.textProperty().bind(getViewModelFactory().getUserViewViewModel().getRole());
        this.usernameLeft.textProperty().bind(getViewModelFactory().getUserViewViewModel().getUsername());
    }


    /**
     * The method changes the view and displays the main view.
     */
    @FXML
    public void onBack(){
        getViewHandler().openView("mainView");
    }
}
