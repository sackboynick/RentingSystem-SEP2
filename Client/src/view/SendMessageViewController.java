package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import viewmodel.ViewState;

/**
 * JavaFX controller class for the sendMessageView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class SendMessageViewController extends ViewController{
    @FXML private TableView<User> userTableView;
    @FXML private TableColumn<User,String> username,role;
    @FXML
    private TextField receiver;
    @FXML private TextArea body;
    @FXML private Label error;

    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        this.userTableView.setItems(getViewModelFactory().getSendMessageViewModel().getOnlineUsers());
        this.username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        this.role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        this.error.textProperty().bindBidirectional(getViewModelFactory().getSendMessageViewModel().getError());
        this.receiver.textProperty().bindBidirectional(getViewModelFactory().getSendMessageViewModel().getReceiver());
        this.body.textProperty().bindBidirectional(getViewModelFactory().getSendMessageViewModel().getBody());
        reset();
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        this.userTableView.setItems(getViewModelFactory().getSendMessageViewModel().getOnlineUsers());
        this.receiver.setText("");
        this.body.setText("");
        this.error.setText("");
    }

    /**
     * The method delegates the ViewModel class to send a message to the inserted user.
     */
    @FXML
    public void sendMessage(){
        getViewModelFactory().getSendMessageViewModel().sendMessage();
        reset();
    }

    /**
     * The method sets the name of the receiver of the message with the username of the user selected from the list.
     */
    @FXML public void selectUser(){
            User user=this.userTableView.getSelectionModel().getSelectedItem();
            if(user!=null) {
                receiver.setText(user.getUsername());
                this.error.setText("");
            }
            else
                this.error.setText("Select an user first!");
    }

    /**
     * The method changes the view and displays the HomePage view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("homePage");
    }
}
