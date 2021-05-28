package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class RentingList implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098645757695L;
    private ArrayList<Renting> rentingArrayList;


    public RentingList(){
        this.rentingArrayList= new ArrayList<>();
    }


    public ArrayList<Renting> getRentingArrayList() {
        return rentingArrayList;
    }
}
