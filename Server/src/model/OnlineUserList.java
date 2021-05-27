package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class OnlineUserList implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757690L;
    private final ArrayList<User> users;

    public OnlineUserList(){
        this.users=new ArrayList<>();
    }

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

    public ArrayList<User> getUsers() {
        return users;
    }



}
