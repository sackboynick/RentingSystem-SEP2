package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * JavaFX controller class for the ServerOfferView view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class ServerOfferViewController extends ViewController{
    @FXML private Label title, description, address,type, numberOfRooms,pricePerMonth,deposit,area,floor,landlordName;


    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
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
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        
    }

    /**
     * The method changes the view and displays the main view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }


}
