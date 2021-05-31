package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class User  implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private String username, password,firstName,middleName,lastName,role;
    private long phone;
    private int dealsClosed;
    private final ArrayList<Message> messagesAndRequests;

    private static final String[] ROLES={"Admin","Landlord","Tenant"};

    public User(String username,String firstName,String middleName,String lastName,String password,long phone,String role){
        setUsername(username);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setPassword(password);
        setPhone(phone);
        setRole(role);
        setDealsClosed(0);
        this.messagesAndRequests=new ArrayList<>();
    }

    public ArrayList<Message> getMessagesAndRequests() {
        return messagesAndRequests;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public int getDealsClosed() {
        return dealsClosed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDealsClosed(int dealsClosed) {
        this.dealsClosed = dealsClosed;
    }

    public boolean forLogin(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addMessageOrRequest(Message message){
        this.messagesAndRequests.add(0,message);
    }
    @Override
    public String toString(){
        return username+", "+firstName+" "+lastName;
    }


    public String toStringShort() {
        return username+", "+firstName+" "+lastName;
    }

    public User copy() {
        return new User(username, password,firstName,middleName,lastName,phone,role);
    }
}
