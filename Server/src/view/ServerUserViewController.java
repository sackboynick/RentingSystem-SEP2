package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Message;
import viewmodel.ViewState;


public class ServerUserViewController extends ViewController {
    @FXML
    private Label username,firstName,middleName,lastName,phone,deals,role,usernameLeft;

    @Override
    protected void init() {
        reset();
    }

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


    @FXML
    public void onBack(){
        getViewHandler().openView("mainView");
    }
}
