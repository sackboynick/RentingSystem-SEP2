package databasemodel.modelinterfaces;

import model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * An interface for classes used to add, delete, retrieve messages from database.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface MessageInterface {

    /**
     * The method adds a message to the database Message schema.
     *
     * @param user The offer to be added.
     * @param receiver The offer to be added.
     * @param body The offer to be added.
     * @throws SQLException if there any problems with the database
     */
    void createMessage(User user, String receiver, String body) throws SQLException;

    /**
     * The method returns the  conversation between two users.
     *
     * @param senderName The sender username.
     * @param receiver The receiver username.
     * @return A conversation between two users
     * @throws SQLException if there any problems with the database
     *
     */
    List<String> getConversationByUsernames(String senderName, String receiver) throws SQLException;

}
