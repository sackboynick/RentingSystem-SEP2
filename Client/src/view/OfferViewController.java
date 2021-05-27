package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Message;
import viewmodel.ViewState;

public class OfferViewController extends ViewController{
    @FXML private Label title, description, address,type, numberOfRooms,pricePerMonth,deposit,area,floor,landlordName;
    @FXML
    private TextArea messageToLandlord;


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
        reset();
    }

    public void reset(){
        this.messageToLandlord.setText("");
    }

    @FXML public void onBack(){
        getViewHandler().openView("homePage");
    }

    @FXML public void sendMessageToLandlord(){
        ViewState.getInstance().getOffer().getLandlord().addMessageOrRequest(new Message(messageToLandlord.getText(),ViewState.getInstance().getUser()));
        this.messageToLandlord.setText("");
    }
}
