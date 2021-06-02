package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Renting;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the clientRentingListView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class ClientRentingListViewController extends ViewController{
    @FXML private TableView<Renting> rentingTableView;
    @FXML private TableColumn<Renting,String> tenant,landlord,offer;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        reset();
        this.rentingTableView.setItems(getViewModelFactory().getRentingListViewModel().getRentings());
        this.tenant.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenant().getUsername()));
        this.landlord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandlord().getUsername()));
        this.offer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOffer().getTitle()));
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        getViewModelFactory().getRentingListViewModel().updateRentingList();
    }


    /**
     * The method changes the view and displays the HomePage.
     */
    @FXML public void onBack(){
        getViewHandler().openView("homePage");
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
