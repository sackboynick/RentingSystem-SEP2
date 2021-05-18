package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Offer;
import model.Renting;
import model.User;
import viewmodel.ViewState;


public class MainViewController extends ViewController{
    @FXML
    public ListView<Renting> dealsList;
    @FXML
    public ListView<Offer> offersList;
    @FXML
    private ListView<User> onlineUsersList;

    @FXML private Label messageStatus;

    public MainViewController(){

    }

    @Override
    protected void init(){
        this.onlineUsersList.setItems(getViewModelFactory().getMainViewViewModel().getUsers());
        this.offersList.setItems(getViewModelFactory().getMainViewViewModel().getOffers());
        this.messageStatus.setText("");
    }

    public void reset(){

    }
    @FXML
    public void openUserInterface(){
        User user=this.onlineUsersList.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    @FXML public void openOfferInterface(){
        Offer offer=this.offersList.getSelectionModel().getSelectedItem();
        if(offer!=null){
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    @FXML public void openUsersList(){
        getViewHandler().openView("usersList");
    }

    @FXML public void openOffersList(){
        getViewHandler().openView("offersList");
    }
}
