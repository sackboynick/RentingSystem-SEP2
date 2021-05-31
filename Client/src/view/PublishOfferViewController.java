package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Offer;

public class PublishOfferViewController extends ViewController{
    @FXML
    private TextField title, pricePerMonth, deposit, address, area, floor, numberOfRooms;
    @FXML private Label error;
    @FXML private TextArea description;
    @FXML private ChoiceBox<String> type;

    @Override
    protected void init() {
        title.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getTitle());
        description.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getDescription());
        deposit.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getDeposit());
        pricePerMonth.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getPricePerMonth());
        address.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getAddress());
        area.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getArea());
        floor.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getFloor());
        numberOfRooms.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getNumberOfRooms());
        type.accessibleTextProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getType());
        this.error.textProperty().bind(getViewModelFactory().getPublishOfferViewModel().getError());
        reset();
    }

    public void reset(){
        title.setText("");
        description.setText("");
        pricePerMonth.setText("");
        deposit.setText("");
        address.setText("");
        area.setText("");
        floor.setText("");
        numberOfRooms.setText("");
        type.setItems(FXCollections.observableArrayList(Offer.getTYPE()));
    }

    @FXML public void createOffer(){
        getViewModelFactory().getPublishOfferViewModel().publishOffer();
        reset();
    }

    @FXML public void onBack(){
        getViewHandler().openView("offersList");
    }
}
