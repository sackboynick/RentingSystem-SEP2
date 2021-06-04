package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private Message message;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("--> setUp()");
        message=new Message("Hello!",new User("Username","firstName","middleName","lastName","password",34564224,"Landlord"));
    }

    @Test void getBody(){
        assertEquals("Hello!",message.getBody());
    }
    @Test void getSender(){
        assertEquals("Username, firstName lastName",message.getSender().toString());
    }

    @Test void string(){
        assertEquals("From "+message.getSender().getUsername()+": "+message.getBody(),message.toString());
    }

}