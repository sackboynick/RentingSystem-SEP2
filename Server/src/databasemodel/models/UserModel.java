package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.UserModelInterface;
import model.Message;
import model.RentingList;
import model.User;
import model.UserList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements UserModelInterface {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
                        + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
                        + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
                DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
    }

    @Override
    public User createUser(User user) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO User(username,password,first_name,middle_name,last_name,role,phone) VALUES (?,?,?,?,?,?,?)");
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

    @Override
    public User readByUsername(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM User WHERE username = ?");
            statement.setString(1, "%" + username + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username1 = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String middleName = resultSet.getString("middle_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("role");
                long phone = resultSet.getLong("phone");
                return createUser(
                        new User(username1, firstName, middleName, lastName, password, phone,
                                role));
            } else {
                return null;
            }
        }
    }

    @Override
    public List<User> readByName(String firstName) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM User WHERE first_name  = ?");
            statement.setString(1, "%" + firstName + "%");
            ResultSet rs = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            User currentUser = null;
            while (rs.next()) {
                String fn = rs.getString("first_name");
                if (currentUser == null || fn != currentUser.getFirstName()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String firstName1 = rs.getString("first_name");
                    String middleName = rs.getString("middle_name");
                    String lastName = rs.getString("last_name");
                    String role = rs.getString("role");
                    long phone = rs.getLong("phone");
                    currentUser = new User(username, firstName1, middleName, lastName,
                            password, phone, role);
                    users.add(currentUser);
                }
            }
            return users;
        }
    }

    @Override
    public UserList getAllUsers() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM User ");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
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
            UserList userList = new UserList();
            for (int i = 0; i < users.size(); i++) {
                userList.addUser((users.get(i)));
            }
            return userList;
        }

    }

    @Override
    public ArrayList<String> getAllMessagesByUsername(String username)
            throws SQLException {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Message WHERE sender_username  = ? OR receiver_username=?");
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> messages = new ArrayList<>();
            while (resultSet.next()) {
                messages.add(resultSet.getString("body_message"));
            }
            return messages;
        }

    }


    @Override
    public void update(User user) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE User SET username = ?, password = ?, first_name = ?, middle_name = ?, last_name = ?, role = ?, phone = ? WHERE username = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getMiddleName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getRole());
            statement.setLong(7, user.getPhone());
            statement.setString(8, user.getUsername());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM User WHERE username = ?");
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        }
    }
}
