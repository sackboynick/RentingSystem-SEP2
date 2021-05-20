package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;
import viewmodel.ViewState;

public class UsersListViewController extends ViewController{
    @FXML
    private TableView<User> userTableView;
    @FXML private TableColumn<User,String> username,firstName,lastName,role;
    @FXML private Label numberOfUsers;

    @Override
    protected void init() {
        this.userTableView.setItems(getViewModelFactory().getUsersListViewModel().getUsers());
        this.username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        this.firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        this.lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        this.role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        this.numberOfUsers.textProperty().bind(getViewModelFactory().getUsersListViewModel().getNumberOfUsers());
        reset();
    }
    public void reset(){
        this.userTableView.setItems(getViewModelFactory().getUsersListViewModel().getUsers());
    }

    @FXML public void deleteUser(){
            User user=this.userTableView.getSelectionModel().getSelectedItem();
            if(user!=null) {
                getViewModelFactory().getUsersListViewModel().removeUser(user);
            }
    }
    @FXML
    public void openUserInterface(){
        User user=this.userTableView.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }

}
