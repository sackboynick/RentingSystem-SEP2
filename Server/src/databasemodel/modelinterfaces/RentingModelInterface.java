package databasemodel.modelinterfaces;

import model.Renting;
import model.RentingList;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface to create Renting objects getting them from a database in SQL.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public interface RentingModelInterface
{
  /**
   * The method adds the Renting object to the database Renting schema.
   *
   * @param renting The renting to be added.
   * @return The Offer if the action is done successfully, null if not.
   *
   * @throws SQLException if there any problems with the database
   */
  Renting createRentingOffer(Renting renting) throws SQLException;

  /**
   * The method returns an Renting object from the database Renting schema.
   *
   * @param id The unique id of the Renting.
   * @return The Renting with the indicated id.
   * @throws SQLException if there any problems with the database
   */
  Renting readById(int id) throws SQLException;

  /**
   * The method returns all the available rentings from the Renting schema.
   *
   * @return The RentingList containing all the offers.
   * @throws SQLException if there any problems with the database
   */
  RentingList getAllRentings() throws SQLException;

  /**
   * The method returns all the renting deals by a username.
   * @param username The username of the user.
   * @return RentingList object from the database.
   * @throws SQLException if there any problems with the database
   */
  RentingList getAllRentingsByUsername(String username) throws SQLException;

  /**
   * The method returns a list of Renting objects with the same title  from the database Offer schema.
   *
   * @param searchString The title of the renting.
   * @return The Renting with a similar title.
   * @throws SQLException if there any problems with the database
   */
  List<Renting> readByRentingName(String searchString) throws SQLException;

  /**
   * The method updates the Renting object from the database Renting schema.
   *
   * @param renting The renting.
   * @throws SQLException if there any problems with the database
   */

  void updateRenting(Renting renting) throws SQLException;

  /**
   * The method deletes the Renting object from the database Renting schema.
   *
   * @param renting The renting to be deleted.
   * @throws SQLException if there any problems with the database
   */
  void deleteRenting(Renting renting) throws SQLException;

  /**
   * The method adds to the database a feedback about landlord and vice versa after a deal is closed.
   *
   * @param role The role of the user.
   * @param feedback The feedback to be added.
   * @param renting The renting that have to be updated to.
   * @throws SQLException if there any problems with the database
   */
  void publishFeedBack(String role, String feedback, Renting renting)throws SQLException;
}
