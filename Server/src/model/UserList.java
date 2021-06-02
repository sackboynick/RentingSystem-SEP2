package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * A class representing a list of users.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class UserList {
    private final ArrayList<User> users;


    /**
     * Zero-argument constructor.
     */
    public UserList(){
        this.users= new ArrayList<>();
    }

    /**
     * Getter for the list of users.
     * @return The ArrayList of User objects.
     */
    public ArrayList<User> getUsersArraylist(){
        return users;
    }

    /**
     * The method adds a user to the list if an another user doesn't have the same username.
     * @param user The User object to be added.
     */

    public void addUser(User user){
        for(User x:users){
            if(x.getUsername().equals(user.getUsername()))
                return;
        }
        if(user!=null) {
            this.users.add(0, user);
        }
        else
            System.out.println("user is null");
    }

    /**
     * The method returns the user in the list which has the same username has the one passed in the argument.
     * @param username The String for the username of the user
     * @return The User object of the user or null if no users have that username.
     */
    public User getUserByUsername(String username){
        for(User user:users){
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
