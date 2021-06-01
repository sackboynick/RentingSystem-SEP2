package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.DealsModelInterface;
import model.Offer;
import model.Renting;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealsModel implements DealsModelInterface
{
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
            + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
            + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
        DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
  }

  @Override public Renting create(Renting rentingOffer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Deals(id,offer_title,decription,location,type,price_per_month,deposit,area,available_date_from, landlord_username, tenant_username) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, rentingOffer.getOffer().getTitle());
      statement.setString(2, rentingOffer.getOffer().getDescription());
      statement.setString(3, rentingOffer.getOffer().getLocation());
      statement.setString(4, rentingOffer.getOffer().getType());
      statement.setInt(5, rentingOffer.getOffer().getFloor());
      statement.setDouble(6, rentingOffer.getOffer().getPricePerMonth());
      statement.setDouble(7, rentingOffer.getOffer().getDeposit());
      statement.setDouble(8, rentingOffer.getOffer().getArea());
      statement.setDate(9, (Date) rentingOffer.getOffer().getAvailableDate());
      statement.setObject(10, rentingOffer.getLandlord().getUsername());
      statement.setObject(11, rentingOffer.getTenant().getUsername());
      statement.setString(12, rentingOffer.getTenantFeedback());
      statement.setString(13, rentingOffer.getLandlordFeedback());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Renting(rentingOffer.getTenant(), rentingOffer.getLandlord(),
            rentingOffer.getOffer());
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public Renting readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Deals WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String title = resultSet.getString("deal_title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int noOfRooms = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month)");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        String landlord_username = resultSet.getString("landlord_username");
        String tenant_username = resultSet.getString("tenant_username");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class

        PreparedStatement statement2 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet2 = statement2.executeQuery();
        statement2.setString(1, tenant_username);
        String password = resultSet2.getString("password");
        String firstName = resultSet2.getString("first_name");
        String middleName = resultSet2.getString("middle_name");
        String lastName = resultSet2.getString("last_name");
        String role = resultSet2.getString("role");
        long phone = resultSet2.getLong("phone");

        PreparedStatement statement3 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet3 = statement3.executeQuery();
        statement2.setString(1, landlord_username);
        String password1 = resultSet3.getString("password");
        String firstName1 = resultSet3.getString("first_name");
        String middleName1 = resultSet3.getString("middle_name");
        String lastName1 = resultSet3.getString("last_name");
        String role1 = resultSet3.getString("role");
        long phone1 = resultSet3.getLong("phone");

        return new Renting(
            new User(tenant_username, firstName, middleName, lastName, password,
                phone, role),
            new User(landlord_username, firstName1, middleName1, lastName1,
                password1, phone1, role1),
            new Offer(id, title, description, pricePerMonth, deposit, location,
                type, area, floor, noOfRooms,
                new User(landlord_username, firstName, middleName, lastName,
                    password, phone, role)));
      }
      else
      {
        return null;
      }

    }
  }

  @Override public List<Renting> readByName(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Deals WHERE deal_title LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Renting> deals = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int floor = resultSet.getInt("floor");

        double price = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        int noOfRooms = resultSet.getInt("deposit");
        double area = resultSet.getDouble("area");
        String landlord_username = resultSet.getString("landlord_username");
        String tenant_username = resultSet.getString("tenant_username");

        PreparedStatement statement2 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet2 = statement2.executeQuery();
        statement2.setString(1, tenant_username);
        String password = resultSet2.getString("password");
        String firstName = resultSet2.getString("first_name");
        String middleName = resultSet2.getString("middle_name");
        String lastName = resultSet2.getString("last_name");
        String role = resultSet2.getString("role");
        long phone = resultSet2.getLong("phone");

        PreparedStatement statement3 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet3 = statement3.executeQuery();
        statement2.setString(1, landlord_username);
        String password1 = resultSet3.getString("password");
        String firstName1 = resultSet3.getString("first_name");
        String middleName1 = resultSet3.getString("middle_name");
        String lastName1 = resultSet3.getString("last_name");
        String role1 = resultSet3.getString("role");
        long phone1 = resultSet3.getLong("phone");

        deals.add(new Renting(
            new User(tenant_username, firstName, middleName, lastName, password,
                phone, role),
            new User(landlord_username, firstName1, middleName1, lastName1,
                password1, phone1, role1),
            new Offer(id, searchString, description, price, deposit, location,
                type, area, floor, noOfRooms,
                new User(landlord_username, firstName, middleName, lastName,
                    password, phone, role))));
      }
      return deals;
    }
  }

  @Override public void update(Renting dealOffer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE Deals SET offer_title = ?, landlord_username = ?, tenant_username = ? WHERE offer_title =? ,landlord_username=?,tenant_username=?");
      statement.setObject(1, dealOffer.getOffer().getTitle());
      statement.setObject(2, dealOffer.getLandlord().getUsername());
      statement.setObject(3, dealOffer.getTenant().getUsername());

      statement.setObject(4, dealOffer.getOffer().getTitle());
      statement.setObject(5, dealOffer.getLandlord().getUsername());
      statement.setObject(6, dealOffer.getTenant().getUsername());
      statement.executeUpdate();
    }

  }

  @Override public void delete(Renting dealOffer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM Deals WHERE offer_title = ? AND landlord_username = ? AND tenant_username = ?");
      statement.setObject(1, dealOffer.getOffer());
      statement.setObject(2, dealOffer.getLandlord());
      statement.setObject(3, dealOffer.getTenant());
      statement.executeUpdate();
    }
  }

  @Override public List<Renting> getAllDeals() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Deals ");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Renting> rentings = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("deal_title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int floor = resultSet.getInt("floor");

        double price = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        int noOfRooms = resultSet.getInt("deposit");
        double area = resultSet.getDouble("area");
        String landlord_username = resultSet.getString("landlord_username");
        String tenant_username = resultSet.getString("tenant_username");

        PreparedStatement statement2 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet2 = statement2.executeQuery();
        statement2.setString(1, tenant_username);
        String password = resultSet2.getString("password");
        String firstName = resultSet2.getString("first_name");
        String middleName = resultSet2.getString("middle_name");
        String lastName = resultSet2.getString("last_name");
        String role = resultSet2.getString("role");
        long phone = resultSet2.getLong("phone");

        PreparedStatement statement3 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet3 = statement3.executeQuery();
        statement2.setString(1, tenant_username);
        String password1 = resultSet3.getString("password");
        String firstName1 = resultSet3.getString("first_name");
        String middleName1 = resultSet3.getString("middle_name");
        String lastName1 = resultSet3.getString("last_name");
        String role1 = resultSet3.getString("role");
        long phone1 = resultSet3.getLong("phone");

        rentings.add(new Renting(
            new User(tenant_username, firstName, middleName, lastName, password,
                phone, role),
            new User(landlord_username, firstName1, middleName1, lastName1,
                password1, phone1, role1),
            new Offer(id, title, description, price, deposit, location, type,
                area, floor, noOfRooms,
                new User(landlord_username, firstName, middleName, lastName,
                    password, phone, role))));
      }
      return rentings;
    }
  }
}
