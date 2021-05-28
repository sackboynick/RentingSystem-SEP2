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
    @FXML private Button sendRequest,accept,refuse;



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
        this.error.textProperty().bindBidirectional(getViewModelFactory().getOfferViewViewModel().getError());
        reset();
    }

    public void reset(){
        if((ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("") || ViewState.getInstance().getOffer().getUsernameOfOfferer()==null) && ViewState.getInstance().getOffer().getLandlord().getUsername().equals(ViewState.getInstance().getUser().getUsername())) {
            alertMessage.setText("No requests available at the moment!");
            this.accept.setVisible(false);
            this.refuse.setVisible(false);
            this.password.setVisible(false);
            this.sendRequest.setVisible(false);
        }
        else if((ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("") || ViewState.getInstance().getOffer().getUsernameOfOfferer()==null) && !ViewState.getInstance().getOffer().getLandlord().getUsername().equals(ViewState.getInstance().getUser().getUsername())){
            alertMessage.setText("*this action will be unreversible, therefore you need to insert your password to be able to send a request to the landlord*");
            this.accept.setVisible(false);
            this.refuse.setVisible(false);
            this.sendRequest.setVisible(true);
            this.password.setVisible(true);
        }
        else if((!ViewState.getInstance().getOffer().getUsernameOfOfferer().equals("") || ViewState.getInstance().getOffer().getUsernameOfOfferer()!=null)&& ViewState.getInstance().getOffer().getLandlord().getUsername().equals(ViewState.getInstance().getUser().getUsername())){
            alertMessage.setText("*this action will be unreversible, therefore you need to insert your password to be able to send a request to the landlord*");
            this.accept.setVisible(true);
            this.refuse.setVisible(true);
            this.sendRequest.setVisible(false);
            this.password.setVisible(true);
        }
        else{
            alertMessage.setText("There is already a request in pending.");
            this.accept.setVisible(false);
            this.refuse.setVisible(false);
            this.sendRequest.setVisible(false);
            this.password.setVisible(false);
        }
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
        reset();
    }

}
