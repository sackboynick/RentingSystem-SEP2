package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class RentingList implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685067546757695L;
    private ArrayList<Renting> rentingArrayList;


    public RentingList(){
        this.rentingArrayList= new ArrayList<>();
    }


    public ArrayList<Renting> getRentingArrayList() {
        return rentingArrayList;
    }

    /**
     * The method adds the Renting object passed as argument to the list of renting deals.
     * @param renting The Renting object to be added to the list.
     */
    public void addRenting(Renting renting) {
        rentingArrayList.add(renting);
    }
}
