package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Renting;
import model.RentingList;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the ServerRentingList view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ServerRentingListViewController extends ViewController{
    @FXML private TableView<Renting> rentingTableView;
    @FXML private TableColumn<Renting,String> tenant,landlord,offer;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        reset();
        this.tenant.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenant().getUsername()));
        this.landlord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandlord().getUsername()));
        this.offer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOffer().getTitle()));
    }
    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        this.rentingTableView.setItems(getViewModelFactory().getRentingListViewModel().getRentings());
    }

    /**
     * The method changes the view and displays the main view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }

    /**
     * The method changes the view and displays the details of the selected renting deal.
     */
    @FXML public void openRentingInterface(){
        Renting renting=rentingTableView.getSelectionModel().getSelectedItem();
        if(renting!=null){
            ViewState.getInstance().setDisplayedRenting(renting);
            getViewHandler().openView("rentingView");
        }
    }
}
