package viewmodel;

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
        this.model=model;
        this.model.addListener("Renting",this);
        this.rentings=FXCollections.observableArrayList();
    }

    public void updateRentingList(){
        if(ViewState.getInstance().getUser()!=null)
            this.rentings= FXCollections.observableArrayList(model.getRentingList(ViewState.getInstance().getUser().getUsername()).getRentingArrayList());

    }

    public ObservableList<Renting> getRentings() {
        return rentings;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.rentings.add(0,(Renting) evt.getNewValue());
    }
}
