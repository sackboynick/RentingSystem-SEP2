package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    private Offer offer;

    @BeforeEach
    void setUp() {
        offer=new Offer("Title","Description",1000,10000,"Location","Type",100,1,1,new User("Username","firstName","middleName","lastName","password",34564224,"Landlord"));
    }

    @Test void title(){
        this.offer.setTitle("testTitle");
        assertEquals("testTitle",offer.getTitle());
    }
    @Test void description(){
        this.offer.setDescription("testDescription");
        assertEquals("testDescription",offer.getDescription());
    }
    @Test void pricePerMonth(){
        this.offer.setPricePerMonth(999);
        assertEquals(999,offer.getPricePerMonth());

        this.offer.setPricePerMonth(0);
        assertEquals(0,offer.getPricePerMonth());

        this.offer.setPricePerMonth(-1);
        assertEquals(1,offer.getPricePerMonth());

        this.offer.setPricePerMonth(-100);
        assertEquals(100,offer.getPricePerMonth());
    }
    @Test void deposit(){
        this.offer.setDeposit(9999);
        assertEquals(9999,offer.getDeposit());

        this.offer.setDeposit(0);
        assertEquals(0,offer.getDeposit());

        this.offer.setDeposit(-1);
        assertEquals(1,offer.getDeposit());

        this.offer.setDeposit(-10000);
        assertEquals(10000,offer.getDeposit());
    }
    @Test void area(){
        this.offer.setArea(50);
        assertEquals(50,offer.getArea());

        this.offer.setArea(0);
        assertEquals(0,offer.getArea());

        this.offer.setArea(-1);
        assertEquals(1,offer.getArea());

        this.offer.setArea(-50);
        assertEquals(50,offer.getArea());
    }
    @Test void floor(){
        this.offer.setFloor(3);
        assertEquals(3,offer.getFloor());

        this.offer.setFloor(0);
        assertEquals(0,offer.getFloor());

        this.offer.setFloor(-1);
        assertEquals(1,offer.getFloor());

        this.offer.setFloor(-3);
        assertEquals(3,offer.getFloor());
    }
    @Test void numberOfRooms(){
        this.offer.setRoomsNumber(4);
        assertEquals(4,offer.getRoomsNumber());

        this.offer.setRoomsNumber(0);
        assertEquals(0,offer.getRoomsNumber());

        this.offer.setRoomsNumber(-1);
        assertEquals(1,offer.getRoomsNumber());

        this.offer.setRoomsNumber(-4);
        assertEquals(4,offer.getRoomsNumber());

    }

    @Test void location(){
        this.offer.setLocation("testLocation");
        assertEquals("testLocation",offer.getLocation());
    }

    @Test void type(){
        this.offer.setType("testType");
        assertEquals("testType",offer.getType());
    }

    @Test void landlord(){
        User user=new User("testUsername","testFirstName","testMiddleName","testLastName","testPassword",34564224,"Landlord");
        this.offer.setLandlord(user);
        assertEquals(user.toString(),offer.getLandlord().toString());
    }

}