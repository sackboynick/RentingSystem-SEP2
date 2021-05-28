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

public class RentingViewController extends ViewController{
    @FXML
    private TableView<Offer> offer;
    @FXML private TableView<User> tenant, landlord;
    @FXML private Label date,tenantFeedback,landlordFeedback;
    @FXML private TableColumn<Offer,String> title, type;
    @FXML private TableColumn<User,String> tUsername,tFirstName,tLastName,lUsername,lFirstName,lLastName;

    @Override
    protected void init() {
        if(ViewState.getInstance().getDisplayedRenting()!=null)
        this.offer.setItems(FXCollections.observableArrayList(ViewState.getInstance().getDisplayedRenting().getOffer()));
        this.tenant.setItems(FXCollections.observableArrayList(ViewState.getInstance().getDisplayedRenting().getTenant()));
        this.landlord.setItems(FXCollections.observableArrayList(ViewState.getInstance().getDisplayedRenting().getLandlord()));
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

    public void reset(){

    }

    @FXML public void onBack(){
        getViewHandler().openView("rentingList");
    }
}
