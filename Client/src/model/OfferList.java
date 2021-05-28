package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class OfferList  implements Serializable {
    private final ArrayList<Offer> offers;
    @Serial
    private static final long serialVersionUID = 65296850986775769L;


    public OfferList(){
        this.offers= new ArrayList<>();
    }

    public void addOffer(Offer offer){
        if(!offers.contains(offer)) {
            offers.add(offer);
        }
    }
    public String removeOffer(String ID){
        for(Offer offer: offers){
            if (offer.getID().equals(ID)) {
                offers.remove(offer);
                return "Offer #"+offer.getID()+" has been removed!";
            }

        }
        return "There has been a problem removing the offer";
    }

    public Offer getOfferByID(String ID) {
        for(Offer offer:offers){
            if(offer.getID().equals(ID))
                return offer;
        }
        return null;
    }


    public ArrayList<Offer> getOffers() {
        return offers;
    }
}
