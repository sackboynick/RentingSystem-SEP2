package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;


public class UserViewViewModel {
    private final Model model;
    private final StringProperty username,firstName,middleName,lastName,phone,role,numberOfDeals,message;

    public UserViewViewModel(Model model){
        this.model=model;
        this.username=new SimpleStringProperty();
        this.firstName=new SimpleStringProperty();
        this.middleName=new SimpleStringProperty();
        this.lastName=new SimpleStringProperty();
        this.phone=new SimpleStringProperty();
        this.role=new SimpleStringProperty();
        this.numberOfDeals=new SimpleStringProperty();
        this.message=new SimpleStringProperty();
        if(ViewState.getInstance().getDisplayedUser()!=null)
            setUserInfo();
    }

    public void setUserInfo(){
        this.username.set(ViewState.getInstance().getDisplayedUser().getUsername());
        this.firstName.set(ViewState.getInstance().getDisplayedUser().getFirstName());
        this.middleName.set(ViewState.getInstance().getDisplayedUser().getMiddleName());
        this.lastName.set(ViewState.getInstance().getDisplayedUser().getLastName());
        this.phone.set(Long.toString(ViewState.getInstance().getDisplayedUser().getPhone()));
        this.role.set(ViewState.getInstance().getDisplayedUser().getRole());
        this.numberOfDeals.set(Integer.toString(ViewState.getInstance().getDisplayedUser().getDealsClosed()));
        this.message.set("");
    }

    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getMiddleName() {
        return middleName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getRole() {
        return role;
    }

    public StringProperty getPhone() {
        return phone;
    }

    public StringProperty getNumberOfDeals() {
        return numberOfDeals;
    }

    public StringProperty getMessageToLandlord() {
        return message;
    }

    public void sendMessage(){
        if(!message.get().equals(""))
            this.model.sendMessage(ViewState.getInstance().getUser(), ViewState.getInstance().getDisplayedUser().getUsername(), message.toString());
    }

}
