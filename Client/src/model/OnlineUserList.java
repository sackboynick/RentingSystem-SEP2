package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class OnlineUserList implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757690L;
    private final ArrayList<User> users;
    private Model model;


    public OnlineUserList(Model model){
        this.users= new ArrayList<>();
        this.model=model;
    }

    public OnlineUserList(){
        this.users=new ArrayList<>();
    }


    public ArrayList<User> getUsers() {
        return users;
    }



}
