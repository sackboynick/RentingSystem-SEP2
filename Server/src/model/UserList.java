package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;


    public UserList(){
        this.users= new ArrayList<>();
    }

    public ArrayList<User> getUsersArraylist(){
        return users;
    }

    public ObservableList<String> getUsersToString() {
        ObservableList<String> observableList=FXCollections.observableArrayList();
        for(User user:users)
            observableList.add(0,user.toStringShort());
        return observableList;
    }

    public String addUser(User user){
        for(User x:users){
            if(x.getUsername().equals(user.getUsername()))
                return "This username already exists";
        }
        if(user!=null) {
            this.users.add(0, user);
            System.out.println(getUsersToString().stream().sorted());
        }
        else
            System.out.println("user is null");
        return "You signed in!";
    }
    public User getUserByUsername(String username){
        for(User user:users){
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
