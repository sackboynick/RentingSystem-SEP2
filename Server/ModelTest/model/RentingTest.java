package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RentingTest {
    private Renting renting;

    @BeforeEach
    void setUp() {
        this.renting=new Renting(new User("usernameTenant","firstNameTenant","middleNameTenant","lastNameTenant","passwordTenant",34564224,"Tenant"),new User("usernameLandlord","firstNameLandlord","middleNameLandlord","lastNameLandlord","passwordLandlord",34564224,"Landlord"),new Offer("Title","Description",1000,10000,"Location","Type",100,1,1,new User("Username","firstName","middleName","lastName","password",34564224,"Landlord")));
    }

    @Test
    void getTenant(){
        assertEquals("usernameTenant",renting.getTenant().getUsername());
    }
    @Test void getLandlord(){
        assertEquals("usernameLandlord",renting.getLandlord().getUsername());
    }

    @Test void feedback(){
        assertEquals("",renting.getTenantFeedback());
        assertEquals("",renting.getLandlordFeedback());
    }

    @Test void getDate(){
        Date date=new Date();
        assertEquals(renting.getDate().toString(),date.toString());
    }
}