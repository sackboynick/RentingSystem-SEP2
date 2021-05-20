package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Offer;
import model.User;
import viewmodel.ViewState;

public class OffersListViewController extends ViewController {
    @FXML
    private TableView<Offer> offerTableView;
    @FXML private TableColumn<Offer,String> title,landlord,type,pricePerMonth;
    @FXML private Label numberOfOffers;

    @Override
    protected void init() {
        this.offerTableView.setItems(getViewModelFactory().getOffersListViewModel().getOffers());
        this.title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.landlord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandlord().getUsername()));
        this.type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        this.pricePerMonth.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getPricePerMonth())));
        this.numberOfOffers.textProperty().bind(getViewModelFactory().getOffersListViewModel().getNumberOfOffers());
    }

    @FXML public void deleteOffer(){
        Offer offer=this.offerTableView.getSelectionModel().getSelectedItem();
        if(offer!=null) {
            getViewModelFactory().getOffersListViewModel().removeOffer(offer);
        }
    }
    @FXML
    public void openOfferInterface(){
        Offer offer=this.offerTableView.getSelectionModel().getSelectedItem();
        if(offer!=null) {
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }
}
