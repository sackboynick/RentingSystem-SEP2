package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageViewModel implements PropertyChangeListener {
    private final Model model;
    private final ObservableList<User> onlineUsers;

    public HomePageViewModel(Model model){
        this.model=model;
        this.model.addListener("OnlineUsers",this);
        this.onlineUsers=getOnlineUsersFromModel();
    }

    public ObservableList<User> getOnlineUsersFromModel(){
        return FXCollections.observableArrayList(model.getUsersOnline().getUsers());
    }

    public ObservableList<User> getOnlineUsers() {
        return this.onlineUsers;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> this.onlineUsers.add(0, (User) evt.getNewValue()));
    }
}
