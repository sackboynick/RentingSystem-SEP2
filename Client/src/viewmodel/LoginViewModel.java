package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.User;


public class LoginViewModel {
    private StringProperty username;
    private StringProperty password;
    private final Model model;

    public LoginViewModel(Model model){
        this.model=model;
        this.username=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
    }


    public StringProperty getUsername(){
        return username;
    }

    public StringProperty getPassword() {
        return password;
    }


    public User login() {
        User user=model.login(username.get(), password.get());
        ViewState.getInstance().setUser(user);
        model.updateOffersList();
        model.updateOnlineUserList();
        return user;
    }



}