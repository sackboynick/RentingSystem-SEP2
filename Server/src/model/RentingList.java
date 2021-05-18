package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RentingList {
    private static RentingList instance;
    private ObservableList<Renting> rentings;


    private RentingList(){
        this.rentings= FXCollections.observableArrayList();
    }

    public static RentingList getInstance(){
        if(instance==null)
            instance=new RentingList();
        return instance;
    }

    public ObservableList<String> getRentingsString() {
        ObservableList<String> observableList=FXCollections.observableArrayList();
        for(Renting renting:rentings)
            observableList.add(renting.toString());
        return observableList;
    }

    public String addRenting(Renting renting){
        if (renting.isApproved()){
            this.rentings.add(renting);
            return "The deal is closed successfully!";
        }

        return "There was a problem closing the deal";
    }
}
