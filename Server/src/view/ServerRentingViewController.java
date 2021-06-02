package view;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Offer;
import model.User;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the ServerRentingView view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ServerRentingViewController extends ViewController{
    @FXML
    private TableView<Offer> offer;
    @FXML private TableView<User> tenant, landlord;
    @FXML private Label date,tenantFeedback,landlordFeedback;
    @FXML private TableColumn<Offer,String> title, type;
    @FXML private TableColumn<User,String> tUsername,tFirstName,tLastName,lUsername,lFirstName,lLastName;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        if(ViewState.getInstance().getDisplayedRenting()!=null) {
            reset();
            this.date.setText(ViewState.getInstance().getDisplayedRenting().getDate().toString());
            this.tenantFeedback.setText(ViewState.getInstance().getDisplayedRenting().getTenantFeedback());
            this.landlordFeedback.setText(ViewState.getInstance().getDisplayedRenting().getLandlordFeedback());
            this.title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
            this.type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
            this.tUsername.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            this.tFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
            this.tLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
            this.lUsername.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            this.lFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
            this.lLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        }
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        this.offer.setItems(FXCollections.observableArrayList(ViewState.getInstance().getDisplayedRenting().getOffer()));
        this.tenant.setItems(FXCollections.observableArrayList(ViewState.getInstance().getDisplayedRenting().getTenant()));
        this.landlord.setItems(FXCollections.observableArrayList(ViewState.getInstance().getDisplayedRenting().getLandlord()));
    }

    /**
     * The method changes the view and displays the renting list view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("rentingList");
    }
}
