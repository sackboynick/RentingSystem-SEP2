package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        this.user=new User("Username","firstName","middleName","lastName","password",34564224,"Landlord");
    }

    @Test
    void username(){
        this.user.setUsername("testUsername");
        assertEquals("testUsername",user.getUsername());
    }
    @Test void firstName(){
        this.user.setFirstName("testFirstName");
        assertEquals("testFirstName",user.getFirstName());
    }
    @Test void middleName(){
        this.user.setMiddleName("testMiddleName");
        assertEquals("testMiddleName",user.getMiddleName());
    }

    @Test void lastName(){
        this.user.setLastName("testLastName");
        assertEquals("testLastName",user.getLastName());
    }

    @Test void password(){
        this.user.setPassword("testPassword");
        assertEquals("testPassword",user.getPassword());
    }

    @Test void phone(){
        this.user.setPhone(45675236L);
        assertEquals(45675236L,user.getPhone());
    }

    @Test void role(){
        this.user.setRole("testRole");
        assertEquals("testRole",user.getRole());
    }
}