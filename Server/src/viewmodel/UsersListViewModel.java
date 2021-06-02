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

/**
 * This class is used to let a controller communicate with the model manager.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class UsersListViewModel implements PropertyChangeListener {
    private final Model model;
    private final ObservableList<User> users;
    private final StringProperty username,firstName,role,numberOfUsers;

    /**
     * One-argument constructor.
     * @param model The model object which will be delegated to get the data and to execute some methods.
     */
    public UsersListViewModel(Model model){
        this.model=model;
        this.model.addListener("User",this);
        this.users= FXCollections.observableArrayList(model.getUsers().getUsersArraylist());
        this.username=new SimpleStringProperty();
        this.firstName=new SimpleStringProperty();
        this.role=new SimpleStringProperty();
        this.numberOfUsers=new SimpleStringProperty(Integer.toString(users.size()));
    }


    /**
     * Getter for the users list.
     * @return The ObservableList of the User objects.
     */
    public ObservableList<User> getUsers() {
        return users;
    }

    /**
     * Getter for the username property.
     * @return The StringProperty of the username.
     */
    public StringProperty getUsername(){
        return username;
    }
    /**
     * Getter for the first name property.
     * @return The StringProperty of the first name.
     */
    public StringProperty getFirstName(){
        return firstName;
    }
    /**
     * Getter for the role property.
     * @return The StringProperty of the role.
     */
    public StringProperty getRole(){
        return role;
    }
    /**
     * Getter for the number of users property.
     * @return The StringProperty of the number of users.
     */
    public StringProperty getNumberOfUsers(){
        return numberOfUsers;
    }

    /**
     * The method deletes and removes the user from every list.
     * @param user The User object to be removed.
     */
    public void removeUser(User user){
        this.model.getUsers().getUsersArraylist().remove(user);
        this.users.remove(user);
        this.numberOfUsers.setValue(Integer.toString(users.size()));
    }

    /**
     * Method called whenever a change in the properties of the model happens, because this ViewModel is its listener.
     * @param evt ObserverEvent object which contains details about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            this.users.add((User) evt.getNewValue());
            this.numberOfUsers.setValue(Integer.toString(users.size()));
        });
    }
}
