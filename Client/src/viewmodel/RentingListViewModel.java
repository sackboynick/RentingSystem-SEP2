package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Renting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */


public class RentingListViewModel implements PropertyChangeListener {
    private ObservableList<Renting> rentings;
    private final Model model;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public RentingListViewModel(Model model){
        this.model=model;
        this.model.addListener("Renting",this);
        this.rentings=FXCollections.observableArrayList();
    }

    /**
     * The method updates the renting deals list obtaining it from the model.
     */
    public void updateRentingList(){
        if(ViewState.getInstance().getUser()!=null)
            this.rentings= FXCollections.observableArrayList(model.getRentingList().getRentingArrayList());

    }

    /**
     * Getter for the renting list.
     * @return An ObservableList of Renting objects.
     */
    public ObservableList<Renting> getRentings() {
        return rentings;
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateRentingList();
        this.rentings.add(0,(Renting) evt.getNewValue());
    }
}
