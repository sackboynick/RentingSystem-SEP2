package databasemodel.models;

import databasemodel.DatabaseVariables;
import databasemodel.modelinterfaces.RentingModelInterface;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * A class  used to add, delete, retrieve data from closed deals  from database.
 *
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class RentingModel implements RentingModelInterface {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + DatabaseVariables.HOST + ":"
                        + DatabaseVariables.PORT + "/" + DatabaseVariables.NAME + "?"
                        + "currentSchema=" + DatabaseVariables.SCHEMA_NAME,
                DatabaseVariables.NAME, DatabaseVariables.PASSWORD);
    }

    @Override
    public Renting createRentingOffer(Renting renting)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Renting(id,renting_title,description,location,type,floor,price_per_month,deposit,area, landlord_username, tenant_username) VALUES (?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(2, renting.getOffer().getTitle());
            statement.setString(3, renting.getOffer().getDescription());
            statement.setString(4, renting.getOffer().getLocation());
            statement.setString(5, renting.getOffer().getType());
            statement.setInt(6, renting.getOffer().getFloor());
            statement.setDouble(7, renting.getOffer().getPricePerMonth());
            statement.setDouble(8, renting.getOffer().getDeposit());
            statement.setDouble(9, renting.getOffer().getArea());
            statement.setString(10, renting.getLandlord().getUsername());
            statement.setString(11, renting.getTenant().getUsername());
            ResultSet keys = statement.getGeneratedKeys();
            statement.setInt(1, keys.getInt(1));//may threw errors
            if (keys.next()) {
                return new Renting(
                        new User(renting.getTenant().getUsername(), renting.getTenant().getFirstName(), renting.getTenant().getMiddleName(), renting.getTenant().getLastName(), renting.getTenant().getPassword(),
                                renting.getTenant().getPhone(), renting.getTenant().getRole()),
                        new User(renting.getLandlord().getUsername(), renting.getLandlord().getFirstName(), renting.getLandlord().getMiddleName(), renting.getLandlord().getLastName(),
                                renting.getLandlord().getPassword(), renting.getLandlord().getPhone(), renting.getLandlord().getRole()),
                        new Offer(renting.getOffer().getTitle(), renting.getOffer().getDescription(), renting.getOffer().getPricePerMonth(), renting.getOffer().getDeposit(), renting.getOffer().getLocation(), renting.getOffer().getType(),
                                renting.getOffer().getArea(), renting.getOffer().getFloor(), renting.getOffer().getRoomsNumber(),
                                new User(renting.getLandlord().getUsername(), renting.getLandlord().getFirstName(), renting.getLandlord().getMiddleName(), renting.getLandlord().getLastName(),
                                        renting.getLandlord().getPassword(), renting.getLandlord().getPhone(), renting.getLandlord().getRole())));
            } else {
                throw new SQLException("No keys generated");
            }
        }

    }

    @Override
    public Renting readById(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Renting WHERE id=?");
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("renting_title");
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
                return new Renting(
                        new User(tenant_username, firstName, middleName, lastName, password,
                                phone, role),
                        new User(landlord_username, firstName1, middleName1, lastName1,
                                password1, phone1, role1),
                        new Offer(title, description, price, deposit, location, type,
                                area, floor, noOfRooms,
                                new User(landlord_username, firstName, middleName, lastName,
                                        password, phone, role)));
            } else {
                return null;
            }

        }
    }

    @Override
    public RentingList getAllRentings() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Renting ");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Renting> rentings = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("renting_title");
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
                        new Offer(title, description, price, deposit, location, type,
                                area, floor, noOfRooms,
                                new User(landlord_username, firstName, middleName, lastName,
                                        password, phone, role))));
            }
            RentingList rentingList = new RentingList();
            for (Renting renting : rentings) {
                rentingList.addRenting(renting);
            }
            return rentingList;
        }
    }

    @Override
    public List<Renting> readByRentingName(String searchString)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Renting WHERE title LIKE ?");
            statement.setString(1, "%" + searchString + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Renting> offers = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("renting_title");
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

                offers.add(new Renting(
                        new User(tenant_username, firstName, middleName, lastName, password,
                                phone, role),
                        new User(landlord_username, firstName1, middleName1, lastName1,
                                password1, phone1, role1),
                        new Offer(title, description, price, deposit, location, type,
                                area, floor, noOfRooms,
                                new User(landlord_username, firstName, middleName, lastName,
                                        password, phone, role))));
            }
            return offers;
        }
    }

    @Override
    public void updateRenting(Renting renting) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Renting SET renting_title = ?, description=?, location=?, type=?, floor=?, price_per_month=?, deposit=?, area=?,  landlord_username = ?, tenant_username = ?, tenant_feedback = ?, landlord_feedback = ? WHERE id=?");
            statement.setString(1, renting.getOffer().getTitle());
            statement.setString(2, renting.getOffer().getDescription());
            statement.setString(3, renting.getOffer().getLocation());
            statement.setString(4, renting.getOffer().getType());
            statement.setInt(5, renting.getOffer().getFloor());
            statement.setDouble(6, renting.getOffer().getPricePerMonth());
            statement.setDouble(7, renting.getOffer().getDeposit());
            statement.setDouble(8, renting.getOffer().getArea());
            statement.setString(9, renting.getLandlord().getUsername());
            statement.setString(10, renting.getTenant().getUsername());
            statement.setString(11, renting.getTenantFeedback());
            statement.setString(12, renting.getLandlordFeedback());
            statement.setInt(13, renting.getOffer().getID());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteRenting(Renting renting) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Renting WHERE id=?");
            statement.setInt(1, renting.getOffer().getID());
            statement.executeUpdate();
        }
    }

    @Override
    public void publishFeedBack(String role, String feedback, Renting renting) throws SQLException {

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Renting SET tenant_feedback = ?, landlord_feedback = ? WHERE id=? ");
            statement.setInt(3, renting.getOffer().getID());
            if (role.equals("Tenant")) {
                statement.setString(1, renting.getTenantFeedback());
            } else if (role.equals("Landlord")) {
                statement.setString(2, renting.getLandlordFeedback());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public RentingList getAllRentingsByUsername(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM Renting WHERE landlord_username OR tenant_username LIKE ?");
            statement.setString(1, "%" + username + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Renting> rentings = new ArrayList<>();
            while (resultSet.next()) {
                String title = resultSet.getString("renting_title");
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
                        new Offer(title, description, price, deposit, location, type,
                                area, floor, noOfRooms,
                                new User(landlord_username, firstName, middleName, lastName,
                                        password, phone, role))));
            }

            RentingList rentingList = new RentingList();
            for (Renting renting : rentings) {
                rentingList.addRenting(renting);
            }
            return rentingList;
        }

    }


}
