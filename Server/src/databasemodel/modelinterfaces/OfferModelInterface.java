package databasemodel.modelinterfaces;

import model.Offer;
import model.OfferList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to create User objects getting them from a database in SQL.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public interface OfferModelInterface
{
  /**
   * The method adds the Offer object to the database Offer schema.
   *
   * @param offer The offer to be added.
   * @throws SQLException if there any problems with the database
   */
  void createOffer(Offer offer) throws SQLException;

  /**
   * The method returns an Offer object from the database Offer schema.
   *
   * @param id The unique id of the offer.
   * @return The Offer with the indicated id.
   * @throws SQLException if there any problems with the database
   */
  Offer readById(int id) throws SQLException;

  /**
   * The method returns an Offer object with the same title  from the database Offer schema.
   *
   * @param searchString The title of the offer.
   * @return The Offer with a similar title.
   * @throws SQLException if there any problems with the database
   */
  List<Offer> readByOfferTitle(String searchString) throws SQLException;

  /**
   * The method updates the Offer object from the database Offer schema.
   *
   * @param offer The offer.
   * @throws SQLException if there any problems with the database
   */
  void updateOffer(Offer offer) throws SQLException;

  /**
   * The method deletes the Offer object from the database Offer schema.
   *
   * @param offer The offer to be deleted.
   * @throws SQLException if there any problems with the database
   */
  void deleteOffer(Offer offer) throws SQLException;

  /**
   * The method returns all the offers available in the Offer schema.
   *
   * @return The OfferList containing all the offers.
   * @throws SQLException if there any problems with the database
   */
  OfferList getAllOffers() throws SQLException;

  /**
   * The method returns a list of Offer objects with a price per month between those 2 variables.
   *
   * @param min The minimum price per month for a offer.
   * @param max The maximum price per month for a offer.
   * @return The Offer object with the price between those two variables.
   * @throws SQLException if there any problems with the database
   */
  List<Offer> returnPriceRange(double min, double max) throws SQLException;

  /**
   * The method returns a list of offers from the database that have the same or more number of rooms.
   *
   * @param noOfRooms The  number of rooms .
   * @return The Offer object with the minimum an bigger number of rooms;
   * @throws SQLException if there any problems with the database
   */
  List<Offer> returnNumberOfRooms(int noOfRooms) throws SQLException;

  /**
   * The method returns list of offers from the database with the same type.
   *
   * @param type The search title input.
   * @return The a list of offers with the same title;
   * @throws SQLException if there any problems with the database
   */
  List<Offer> returnOfferType(String type) throws SQLException;

  /**
   * The method returns list of offers from the database that are located on the same floor.
   *
   * @param floor The floor number .
   * @return A list of offers with the same floor number;
   * @throws SQLException if there any problems with the database
   */
  List<Offer> returnOfferFloor(int floor) throws SQLException;

  /**
   * The method returns a list of offers from the database that have the deposit indicated in the searchInt.
   *
   * @param deposit The deposit amount .
   * @return A list of offers with the deposit maximum or less indicated in searchInt;
   * @throws SQLException if there any problems with the database
   */
  List<Offer> returnDeposit(double deposit) throws SQLException;

  /**
   * The method returns a list of offers from the database using a combined search filter
   * and returns the objects that correspond to the arguments inserted.
   *
   * @param  minPrice The minimum price per month for a offer.
   * @param maxPrice The maximum price per month for a offer.
   * @param noOfRooms The minimum number of rooms .
   * @param type The type of rent.
   * @param floor The floor number.
   * @param deposit The deposit amount.
   * @return A list of offers sorted
   * @throws SQLException if there any problems with the database
   */
  ArrayList<Offer> returnCombinedFilter(double minPrice, double maxPrice,
      int noOfRooms, String type, int floor, double deposit)
      throws SQLException;

}
