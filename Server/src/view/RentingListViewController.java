package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Renting;
import model.RentingList;
import viewmodel.ViewState;

public class RentingListViewController extends ViewController{
    @FXML private TableView<Renting> rentingTableView;
    @FXML private TableColumn<Renting,String> tenant,landlord,offer;

    @Override
    protected void init() {
        this.rentingTableView.setItems(getViewModelFactory().getRentingListViewModel().getRentings());
        this.tenant.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenant().getUsername()));
        this.landlord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandlord().getUsername()));
        this.offer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOffer().getTitle()));
    }

    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }

    @FXML public void openRentingInterface(){
        Renting renting=rentingTableView.getSelectionModel().getSelectedItem();
        if(renting!=null){
            ViewState.getInstance().setDisplayedRenting(renting);
            getViewHandler().openView("rentingView");
        }
    }
}
