package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import viewmodel.ViewState;


public class UserViewController extends ViewController {
    @FXML
    private Label username,firstName,middleName,lastName,phone,deals,role;
    @FXML
    private TextArea message;

    @Override
    protected void init() {
        reset();
    }

    public void reset(){
        this.username.textProperty().bind(getViewModelFactory().getUserViewViewModel().getUsername());
        this.firstName.textProperty().bind(getViewModelFactory().getUserViewViewModel().getFirstName());
        this.middleName.textProperty().bind(getViewModelFactory().getUserViewViewModel().getMiddleName());
        this.lastName.textProperty().bind(getViewModelFactory().getUserViewViewModel().getLastName());
        this.phone.textProperty().bind(getViewModelFactory().getUserViewViewModel().getPhone());
        this.deals.textProperty().bind(getViewModelFactory().getUserViewViewModel().getNumberOfDeals());
        this.role.textProperty().bind(getViewModelFactory().getUserViewViewModel().getRole());
        this.message.setText("");
    }

    @FXML public void sendMessage(){
        String message=this.message.getText();
        if(message!=null && message.equals(""))
             ViewState.getInstance().getDisplayedUser().addMessageOrRequest(message);
    }

    @FXML
    public void onBack(){
        getViewHandler().openView("homePage");
    }
}
