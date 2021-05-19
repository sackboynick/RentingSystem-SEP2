package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UsersListViewModel implements PropertyChangeListener {
    private final Model model;
    private final ObservableList<User> users;
    private final StringProperty username,firstName,lastName,role,numberOfUsers;

    public UsersListViewModel(Model model){
        this.model=model;
        this.model.addListener("Users",this);
        this.users= FXCollections.observableArrayList(model.getUsers().getUsersArraylist());
        this.username=new SimpleStringProperty();
        this.firstName=new SimpleStringProperty();
        this.lastName=new SimpleStringProperty();
        this.role=new SimpleStringProperty();
        this.numberOfUsers=new SimpleStringProperty(Integer.toString(users.size()));
    }


    public ObservableList<User> getUsers() {
        return users;
    }

    public StringProperty getUsername(){
        return username;
    }
    public StringProperty getFirstName(){
        return firstName;
    }
    public StringProperty getLastName(){
        return lastName;
    }
    public StringProperty getRole(){
        return role;
    }
    public StringProperty getNumberOfUsers(){
        return numberOfUsers;
    }

    public void removeUser(User user){
        this.model.getUsers().getUsersArraylist().remove(user);
        this.users.remove(user);
        this.numberOfUsers.setValue(Integer.toString(users.size()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            this.users.add((User) evt.getNewValue());
            this.numberOfUsers.setValue(Integer.toString(users.size()));
        });
    }
}
