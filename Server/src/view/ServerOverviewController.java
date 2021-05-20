package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerOverviewController extends ViewController{
    @FXML
    private Label numberOfUsers,numberOfOffers,numberOfClosedDeals,numberOfTenants,numberOfLandlords;

    @Override
    protected void init() {
        this.numberOfUsers.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfUsers());
        this.numberOfOffers.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfOffers());
        this.numberOfClosedDeals.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfClosedDeals());
        this.numberOfTenants.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfTenants());
        this.numberOfLandlords.textProperty().bind(getViewModelFactory().getServerOverviewViewModel().getNumberOfLandlords());
    }

    public void reset(){

    }

    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }
}
