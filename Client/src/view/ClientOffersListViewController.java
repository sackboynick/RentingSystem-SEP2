package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import model.Offer;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the clientOffersList view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class ClientOffersListViewController extends ViewController {
    @FXML
    private TableView<Offer> offerTableView;
    @FXML
    private TableColumn<Offer, String> title, landlord, type, pricePerMonth, deposit;
    @FXML
    private Button publishAnOffer;
    @FXML
    TextField minPriceTextField, maxPriceTextField, minRoomsTextField, depositTextField, typeTextField, floorTextField;

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
        this.deposit.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getDeposit())));

    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset() {
        getViewModelFactory().getOffersListViewModel().updateOffersListFromModel();
        publishAnOffer.setVisible(true);
        this.offerTableView.setItems(getViewModelFactory().getOffersListViewModel().getOffers());
        if (ViewState.getInstance().getUser().getRole().equals("Tenant")) {
            publishAnOffer.setVisible(false);
        }

    }

    /**
     * Changes the view and displays the HomePage
     */
    @FXML
    public void back() {
        getViewHandler().openView("homePage");
    }

    /**
     * Changes the view and displays the publish offer interface
     */
    @FXML
    public void publishOffer() {
        getViewHandler().openView("publishOffer");
    }

    /**
     * Changes the view and displays the details of the selected user.
     */
    @FXML
    public void openOfferInterface() {
        Offer offer = this.offerTableView.getSelectionModel().getSelectedItem();
        if (offer != null) {
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    /**
     * Filters the search and returns the Offer list with preferred options.
     */
    @FXML
    public void applyFilters() {
        getViewModelFactory().getOffersListViewModel().applyFilters();
    }

    /**
     * Changes the view and displays the view to send a message to an user
     */
    @FXML
    public void openSendMessage() {
        getViewHandler().openView("sendMessageView");
    }}
