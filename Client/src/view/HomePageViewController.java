package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Message;
import model.Offer;
import model.User;
import viewmodel.ViewState;
/**
 * JavaFX controller class for the homePageView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class HomePageViewController extends ViewController{
    @FXML
    private ListView<User> onlineUsers;
    @FXML private ListView<Offer> offerListView;
    @FXML private ListView<Message> messageListView;
    @FXML private Label username;


    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        getViewModelFactory().getHomePageViewModel().updateLists();
        this.onlineUsers.setItems(getViewModelFactory().getHomePageViewModel().getOnlineUsers());
        this.offerListView.setItems(getViewModelFactory().getHomePageViewModel().getOffers());
        this.messageListView.setItems(getViewModelFactory().getHomePageViewModel().getMessages());
        this.username.setText(ViewState.getInstance().getUser().getUsername());
    }

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        reset();
    }

    /**
     * The method changes the view and displays the offers list interface.
     */
    @FXML
    public void openOffersList(){
        reset();
        getViewHandler().openView("offersList");
    }

    /**
     * The method changes the view and displays the details of the user selected.
     */
    @FXML
    public void openUserInterface(){
        User user=this.onlineUsers.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    /**
     * The method changes the view and displays the details of the selected offer.
     */
    @FXML public void openOfferInterface(){
        Offer offer=this.offerListView.getSelectionModel().getSelectedItem();
        if(offer!=null){
            ViewState.getInstance().setOffer(offer);
            getViewHandler().openView("offerView");
        }
    }

    /**
     * The method changes the view and displays the details of the selected offer.
     */
    @FXML void openYourProfileInterface(){
        ViewState.getInstance().setDisplayedUser(ViewState.getInstance().getUser());
        getViewModelFactory().getUserViewViewModel().setUserInfo();
        getViewHandler().openView("userInterface");
    }

    /**
     * The method changes the view and displays the details of the selected message.
     */
    @FXML public void openMessage(){
        Message message=this.messageListView.getSelectionModel().getSelectedItem();
        if(message!=null){
            ViewState.getInstance().setDisplayedMessage(message);
            getViewHandler().openView("messageView");
        }
    }

    /**
     * The method changes the view and displays the renting deals list interface.
     */
    @FXML public void openRentingList(){
        getViewHandler().openView("rentingListView");
    }

    /**
     * The method changes the view and displays the send message interface.
     */
    @FXML void openSendMessageInterface(){
        getViewHandler().openView("sendMessageView");
    }

    /**
     * The method closes the view of the system.
     */
    @FXML public void exit(){
        getViewHandler().close();
    }
}
