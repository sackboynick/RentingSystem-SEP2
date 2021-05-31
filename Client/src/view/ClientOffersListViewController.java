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


public class ClientOffersListViewController extends ViewController {
    @FXML
    private TableView<Offer> offerTableView;
    @FXML
    private TableColumn<Offer, String> title, landlord, type, pricePerMonth, deposit;
    @FXML
    private Button publishAnOffer;

    @Override
    protected void init() {
        reset();
        this.title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.landlord.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLandlord().getUsername()));
        this.type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        this.pricePerMonth.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getPricePerMonth())));
        this.deposit.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getDeposit())));

    }

    public void reset() {
        getViewModelFactory().getOffersListViewModel().updateOffersListFromModel();
        publishAnOffer.setVisible(true);
        this.offerTableView.setItems(getViewModelFactory().getOffersListViewModel().getOffers());
        if (ViewState.getInstance().getUser().getRole().equals("Tenant")) {
            publishAnOffer.setVisible(false);
        }

    }

    @FXML
    public void back() {
        getViewHandler().openView("homePage");
    }

    @FXML
    public void publishOffer() {
        getViewHandler().openView("publishOffer");
    }

    @FXML
    public void openOfferInterface() {
        Offer offer = this.offerTableView.getSelectionModel().getSelectedItem();
        if (offer != null) {
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    @FXML public void openSendMessage(){
        getViewHandler().openView("sendMessageView");
    }}
