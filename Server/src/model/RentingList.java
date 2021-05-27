package model;

import java.util.ArrayList;

public class RentingList {
    private ArrayList<Renting> rentingArrayList;


    public RentingList(){
        this.rentingArrayList= new ArrayList<>();
    }


    public ArrayList<Renting> getRentingArrayList() {
        return rentingArrayList;
    }
}
