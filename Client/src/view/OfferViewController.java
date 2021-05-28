package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Message;
import viewmodel.ViewState;

public class OfferViewController extends ViewController{
    @FXML private Label title, description, address,type, numberOfRooms,pricePerMonth,deposit,area,floor,landlordName,error,alertMessage;
    @FXML
    private TextArea messageToLandlord;
    @FXML private TextField password;
    @FXML private Button sendOrAcceptRequest;



    @Override
    protected void init() {
        this.title.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getTitle());
        this.description.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getDescription());
        this.address.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getAddress());
        this.type.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getType());
        this.numberOfRooms.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getNumberOfRooms());
        this.pricePerMonth.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getPricePerMonth());
        this.deposit.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getDeposit());
        this.area.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getArea());
        this.floor.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getFloor());
        this.landlordName.textProperty().bind(getViewModelFactory().getOfferViewViewModel().getLandlordName());
        this.password.textProperty().bindBidirectional(getViewModelFactory().getOfferViewViewModel().getPassword());
        reset();
    }

    public void reset(){
        if(ViewState.getInstance().getOffer().getLandlord().getUsername().equals(ViewState.getInstance().getUser().getUsername()) && !ViewState.getInstance().getOffer().getUsernameOfOfferer().equals(""))
            alertMessage.setText(ViewState.getInstance().getOffer().getUsernameOfOfferer()+ " made an offer for this property! Press and insert your password to accept it, or refuse it.");
        this.messageToLandlord.setText("");
        this.error.setText("");
        this.error.setVisible(false);
    }

    @FXML public void onBack(){
        getViewHandler().openView("homePage");
    }

    @FXML public void sendMessageToLandlord(){
        getViewModelFactory().getOfferViewViewModel().sendMessage();
        this.messageToLandlord.setText("");
    }

    @FXML public void sendRequest(){
        getViewModelFactory().getOfferViewViewModel().sendRequest();
    }

}
