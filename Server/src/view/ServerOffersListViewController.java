package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Offer;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the ServerOffersList view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ServerOffersListViewController extends ViewController {
    @FXML
    private TableView<Offer> offerTableView;
    @FXML private TableColumn<Offer,String> title,landlord,type,pricePerMonth;
    @FXML private Label numberOfOffers;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        reset();
        this.title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.landlord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandlord().getUsername()));
        this.type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        this.pricePerMonth.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getPricePerMonth())));
        this.numberOfOffers.textProperty().bind(getViewModelFactory().getOffersListViewModel().getNumberOfOffers());
    }
    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        this.offerTableView.setItems(getViewModelFactory().getOffersListViewModel().getOffers());
    }

    /**
     * The method removes the offer selected from all the lists.
     */
    @FXML public void deleteOffer(){
        Offer offer=this.offerTableView.getSelectionModel().getSelectedItem();
        if(offer!=null) {
            getViewModelFactory().getOffersListViewModel().removeOffer(offer);
        }
    }

    /**
     * The method changes the view and displays the interface with the details of the selected offer.
     */
    @FXML
    public void openOfferInterface(){
        Offer offer=this.offerTableView.getSelectionModel().getSelectedItem();
        if(offer!=null) {
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    /**
     * The method changes the view and displays the main view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }
}
