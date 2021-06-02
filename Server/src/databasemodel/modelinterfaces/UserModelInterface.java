package databasemodel.modelinterfaces;

import model.Message;
import model.User;
import model.UserList;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserModelInterface
{
  User createUser(User user) throws SQLException;
  User readByUsername(String username) throws SQLException;
  List<User> readByName(String firstName) throws SQLException;
  UserList getAllUsers() throws SQLException;
  ArrayList<String> getAllMessagesByUsername(String username) throws SQLException;
  void update(User user) throws SQLException;
  void delete(User user) throws SQLException;
}
