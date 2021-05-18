package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController extends ViewController{
    @FXML
    private TextField username,password;

    public LoginViewController(){
    }

    @Override
    protected void init() {
        this.username.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getUsername());
        this.password.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getPassword());
    }

    public void reset() {
        getViewModelFactory().getLoginViewModel().getUsername().set("");
        getViewModelFactory().getLoginViewModel().getPassword().set("");
    }

    @FXML public void login() throws InterruptedException, IOException {
        getViewModelFactory().getLoginViewModel().login();
        getViewHandler().openView("homePage");
    }

    @FXML public void signUpRedirect(){
        getViewHandler().openView("signUp");
    }

}