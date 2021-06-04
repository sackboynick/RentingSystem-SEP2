package model;

import databasemodel.modelinterfaces.UserModelInterface;
import databasemodel.models.UserModel;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;
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
     * List of users.
     */
    private final ArrayList<User> users;
    private final UserModelInterface databaseUserModel;

    /**
     * Zero-argument constructor.
     */
    public OnlineUserList(){
        this.databaseUserModel=new UserModel();
        this.users=new ArrayList<>();
    }

    /**
     * The method returns an User object if an user the database has the same credentials as the ones passed as arguments.
     * @param username The String for the username of the user
     * @param password The String for the password of the user
     * @param userList The UserList object which represents the list of users registered in the system.
     * @return The User object corresponding to the account with the same credentials.
     */
    public User loginInUser(String username,String password,UserList userList){
        for(User user : userList.getUsersArraylist()) {
            if (user.forLogin(username, password)) {
                {
                    if(!users.contains(user))
                        this.users.add(0, user);
                }
                return user;
            }
        }
        return null;
    }

    /**
     * The method returns an User object if an user the database has the same credentials as the ones passed as arguments.
     * @param username The String for the username of the user
     * @param password The String for the password of the user
     * @return The User object corresponding to the account with the same credentials.
     */
    public User loginInUser(String username,String password){
        try {
            for(User user : databaseUserModel.getAllUsers().getUsersArraylist()) {
                if (user.forLogin(username, password)) {
                    {
                        if(!users.contains(user))
                            this.users.add(0, user);
                    }
                    return user;
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * Getter for the list of users.
     * @return ArrayList of user objects.
     */
    public ArrayList<User> getUsers() {
        return users;
    }



}
