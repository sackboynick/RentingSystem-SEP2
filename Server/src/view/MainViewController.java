package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Offer;
import model.User;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the MainView view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class MainViewController extends ViewController{

    /**
     * Offers list.
     */
    @FXML
    public ListView<Offer> offersList;
    /**
     * Online users list.
     */
    @FXML
    private ListView<User> onlineUsersList;


    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init(){
        this.onlineUsersList.setItems(getViewModelFactory().getMainViewViewModel().getUsers());
        reset();
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){

        this.offersList.setItems(getViewModelFactory().getMainViewViewModel().getOffers());
    }

    /**
     * The method changes the view and displays the interface with the details of the selected user.
     */
    @FXML
    public void openUserInterface(){
        User user=this.onlineUsersList.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    /**
     * The method changes the view and displays the interface with the details of the selected offer.
     */
    @FXML public void openOfferInterface(){
        Offer offer=this.offersList.getSelectionModel().getSelectedItem();
        if(offer!=null){
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }

    /**
     * The method changes the view and displays the interface with the users list.
     */
    @FXML public void openUsersList(){
        getViewHandler().openView("usersList");
    }

    /**
     * The method changes the view and displays the interface with the offers list.
     */
    @FXML public void openOffersList(){
        getViewHandler().openView("offersList");
    }

    /**
     * The method changes the view and displays the interface with the online user list.
     */
    @FXML public void openServerOverview(){
        getViewHandler().openView("serverOverview");
    }

    /**
     * The method changes the view and display the interface with the renting list.
     */
    @FXML public void openRentingList(){
        getViewHandler().openView("rentingList");
    }
}
