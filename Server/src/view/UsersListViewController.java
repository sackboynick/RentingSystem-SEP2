package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the ServerUserView view.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class UsersListViewController extends ViewController{
    @FXML
    private TableView<User> userTableView;
    @FXML private TableColumn<User,String> username,firstName,lastName,role;
    @FXML private Label numberOfUsers;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
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
    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        this.userTableView.setItems(getViewModelFactory().getUsersListViewModel().getUsers());
    }

    /**
     * The method deletes permanently the user selected from every list in the system.
     */
    @FXML public void deleteUser(){
            User user=this.userTableView.getSelectionModel().getSelectedItem();
            if(user!=null) {
                getViewModelFactory().getUsersListViewModel().removeUser(user);
            }
    }
    /**
     * The method changes the view and displays the interface with the details of the selected user.
     */
    @FXML
    public void openUserInterface(){
        User user=this.userTableView.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    /**
     * The method changes the view and displays the main view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("mainView");
    }

}
