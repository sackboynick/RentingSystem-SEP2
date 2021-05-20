package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.User;
import viewmodel.ViewState;

public class HomePageViewController extends ViewController{
    @FXML
    private ListView<User> onlineUsers;


    public HomePageViewController(){

    }

    public void reset(){
        getViewModelFactory().getHomePageViewModel().updateLists();
    }

    @Override
    protected void init() {
        reset();
        this.onlineUsers.setItems(getViewModelFactory().getHomePageViewModel().getOnlineUsers());
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

    @FXML void openYourProfileInterface(){
        ViewState.getInstance().setDisplayedUser(ViewState.getInstance().getUser());
        getViewModelFactory().getUserViewViewModel().setUserInfo();
        getViewHandler().openView("userInterface");
    }
}
