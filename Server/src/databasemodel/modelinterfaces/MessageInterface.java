package databasemodel.modelinterfaces;

import model.Renting;

import java.sql.SQLException;
import java.util.List;

import model.Message;
import model.User;

public interface MessageInterface {
    void createMessage(User user, String receiver, String body) throws SQLException;

    List<String> getConversationByUsernames(String senderName, String receiver) throws SQLException;

}
