package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.OfferModelInterface;
import model.Offer;
import model.OfferList;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * A class  used to add, delete, retrieve Offer objects from database.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class OfferModel implements OfferModelInterface {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
                        + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
                        + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
                DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
    }

    @Override
    public Offer createOffer(Offer offer) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Offer(id,title,description,location,type,floor,price_per_month,deposit,area, number_of_rooms,available_date_from,landlord_username) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(2, offer.getTitle());
            statement.setString(3, offer.getDescription());
            statement.setString(4, offer.getLocation());
            statement.setString(5, offer.getType());
            statement.setInt(6,  offer.getFloor());
            statement.setDouble(7, offer.getPricePerMonth());
            statement.setDouble(8, offer.getDeposit());
            statement.setDouble(9, offer.getArea());
            statement.setDouble(10, offer.getRoomsNumber());
            statement.setString(11, offer.getLandlord().getUsername());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            statement.setInt(1, keys.getInt(1));// may threw exception
            if (keys.next()) {
                return new Offer(offer.getTitle(),
                        offer.getDescription(), offer.getPricePerMonth(),
                        offer.getDeposit(), offer.getLocation(), offer.getType(),
                        offer.getArea(), offer.getFloor(), offer.getRoomsNumber(),
                        offer.getLandlord());
            } else {
                throw new SQLException("No keys generated");
            }
        }
    }

    @Override
    public Offer readById(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int floor = resultSet.getInt("floor");
                int roomsNo = resultSet.getInt("rooms_number");

                double pricePerMonth = resultSet.getDouble("price_per_month)");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                String landlordUsername = resultSet.getString("landlord_username");

                PreparedStatement statement2 = connection
                        .prepareStatement("SELECT * FROM User WHERE username=? ");
                ResultSet resultSet2 = statement2.executeQuery();
                statement2.setString(1, landlordUsername);
                String password = resultSet2.getString("password");
                String firstName = resultSet2.getString("first_name");
                String middleName = resultSet2.getString("middle_name");
                String lastName = resultSet2.getString("last_name");
                String role = resultSet2.getString("role");
                long phone = resultSet2.getLong("phone");
                return new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, new User(landlordUsername, firstName, middleName, lastName, password,
                        phone, role));
            } else {
                return null;
            }

        }
    }

    @Override
    public List<Offer> readByOfferTitle(String searchString)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer WHERE title LIKE ?");
            statement.setString(1, "%" + searchString + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int roomsNo = resultSet.getInt("rooms_number");
                int floor = resultSet.getInt("floor");
                double pricePerMonth = resultSet.getDouble("price_per_month)");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                Object landlord = resultSet.getObject("landlord");
                Offer offer = new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, (User) landlord);
                offers.add(offer);
            }
            return offers;
        }
    }

    @Override
    public void updateOffer(Offer offer) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE User SET  title=?, description=?, location=?, type=?,floor=?, price_per_month=?, deposit=?, area=?,number_of_rooms=?, landlord_username=? WHERE id = ?");
            statement.setString(1, offer.getTitle());
            statement.setString(2, offer.getDescription());
            statement.setString(3, offer.getLocation());
            statement.setString(4, offer.getType());
            statement.setInt(5, offer.getFloor());
            statement.setDouble(6, offer.getPricePerMonth());
            statement.setDouble(7, offer.getDeposit());
            statement.setDouble(8, offer.getArea());
            statement.setInt(9, offer.getRoomsNumber());
            statement.setString(10, offer.getLandlord().getUsername());
            statement.setInt(11, offer.getID());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteOffer(Offer offer) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM Offer WHERE id = ?");
            statement.setInt(1, offer.getID());
            statement.executeUpdate();
        }
    }

    @Override
    public OfferList getAllOffers() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer ");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                int floor = resultSet.getInt("floor");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price_per_month");
                double deposit = resultSet.getDouble("deposit");
                int noOfRooms = resultSet.getInt("number_of_rooms");
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
                        new Offer(title, description, price, deposit, location, type,
                                area, floor, noOfRooms,
                                new User(username, firstName, middleName, lastName, password,
                                        phone, role)));
            }

            OfferList offerList = new OfferList();
            for (int i = 0; i < offers.size(); i++) {
            offerList.addOffer(offers.get(i));
            }
            return offerList;
        }
    }

    @Override
    public List<Offer> returnPriceRange(double min, double max)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Offer WHERE price_per_month BETWEEN ? AND ? ");
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int roomsNo = resultSet.getInt("number_of_rooms");
                int floor = resultSet.getInt("floor");
                double pricePerMonth = resultSet.getDouble("price_per_month");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                String landlordUsername = resultSet.getString("landlord_username");

                PreparedStatement statement2 = connection
                        .prepareStatement("SELECT * FROM User WHERE username=? ");
                ResultSet resultSet2 = statement2.executeQuery();
                statement2.setString(1, landlordUsername);
                String password = resultSet2.getString("password");
                String firstName = resultSet2.getString("first_name");
                String middleName = resultSet2.getString("middle_name");
                String lastName = resultSet2.getString("last_name");
                String role = resultSet2.getString("role");
                long phone = resultSet2.getLong("phone");
                Offer offer = new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, new User(landlordUsername, firstName, middleName, lastName, password,
                        phone, role));
                offers.add(offer);
            }
            return offers;
        }
    }

    @Override
    public List<Offer> returnNumberOfRooms(int noOfRooms)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer WHERE rooms_number EQUAL ? ");
            statement.setInt(1, noOfRooms);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int roomsNo = resultSet.getInt("number_of_rooms");
                int floor = resultSet.getInt("floor");
                double pricePerMonth = resultSet.getDouble("price_per_month");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                String landlordUsername = resultSet.getString("landlord_username");

                PreparedStatement statement2 = connection
                        .prepareStatement("SELECT * FROM User WHERE username=? ");
                ResultSet resultSet2 = statement2.executeQuery();
                statement2.setString(1, landlordUsername);
                String password = resultSet2.getString("password");
                String firstName = resultSet2.getString("first_name");
                String middleName = resultSet2.getString("middle_name");
                String lastName = resultSet2.getString("last_name");
                String role = resultSet2.getString("role");
                long phone = resultSet2.getLong("phone");
                Offer offer = new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, new User(landlordUsername, firstName, middleName, lastName, password,
                        phone, role));
                offers.add(offer);
            }
            return offers;
        }
    }

    @Override
    public List<Offer> returnOfferType(String searchString)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer WHERE type LIKE ? ");
            statement.setString(1, searchString);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int roomsNo = resultSet.getInt("number_of_rooms");
                int floor = resultSet.getInt("floor");
                double pricePerMonth = resultSet.getDouble("price_per_month");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                String landlordUsername = resultSet.getString("landlord_username");

                PreparedStatement statement2 = connection
                        .prepareStatement("SELECT * FROM User WHERE username=? ");
                ResultSet resultSet2 = statement2.executeQuery();
                statement2.setString(1, landlordUsername);
                String password = resultSet2.getString("password");
                String firstName = resultSet2.getString("first_name");
                String middleName = resultSet2.getString("middle_name");
                String lastName = resultSet2.getString("last_name");
                String role = resultSet2.getString("role");
                long phone = resultSet2.getLong("phone");
                Offer offer = new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, new User(landlordUsername, firstName, middleName, lastName, password,
                        phone, role));
                offers.add(offer);
            }
            return offers;
        }
    }

    @Override
    public List<Offer> returnOfferFloor(int searchInt)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer WHERE floor EQUAL ? ");
            statement.setInt(1, searchInt);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int roomsNo = resultSet.getInt("number_of_rooms");
                int floor = resultSet.getInt("floor");
                double pricePerMonth = resultSet.getDouble("price_per_month");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                String landlordUsername = resultSet.getString("landlord_username");

                PreparedStatement statement2 = connection
                        .prepareStatement("SELECT * FROM User WHERE username=? ");
                ResultSet resultSet2 = statement2.executeQuery();
                statement2.setString(1, landlordUsername);
                String password = resultSet2.getString("password");
                String firstName = resultSet2.getString("first_name");
                String middleName = resultSet2.getString("middle_name");
                String lastName = resultSet2.getString("last_name");
                String role = resultSet2.getString("role");
                long phone = resultSet2.getLong("phone");
                Offer offer = new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, new User(landlordUsername, firstName, middleName, lastName, password,
                        phone, role));
                offers.add(offer);
            }
            return offers;
        }
    }

    @Override
    public List<Offer> returnDeposit(double searchInt)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Offer WHERE deposit EQUAL ? ");
            statement.setDouble(1, searchInt);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                int roomsNo = resultSet.getInt("number_of_rooms");
                int floor = resultSet.getInt("floor");
                double pricePerMonth = resultSet.getDouble("price_per_month");
                double deposit = resultSet.getDouble("deposit");
                double area = resultSet.getDouble("area");
                String landlordUsername = resultSet.getString("landlord_username");

                PreparedStatement statement2 = connection
                        .prepareStatement("SELECT * FROM User WHERE username=? ");
                ResultSet resultSet2 = statement2.executeQuery();
                statement2.setString(1, landlordUsername);
                String password = resultSet2.getString("password");
                String firstName = resultSet2.getString("first_name");
                String middleName = resultSet2.getString("middle_name");
                String lastName = resultSet2.getString("last_name");
                String role = resultSet2.getString("role");
                long phone = resultSet2.getLong("phone");
                Offer offer = new Offer(title, description, pricePerMonth, deposit,
                        location, type, area, floor, roomsNo, new User(landlordUsername, firstName, middleName, lastName, password,
                        phone, role));
                offers.add(offer);
            }
            return offers;
        }
    }


    @Override
    public ArrayList<Offer> returnCombinedFilter(double minPrice,
                                                        double maxPrice, int noOfRooms, String type, int floor, double deposit)
            throws SQLException {
        ArrayList<Offer> offers = new ArrayList<>();

        offers.addAll(returnPriceRange(minPrice, maxPrice));
        offers.addAll(returnDeposit(deposit));
        offers.addAll(returnOfferFloor(floor));
        offers.addAll(returnNumberOfRooms(noOfRooms));
        offers.addAll(returnOfferType(type));
        return offers;
    }
}
