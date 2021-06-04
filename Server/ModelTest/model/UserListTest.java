package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {
    private UserList userList;

    @BeforeEach
    void setUp() {
        this.userList=new UserList();
    }
    @Test
    void addUser(){
        User user=new User("Username","firstName","middleName","lastName","password",34564224,"Landlord");
        userList.addUser(user);
        assertTrue(this.userList.getUsersArraylist().contains(user));

        User user1= new User("Username","TestFirstName","TestMiddleName","TestLastName","password",34564224,"Landlord");
        userList.addUser(user1);
        assertFalse(this.userList.getUsersArraylist().contains(user1));
    }
}