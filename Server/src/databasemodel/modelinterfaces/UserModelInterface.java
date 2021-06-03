package databasemodel.modelinterfaces;

import model.User;
import model.UserList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to create User objects getting them from a database in SQL.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface UserModelInterface
{
  /**
   * The method adds a User object to the database User schema.
   *
   * @param user The user to be added.
   * @return The User object.
   * @throws SQLException if there any problems with the database
   */
  User createUser(User user) throws SQLException;

  /**
   * The method returns a User object from the database User schema.
   *
   * @param username The user to be returned.
   * @return The User object.
   * @throws SQLException if there any problems with the database
   */
  User readByUsername(String username) throws SQLException;

  /**
   * The method returns a User object from the database User schema.
   *
   * @param firstName The user First Name to be returned.
   * @return The User object.
   * @throws SQLException if there any problems with the database
   */
  List<User> readByName(String firstName) throws SQLException;

  /**
   * The method returns a list with object from the database User schema.
   *
   * @return A User list with all the users from the database.
   * @throws SQLException if there any problems with the database
   */
  UserList getAllUsers() throws SQLException;

  /**
   * The method returns a list with object from the database User schema.
   *
   * @param username The user with the same username to be returned
   * @return A User list with all the users with the same username.
   * @throws SQLException if there any problems with the database
   */
  ArrayList<String> getAllMessagesByUsername(String username) throws SQLException;

  /**
   * The method updates a user in the database User schema.
   *
   * @param user The user to be updated
   * @throws SQLException if there any problems with the database
   */
  void update(User user) throws SQLException;

  /**
   * The method deletes a user object from the database User schema.
   *
   * @param user The user to be deleted
   * @throws SQLException if there any problems with the database
   */
  void delete(User user) throws SQLException;
}
