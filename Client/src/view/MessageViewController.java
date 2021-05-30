package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MessageViewController extends ViewController{
    @FXML private Label sender,body;
    @FXML
    private TextArea reply;


    @Override
    protected void init() {
        this.sender.textProperty().bindBidirectional(getViewModelFactory().getMessageViewViewModel().getSender());
        this.body.textProperty().bindBidirectional(getViewModelFactory().getMessageViewViewModel().getBody());
        this.reply.textProperty().bindBidirectional(getViewModelFactory().getMessageViewViewModel().getReplyBox());
        reset();
    }

    public void reset(){
        this.reply.setText("");
        getViewModelFactory().getMessageViewViewModel().updateData();
    }

    @FXML public void sendReply(){
        getViewModelFactory().getMessageViewViewModel().sendMessage();
        this.reply.setText("");
    }

    @FXML public void onBack(){
        getViewHandler().openView("homePage");
    }
}
