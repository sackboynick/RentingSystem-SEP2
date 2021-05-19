package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerOverviewController extends ViewController{
    @FXML
    private Label numberOfUsers,numberOfOffers,numberOfClosedDeals,numberOfTenants,numberOfLandlords;

    @Override
    protected void init() {
        this.numberOfUsers.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfUsers());
    }
}
