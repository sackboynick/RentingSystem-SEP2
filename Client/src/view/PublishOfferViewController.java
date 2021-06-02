package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Offer;

/**
 * JavaFX controller class for the publishOfferView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class PublishOfferViewController extends ViewController{
    @FXML
    private TextField title, pricePerMonth, deposit, address, area, floor, numberOfRooms;
    @FXML private Label error;
    @FXML private TextArea description;
    @FXML private ChoiceBox<String> type;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
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

    /**
     * Method executed everytime the view and the controller are set.
     */
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

    /**
     * The method delegates the ViewModel class to create an offer with the details provided by the user.
     */
    @FXML public void createOffer(){
        if(type.getValue()!=null)
        getViewModelFactory().getPublishOfferViewModel().publishOffer(type.getValue());
        else error.setText("Please do not leave any blank field");
        reset();
    }

    /**
     * The method changes the view and displays the offer list interview.
     */
    @FXML public void onBack(){
        getViewHandler().openView("offersList");
    }
}
