package databasemodel.modelinterfaces;

import model.Offer;
import model.Renting;
import model.RentingList;
import model.User;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface RentingModelInterface
{
  Renting createRentingOffer(Renting renting) throws SQLException;
  Renting readById(int id) throws SQLException;
  RentingList getAllRentings() throws SQLException;
  RentingList getAllRentingsByUsername(String username) throws SQLException;
  List<Renting> readByRentingName(String searchString) throws SQLException;
  void updateRenting(Renting renting) throws SQLException;
  void deleteRenting(Renting renting) throws SQLException;
  void publishFeedBack(String role, String feedback, Renting renting)throws SQLException;
}
