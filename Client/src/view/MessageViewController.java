package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * JavaFX controller class for the messageView view.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class MessageViewController extends ViewController{
    @FXML private Label sender,body;
    @FXML
    private TextArea reply;


    /**
     * Overridden method from the ViewController abstract class that initializes the controller after its root element has been completely processed
     */
    @Override
    protected void init() {
        this.sender.textProperty().bindBidirectional(getViewModelFactory().getMessageViewViewModel().getSender());
        this.body.textProperty().bindBidirectional(getViewModelFactory().getMessageViewViewModel().getBody());
        this.reply.textProperty().bindBidirectional(getViewModelFactory().getMessageViewViewModel().getReplyBox());
        reset();
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){
        this.reply.setText("");
        getViewModelFactory().getMessageViewViewModel().updateData();
    }

    /**
     * The method delegates the ViewModel to send a message using the text from the JavaFX elements.
     */
    @FXML public void sendReply(){
        getViewModelFactory().getMessageViewViewModel().sendMessage();
        this.reply.setText("");
    }

    /**
     * The method changes the view and displays the HomePage view.
     */
    @FXML public void onBack(){
        getViewHandler().openView("homePage");
    }
}
