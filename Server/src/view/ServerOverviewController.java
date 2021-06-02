package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * JavaFX controller class for the ServerOverView view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ServerOverviewController extends ViewController{
    @FXML
    private Label numberOfUsers,numberOfOffers,numberOfClosedDeals,numberOfTenants,numberOfLandlords;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        this.numberOfUsers.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfUsers());
        this.numberOfOffers.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfOffers());
        this.numberOfClosedDeals.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfClosedDeals());
        this.numberOfTenants.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfTenants());
        this.numberOfLandlords.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfLandlords());
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
