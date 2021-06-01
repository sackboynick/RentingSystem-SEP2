package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.OfferModelInterface;
import model.Offer;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferModel implements OfferModelInterface
{
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
            + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
            + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
        DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
  }

  @Override public Offer createOffer(Offer offer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Offer(title,description,location,type,rooms_number,floor,price_per_month,deposit,area,available_date_from,landlord) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, offer.getTitle());
      statement.setString(2, offer.getDescription());
      statement.setString(3, offer.getLocation());
      statement.setString(4, offer.getType());
      statement.setInt(5, offer.getRoomsNumber());
      statement.setInt(6, offer.getFloor());
      statement.setDouble(7, offer.getPricePerMonth());
      statement.setDouble(8, offer.getDeposit());
      statement.setDouble(9, offer.getArea());
      statement.setDate(10, (Date) offer.getAvailableDate());
      statement.setObject(11, offer.getLandlord());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Offer(keys.getInt(1), offer.getTitle(),
            offer.getDescription(), offer.getPricePerMonth(),
            offer.getDeposit(), offer.getLocation(), offer.getType(),
            offer.getArea(), offer.getFloor(), offer.getRoomsNumber(),
            offer.getLandlord());
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public Offer readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month)");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");
        return new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
      }
      else
      {
        return null;
      }

    }
  }

  @Override public List<Offer> readByOfferTitle(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE title LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month)");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");

        Offer offer = new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
        offers.add(offer);
      }
      return offers;
    }
  }

  @Override public void updateOffer(Offer offer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE User SET  title=?, description=?, location=?, type=?, rooms_number=?, floor,price_per_month=?, deposit=?, area=?, available_date_from=?, landlord=? WHERE id = ?");
      statement.setString(1, offer.getTitle());
      statement.setString(2, offer.getDescription());
      statement.setString(3, offer.getLocation());
      statement.setString(4, offer.getType());
      statement.setInt(5, offer.getRoomsNumber());
      statement.setInt(6, offer.getFloor());
      statement.setDouble(7, offer.getPricePerMonth());
      statement.setDouble(8, offer.getDeposit());
      statement.setDouble(9, offer.getArea());
      statement.setDate(10, (Date) offer.getAvailableDate());
      statement.setObject(11, offer.getLandlord());
      statement.executeUpdate();
    }
  }

  @Override public void deleteOffer(Offer offer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM Offer WHERE id = ?");
      statement.setInt(1, offer.getID());
      statement.executeUpdate();
    }
  }

  @Override public List<Offer> getAllOffers() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer ");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String type = resultSet.getString("type");
        int floor = resultSet.getInt("floor");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        int noOfRooms = resultSet.getInt("deposit");
        double area = resultSet.getDouble("area");
        String username = resultSet.getString("landlord_username");
        PreparedStatement statement2 = connection
            .prepareStatement("SELECT * FROM User WHERE username=? ");
        ResultSet resultSet2 = statement2.executeQuery();
        statement2.setString(1, username);
        String password = resultSet2.getString("password");
        String firstName = resultSet2.getString("first_name");
        String middleName = resultSet2.getString("middle_name");
        String lastName = resultSet2.getString("last_name");
        String role = resultSet2.getString("role");
        long phone = resultSet2.getLong("phone");

        offers.add(
            new Offer(id, title, description, price, deposit, location, type,
                area, floor, noOfRooms,
                new User(username, firstName, middleName, lastName, password,
                    phone, role)));
      }
      return offers;
    }
  }

  @Override public List<Offer> returnPriceRange(double min, double max)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Offer WHERE price_per_month BETWEEN ? AND ? ");
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");

        Offer offer = new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
        offers.add(offer);
      }
      return offers;
    }
  }

  @Override public List<Offer> returnNumberOfRooms(int noOfRooms)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE rooms_number EQUAL ? ");
      statement.setInt(1, noOfRooms);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");

        Offer offer = new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
        offers.add(offer);
      }
      return offers;
    }
  }

  @Override public List<Offer> returnOfferType(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE type LIKE ? ");
      statement.setString(1, searchString);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");

        Offer offer = new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
        offers.add(offer);
      }
      return offers;
    }
  }

  @Override public List<Offer> returnOfferFloor(int searchInt)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE floor EQUAL ? ");
      statement.setInt(1, searchInt);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");

        Offer offer = new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
        offers.add(offer);
      }
      return offers;
    }
  }

  @Override public List<Offer> returnDeposit(double searchInt)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Offer WHERE deposit EQUAL ? ");
      statement.setDouble(1, searchInt);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Offer> offers = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        String type = resultSet.getString("type");
        int roomsNo = resultSet.getInt("rooms_number");
        int floor = resultSet.getInt("floor");
        double pricePerMonth = resultSet.getDouble("price_per_month");
        double deposit = resultSet.getDouble("deposit");
        double area = resultSet.getDouble("area");
        Date availableDateFrom = resultSet
            .getDate("available_date_from");//If possible modify the Offer class
        Object landlord = resultSet.getObject("landlord");

        Offer offer = new Offer(id, title, description, pricePerMonth, deposit,
            location, type, area, floor, roomsNo, (User) landlord);
        offers.add(offer);
      }
      return offers;
    }
  }


  @Override public List<Offer> returnCombinedFilter(double minPrice,
      double maxPrice, int noOfRooms, String type, int floor, double deposit)
      throws SQLException
  {
    ArrayList<Offer> offers = new ArrayList<>();

    offers.addAll(returnPriceRange(minPrice, maxPrice));
    offers.addAll(returnDeposit(deposit));
    offers.addAll(returnOfferFloor(floor));
    offers.addAll(returnNumberOfRooms(noOfRooms));
    offers.addAll(returnOfferType(type));
    return offers;
  }
}
