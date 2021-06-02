package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of renting deals.
 * @author Group 8-SEP2
 * @version 1.0.0 2021
 */

public class RentingList implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685067546757695L;
    private final ArrayList<Renting> rentingArrayList;


    /**
     * Zero-argument constructor.
     */
    public RentingList(){
        this.rentingArrayList= new ArrayList<>();
    }


    /**
     * Getter for the list of renting deals
     * @return ArrayList of Renting objects.
     */
    public ArrayList<Renting> getRentingArrayList() {
        return rentingArrayList;
    }
}
