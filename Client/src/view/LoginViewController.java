package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;

public class LoginViewController extends ViewController{
    @FXML
    private TextField username,password;
    @FXML private Label error;

    public LoginViewController(){
    }

    @Override
    protected void init() {
        this.username.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getUsername());
        this.password.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getPassword());
        reset();
    }

    public void reset() {
        this.error.setVisible(false);
        getViewModelFactory().getLoginViewModel().getUsername().set("");
        getViewModelFactory().getLoginViewModel().getPassword().set("");
    }

    @FXML public void login() throws InterruptedException, IOException {
        if(getViewModelFactory().getLoginViewModel().login()!=null)
           getViewHandler().openView("homePage");
        else
            this.error.setVisible(true);
    }

    @FXML public void signUpRedirect(){
        getViewHandler().openView("signUp");
    }

}