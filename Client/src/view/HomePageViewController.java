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
        this.onlineUsers.setItems(getViewModelFactory().getHomePageViewModel().getOnlineUsers());
    }
    @Override
    protected void init() {
        reset();
    }

    @FXML
    public void openOffersList(){
        getViewHandler().openView("offersList");
    }

    @FXML
    public void openUserInterface(){
        User user=this.onlineUsers.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewHandler().openView("userInterface");
        }
    }
}
