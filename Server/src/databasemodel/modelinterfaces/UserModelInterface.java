package databasemodel.modelinterfaces;

import model.Message;
import model.User;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

public interface UserModelInterface
{
  User createUser(User user) throws SQLException;
  User readByUsername(String username) throws SQLException;
  List<User> readByName(String firstName) throws SQLException;
  List<User> getAllUsers() throws SQLException;
  Array getAllMessagesByUsername(String username) throws SQLException;
  void updateMessages(Message message) throws SQLException;
  void update(User user) throws SQLException;
  void delete(User user) throws SQLException;
}
