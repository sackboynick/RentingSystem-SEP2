package databasemodel.modelinterfaces;

import model.Offer;
import model.Renting;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface DealsModelInterface
{
  Renting create(Renting dealOffer) throws SQLException;
  Renting readById(int id) throws SQLException;
  List<Renting> readByName(String searchString) throws SQLException;
  void update(Renting dealOffer) throws SQLException;
  void delete(Renting dealOffer) throws SQLException;
  List<Renting> getAllDeals() throws SQLException;
}
