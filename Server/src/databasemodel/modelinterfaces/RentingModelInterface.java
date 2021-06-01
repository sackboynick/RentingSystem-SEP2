package databasemodel.modelinterfaces;

import model.Offer;
import model.Renting;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface RentingModelInterface
{
  Renting createRentingOffer(Renting renting) throws SQLException;
  Renting readById(Offer offer, User landlord, User tenant) throws SQLException;
  List<Renting> getAllRentings() throws SQLException;
  List<Renting> readByRentingName(String searchString) throws SQLException;
  void updateRenting(Renting renting) throws SQLException;
  void deleteRenting(Renting renting) throws SQLException;
}
