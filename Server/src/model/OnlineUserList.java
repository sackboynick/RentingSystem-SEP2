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
    private final Model model;


    public OnlineUserList(Model model){
        this.users= new ArrayList<>();
        this.model=model;
    }

    public User loginInUser(String username,String password){
        for(User user : model.getUsers().getUsersArraylist()) {
            if (user.forLogin(username, password)) {
                this.users.add(0,user);
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }



}
