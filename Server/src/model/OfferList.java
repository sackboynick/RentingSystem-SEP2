package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class representing a list of Offers.
 * @author Group 8-SEP2
 * @version 1.0.0 2021
 */

public class OfferList  implements Serializable {
    @Serial
    private static final long serialVersionUID = 65296850986775769L;
    private final ArrayList<Offer> offers;

    /**
     * Zero-argument constructor.
     */
    public OfferList(){
        this.offers= new ArrayList<>();
    }

    /**
     * Method to add an offer to the offer list. An illegal offer has the same
     * title has an offer which already exists.
     * @param offer The offer that has to be added to the list.
     */
    public void addOffer(Offer offer){
        boolean result=false;
        for(Offer x:offers){
            if (offer.getTitle().equals(x.getTitle())) {
                result = true;
                break;
            }
        }
        if(!result)
            this.offers.add(offer);
    }


    public Offer getOfferByID(String ID) {
        for(Offer offer:offers){
            if(offer.getID().equals(ID))
                return offer;
        }
        return null;
    }

    /**
     * Method to obtain the list of offers in an ArrayList type.
     * @return ArrayList of offers.
     */
    public ArrayList<Offer> getOffers() {
        return offers;
    }
}
