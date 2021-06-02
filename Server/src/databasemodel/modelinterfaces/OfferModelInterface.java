package databasemodel.modelinterfaces;

import model.Offer;
import model.OfferList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface OfferModelInterface
{
  Offer createOffer(Offer offer) throws SQLException;
  Offer readById(int id) throws SQLException;
  List<Offer> readByOfferTitle(String searchString) throws SQLException;
  void updateOffer(Offer offer) throws SQLException;
  void deleteOffer(Offer offer) throws SQLException;
  OfferList getAllOffers() throws SQLException;
  List<Offer> returnPriceRange(double min, double max) throws SQLException;
  List<Offer> returnNumberOfRooms(int noOfRooms) throws SQLException;
  List<Offer> returnOfferType(String type) throws SQLException;
  List<Offer> returnOfferFloor(int floor) throws SQLException;
  List<Offer> returnDeposit(double deposit) throws SQLException;
  ArrayList<Offer> returnCombinedFilter(double minPrice, double maxPrice,
      int noOfRooms, String type, int floor, double deposit)
      throws SQLException;

}
