package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentingListTest {
    private RentingList rentingList;

    @BeforeEach
    void setUp() {
        this.rentingList=new RentingList();
    }

    @Test
    void addRenting(){
        Renting renting=new Renting(new User("usernameTenant","firstNameTenant","middleNameTenant","lastNameTenant","passwordTenant",34564224,"Tenant"),new User("usernameLandlord","firstNameLandlord","middleNameLandlord","lastNameLandlord","passwordLandlord",34564224,"Landlord"),new Offer("Title","Description",1000,10000,"Location","Type",100,1,1,new User("Username","firstName","middleName","lastName","password",34564224,"Landlord")));
        this.rentingList.addRenting(renting);
        assertTrue(this.rentingList.getRentingArrayList().contains(renting));
    }
}