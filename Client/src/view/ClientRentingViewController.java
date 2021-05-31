package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Offer;
import model.User;
import viewmodel.ViewState;

public class ClientRentingViewController extends ViewController{
    @FXML
    private TableView<Offer> offer;
    @FXML private TableView<User> tenant, landlord;
    @FXML private Label date,error;
    @FXML private TextArea feedback;
    @FXML private TableColumn<Offer,String> title, type;
    @FXML private TableColumn<User,String> tUsername,tFirstName,tLastName,lUsername,lFirstName,lLastName;
    @FXML private Button publishButton;


    @Override
    protected void init() {
        reset();
        if(ViewState.getInstance().getDisplayedRenting()!=null) {
            this.feedback.textProperty().bindBidirectional(getViewModelFactory().getRentingViewViewModel().getFeedback());
            this.title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
            this.type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
            this.tUsername.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            this.tFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
            this.tLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
            this.lUsername.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            this.lFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
            this.lLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
            this.date.textProperty().bindBidirectional(getViewModelFactory().getRentingViewViewModel().getDate());
        }

    }

    public void reset(){
        this.feedback.setVisible(false);
        this.publishButton.setVisible(false);
        this.error.setText("");
        getViewModelFactory().getRentingViewViewModel().setRentingInfo();
        this.offer.setItems(getViewModelFactory().getRentingViewViewModel().getOffer());
        this.tenant.setItems(getViewModelFactory().getRentingViewViewModel().getTenant());
        this.landlord.setItems(getViewModelFactory().getRentingViewViewModel().getLandlord());
        if(ViewState.getInstance().getDisplayedRenting().getLandlordFeedback().equals("") && ViewState.getInstance().getDisplayedRenting().getLandlord().getUsername().equals(ViewState.getInstance().getUser().getUsername())){
            this.feedback.setVisible(true);
            this.publishButton.setVisible(true);
        }
        else if(ViewState.getInstance().getDisplayedRenting().getTenantFeedback().equals("") && ViewState.getInstance().getDisplayedRenting().getTenant().getUsername().equals(ViewState.getInstance().getUser().getUsername())){
            this.feedback.setVisible(true);
            this.publishButton.setVisible(true);
        }
    }



    @FXML public void onBack(){
        getViewHandler().openView("rentingListView");
    }

    @FXML public void publishFeedback(){
        if(feedback.getText().equals("") || feedback.getText()==null) {
            this.error.setText("Please insert some text");
        }
        else {
            getViewModelFactory().getRentingViewViewModel().sendFeedback();
            this.error.setText("");
            this.feedback.setText("");
        }
    }
}
