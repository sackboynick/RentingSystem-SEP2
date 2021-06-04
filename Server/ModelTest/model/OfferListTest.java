package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferListTest {
    private OfferList offerList;

    @BeforeEach
    void setUp() {
        this.offerList=new OfferList();
    }

    @Test
    void addOffer(){
        Offer offer=new Offer("Title","Description",1000,10000,"Location","Type",100,1,1,new User("Username","firstName","middleName","lastName","password",34564224,"Landlord"));
        this.offerList.addOffer(offer);
        assertTrue(this.offerList.getOffers().contains(offer));

        Offer offerCopy=new Offer("Title","DescriptionCopy",1000,10000,"LocationCopy","Type",100,1,1,new User("Username","firstName","middleName","lastName","password",34564224,"Landlord"));
        this.offerList.addOffer(offerCopy);
        assertFalse(this.offerList.getOffers().contains(offerCopy));
    }
}