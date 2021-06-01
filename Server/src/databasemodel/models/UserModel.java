package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.UserModelInterface;
import model.Message;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements UserModelInterface
{

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
            + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
            + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
        DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
  }

  @Override public User createUser(User user) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO User(username,password,first_name,middle_name,last_name,role,phone,messages_and_requests) VALUES (?,?,?,?,?,?,?,?)");//add messages and requests
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFirstName());
      statement.setString(4, user.getMiddleName());
      statement.setString(5, user.getLastName());
      statement.setString(6, user.getRole());
      statement.setLong(7, user.getPhone());
      statement.setArray(8, (Array) user.getMessagesAndRequests());
      statement.executeUpdate();
    }
    return user;
  }

  @Override public User readByUsername(String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User WHERE username = ?");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String middleName = resultSet.getString("middle_name");
        String lastName = resultSet.getString("last_name");
        String role = resultSet.getString("role");
        long phone = resultSet.getLong("phone");
        return createUser(
            new User(username, firstName, middleName, lastName, password, phone,
                role));
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<User> readByName(String firstName) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User WHERE first_name  = ?");
      statement.setString(1, firstName);
      ResultSet rs = statement.executeQuery();
      ArrayList<User> users = new ArrayList<>();
      User currentUser = null;
      while (rs.next())
      {
        String fn = rs.getString("first_name");
        if (currentUser == null || fn != currentUser.getFirstName())
        {
          String username = rs.getString("username");
          String password = rs.getString("password");
          String middleName = rs.getString("middle_name");
          String lastName = rs.getString("last_name");
          String role = rs.getString("role");
          long phone = rs.getLong("phone");
          currentUser = new User(username, firstName, middleName, lastName,
              password, phone, role);
          users.add(currentUser);
        }
      }
      return users;
    }
  }

  @Override public List<User> getAllUsers() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User ");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<User> users = new ArrayList<>();
      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String middleName = resultSet.getString("middle_name");
        String lastName = resultSet.getString("last_name");
        String role = resultSet.getString("role");
        long phone = resultSet.getLong("phone");
        users.add(
            new User(username, firstName, middleName, lastName, password, phone,
                role));
      }
      return users;
    }

  }

  @Override public Array getAllMessagesByUsername(String username)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {

      PreparedStatement statement = connection.prepareStatement(
          "SELECT messages_and_requests FROM User WHERE username  = ?");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getArray("messages_and_requests");
      }
      else
      {
        return null;
      }
    }

  }

  @Override public void updateMessages(Message message) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO User(messages_and_requests) VALUES (?) WHERE username=?");//add messages and requests
     // statement.setArray(1, (Array) );
   //   statement.setString(2,message.);
      statement.executeUpdate();
    }
  }

  @Override public void update(User user) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE User SET username = ?, password = ?, first_name = ?, middle_name = ?, last_name = ?, role = ?, phone = ?, messages_and_requests = ? WHERE username = ?");
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFirstName());
      statement.setString(4, user.getMiddleName());
      statement.setString(5, user.getLastName());
      statement.setString(6, user.getRole());
      statement.setLong(7, user.getPhone());
      statement.setArray(8, (Array) user.getMessagesAndRequests());
      statement.setString(9, user.getUsername());
      statement.executeUpdate();
    }
  }

  @Override public void delete(User user) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM User WHERE username = ?");
      statement.setString(1, user.getUsername());
      statement.executeUpdate();
    }
  }
}
