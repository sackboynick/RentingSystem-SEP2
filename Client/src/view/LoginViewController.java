package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;

/**
 * JavaFX controller class for the loginView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class LoginViewController extends ViewController{
    @FXML
    private TextField username,password;
    @FXML private Label error;



    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        this.username.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getUsername());
        this.password.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getPassword());
        reset();
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset() {
        this.error.setVisible(false);
        getViewModelFactory().getLoginViewModel().getUsername().set("");
        getViewModelFactory().getLoginViewModel().getPassword().set("");
    }

    /**
     * The method delegates the ViewModel class to login using the credentials provided by the user.
     * @throws InterruptedException In case of interruptions in the connection with the server.
     * @throws IOException In case of issues in the connection with the server.
     */
    @FXML public void login() throws InterruptedException, IOException {
        if(getViewModelFactory().getLoginViewModel().login()!=null)
           getViewHandler().openView("homePage");
        else
            this.error.setVisible(true);
    }

    /**
     * The method changes the view and displays the sign up interface.
     */
    @FXML public void signUpRedirect(){
        getViewHandler().openView("signUp");
    }

}