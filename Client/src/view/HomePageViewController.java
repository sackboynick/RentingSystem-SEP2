package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML private Button userOffersButton;


    public HomePageViewController(){

    }

    public void reset(){
        getViewModelFactory().getHomePageViewModel().updateLists();
        this.username.setText(ViewState.getInstance().getUser().getUsername());
        if(!ViewState.getInstance().getUser().getRole().equals("Tenant"))
            userOffersButton.setVisible(false);
    }

    @Override
    protected void init() {
        reset();
        this.onlineUsers.setItems(getViewModelFactory().getHomePageViewModel().getOnlineUsers());
        this.offerListView.setItems(getViewModelFactory().getHomePageViewModel().getOffers());
        this.messageListView.setItems(getViewModelFactory().getHomePageViewModel().getMessages());
    }

    @FXML
    public void openOffersList(){
        reset();
        getViewHandler().openView("offersList");
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
            getViewHandler().openView("offerView");
        }
    }

    @FXML void openYourProfileInterface(){
        ViewState.getInstance().setDisplayedUser(ViewState.getInstance().getUser());
        getViewModelFactory().getUserViewViewModel().setUserInfo();
        getViewHandler().openView("userInterface");
    }

    @FXML void openUserOfferList(){
        getViewHandler().openView("userOffersList");
    }

    @FXML public void openRentingList(){
        getViewHandler().openView("rentingListView");
    }

    @FXML void openSendMessageInterface(){
        getViewHandler().openView("sendMessageView");
    }
}
