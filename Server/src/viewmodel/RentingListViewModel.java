package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Renting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RentingListViewModel implements PropertyChangeListener {
    private ObservableList<Renting> rentings;
    private final Model model;

    public RentingListViewModel(Model model){
        this.rentings= FXCollections.observableArrayList(model.getRentingList().getRentingArrayList());
        this.model=model;
        this.model.addListener("ReloadLists",this);
    }

    public ObservableList<Renting> getRentings() {
        return rentings;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> this.rentings=FXCollections.observableArrayList(model.getRentingList().getRentingArrayList()));
    }
}
