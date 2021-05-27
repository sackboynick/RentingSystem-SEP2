package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Renting;

public class RentingListViewModel {
    private final ObservableList<Renting> rentings;

    public RentingListViewModel(Model model){
        this.rentings= FXCollections.observableArrayList(model.getRentingList().getRentingArrayList());
    }

    public ObservableList<Renting> getRentings() {
        return rentings;
    }
}
