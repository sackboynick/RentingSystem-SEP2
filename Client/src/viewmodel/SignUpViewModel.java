package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import model.Model;
import model.User;

public class SignUpViewModel {
    private StringProperty username;
    private StringProperty password;
    private final StringProperty firstName,middleName,lastName,phone,role;
    private final Model model;

    public SignUpViewModel(Model model){
        this.model=model;
        this.username=new SimpleStringProperty("");
        this.password=new SimpleStringProperty("");
        this.firstName=new SimpleStringProperty("");
        this.middleName=new SimpleStringProperty("");
        this.lastName=new SimpleStringProperty("");
        this.phone=new SimpleStringProperty("");
        this.role=new SimpleStringProperty("");
    }


    public StringProperty getUsername(){
        return username;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getMiddleName() {
        return middleName;
    }

    public StringProperty getPhone() {
        return phone;
    }

    public StringProperty getRole() {
        return role;
    }

    public String signUp(String role){
        if(username.get().equals("") || password.get().equals("") || firstName.get().equals("")  || lastName.get().equals("") || phone.get().equals("") || role.equals("")) {
            {
                return "Please do not leave any blank field.";
            }
        }
        else if(password.get().length()<5) {
            this.password.set("");
            return "The password should be at least 5 characters long.";
        }
        else if(model.signUp(new User(username.get(), firstName.get(), middleName.get(), lastName.get(), password.get(), Long.parseLong(phone.get()), role)))
            return "Valid";
        else
            return "The user already exists.";
    }
}
