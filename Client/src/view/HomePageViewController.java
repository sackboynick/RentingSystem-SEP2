package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Message;
import model.Offer;
import model.User;
import viewmodel.ViewState;

public class HomePageViewController extends ViewController{
    @FXML
    private ListView<User> onlineUsers;
    @FXML private ListView<Offer> offerListView;
    @FXML private ListView<Message> messageListView;
    @FXML private Label username;


    public HomePageViewController(){

    }

    public void reset(){
        getViewModelFactory().getHomePageViewModel().updateLists();
        this.username.setText(ViewState.getInstance().getUser().getUsername());
    }

    @Override
    protected void init() {
        reset();
        this.onlineUsers.setItems(getViewModelFactory().getHomePageViewModel().getOnlineUsers());
        this.offerListView.setItems(getViewModelFactory().getHomePageViewModel().getOffers());
    }

    @FXML
    public void openOffersList(){
        reset();
        getViewHandler().openView("offersList");
    }

    @FXML public void openDealsList(){
        getViewHandler().openView("dealsList");
    }

    @FXML
    public void openUserInterface(){
        User user=this.onlineUsers.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    @FXML public void openOfferInterface(){
        Offer offer=this.offerListView.getSelectionModel().getSelectedItem();
        if(offer!=null){
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    @FXML void openYourProfileInterface(){
        ViewState.getInstance().setDisplayedUser(ViewState.getInstance().getUser());
        getViewModelFactory().getUserViewViewModel().setUserInfo();
        getViewHandler().openView("userInterface");
    }
}
