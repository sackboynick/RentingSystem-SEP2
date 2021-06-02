package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.MessageInterface;
import model.Message;
import model.Offer;
import model.Renting;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * A class  used to add, delete, retrieve messages from database.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class MessageModel implements MessageInterface {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
                        + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
                        + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
                DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
    }
    @Override
    public void createMessage(User user, String receiver, String body) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Message(sender_username,receiver_username,body_message) VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, receiver);
            statement.setString(2, body);
            statement.executeUpdate();

        }
    }

    @Override
    public List<String> getConversationByUsernames(String senderName, String receiver) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT body_message FROM Message WHERE sender_username = ? AND receiver_username=?");
            statement.setString(1, senderName);
            statement.setString(1, receiver);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> messages = new ArrayList<>();
            while (resultSet.next()) {

                messages.add(resultSet.getString("body_message"));

            }
        return messages;
        }
    }

    @Override
    public void delete(Message message) throws SQLException {

    }

    @Override
    public List<Message> getAllMessages() throws SQLException {
        return null;
    }
}
