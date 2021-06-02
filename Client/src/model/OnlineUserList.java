package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of the users online.
 * @author Group 8-SEP2
 * @version 1.0.0 2021
 */

public class OnlineUserList implements Serializable {

    @Serial
    private static final long serialVersionUID = 652968509867757690L;
    /**
     * List of offers.
     */
    private final ArrayList<User> users;


    /**
     * Zero-argument constructor.
     */
    public OnlineUserList(){
        this.users=new ArrayList<>();
    }


    /**
     * Getter for the list of users.
     * @return ArrayList of user objects.
     */
    public ArrayList<User> getUsers() {
        return users;
    }



}
