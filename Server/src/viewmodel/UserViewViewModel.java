package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;


public class UserViewViewModel {
    private final Model model;
    private StringProperty username,firstName,middleName,lastName,phone,role,numberOfDeals,messageToLandlord;

    public UserViewViewModel(Model model){
        this.model=model;
        if(ViewState.getInstance().getDisplayedUser()!=null)
            setUserInfo();
    }

    public void setUserInfo(){
        this.username=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getUsername());
        this.firstName=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getFirstName());
        this.middleName=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getMiddleName());
        this.lastName=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getLastName());
        this.phone=new SimpleStringProperty(Long.toString(ViewState.getInstance().getDisplayedUser().getPhone()));
        this.role=new SimpleStringProperty(ViewState.getInstance().getDisplayedUser().getRole());
        this.numberOfDeals=new SimpleStringProperty(Integer.toString(ViewState.getInstance().getDisplayedUser().getDealsClosed()));
        this.messageToLandlord=new SimpleStringProperty();
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



}
