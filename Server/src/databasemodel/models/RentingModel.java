package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.RentingModelInterface;
import model.Offer;
import model.Renting;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentingModel implements RentingModelInterface
{
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
            + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
            + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
        DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
  }

  @Override public Renting createRentingOffer(Renting renting)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Renting(offer, landlord, tenant, tenant_feedback, landlord_feedback) VALUES (?,?,?,?,?)");
      statement.setObject(1, renting.getOffer());
      statement.setObject(2, renting.getLandlord());
      statement.setObject(3, renting.getTenant());
      statement.setString(4, renting.getTenantFeedback());
      statement.setString(5, renting.getLandlordFeedback());
      statement.executeUpdate();
    }
    return new Renting(renting.getTenant(), renting.getLandlord(),
        renting.getOffer());
  }

  @Override public Renting readById(Offer offerInput, User landlordInput,
      User tenantInput) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Renting WHERE offer = ? AND landlord=? AND tenant=?");
      statement.setObject(1, offerInput);
      statement.setObject(2, landlordInput);
      statement.setObject(3, tenantInput);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        User tenant = (User) resultSet.getObject("tenant");
        User landlord = (User) resultSet.getObject("landlord");
        Offer offer = (Offer) resultSet.getObject("offer");
        return new Renting(tenant, landlord, offer);
      }
      else
      {
        return null;
      }

    }
  }

  @Override public List<Renting> getAllRentings() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Renting ");
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

  @Override public List<Renting> readByRentingName(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE title LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Renting> offers = new ArrayList<>();
      while (resultSet.next())
      {
        Object offer = resultSet.getObject("offer");
        Object landlord = resultSet.getObject("landlord");
        Object tenant = resultSet.getObject("tenant");

        offers.add(new Renting((User) tenant, (User) landlord, (Offer) offer));
      }
      return offers;
    }
  }

  @Override public void updateRenting(Renting renting) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE User SET offer = ?, landlord = ?, tenant = ?, tenant_feedback = ?, landlord_feedback = ? WHERE offer = ?,landlord=?,tenant=?");
      statement.setObject(1, renting.getOffer());
      statement.setObject(2, renting.getLandlord());
      statement.setObject(3, renting.getTenant());
      statement.setString(5, renting.getTenantFeedback());
      statement.setString(6, renting.getLandlordFeedback());

      statement.setObject(7, renting.getOffer());
      statement.setObject(8, renting.getLandlord());
      statement.setObject(9, renting.getTenant());
      statement.executeUpdate();
    }
  }

  @Override public void deleteRenting(Renting renting) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM Renting WHERE offer = ? AND landlord = ? AND tenant = ?");
      statement.setObject(1, renting.getOffer());
      statement.setObject(2, renting.getLandlord());
      statement.setObject(3, renting.getTenant());
      statement.executeUpdate();
    }
  }
}
