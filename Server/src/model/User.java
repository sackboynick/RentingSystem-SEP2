package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class representing an user in the system.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class User  implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private String username, password,firstName,middleName,lastName,role;
    private long phone;
    private int dealsClosed;
    private final ArrayList<Message> messagesAndRequests;

    private static final String[] ROLES={"Landlord","Tenant"};

    /**
     * Seven-argument constructor.
     * @param username The username of the user.
     * @param firstName The first name of the user.
     * @param middleName The middle name of the user.
     * @param lastName The last name of the user.
     * @param password The password of the user.
     * @param phone The phone number of the user.
     * @param role The role of the user in the system.
     */
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

    /**
     * Getter for the list of messages
     * @return ArrayList of Message objects of the user
     */
    public ArrayList<Message> getMessagesAndRequests() {
        return messagesAndRequests;
    }

    /**
     * Getter for the username of the user
     * @return String for the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the password of the user
     * @return String for the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for the first name of the user
     * @return String for the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the middle name of the user
     * @return String for the middle name of the user.
     */
    public String getMiddleName() {
        return middleName;

    }


    /**
     * Getter for the last name of the user.
     * @return String for the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the phone number of the user.
     * @return Long value for the phone number of the user.
     */
    public long getPhone() {
        return phone;
    }

    /**
     * Getter for the role of the user in the system.
     * @return String for the role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Getter for the number of deals closed of the user
     * @return Integer for the number of deals closed.
     */
    public int getDealsClosed() {
        return dealsClosed;
    }

    /**
     * Setter for the username of the user
     * @param username String for the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for the password of the user
     * @param password String for the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for the first name of the user
     * @param firstName String for the first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for the middle name of the user
     * @param middleName String for the middle name of the user.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Setter for the last name of the user
     * @param lastName String for the last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter for the phone number of the user
     * @param phone Long value for the phone number
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * Setter for the role of the user
     * @param role String for the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Setter for the number of deals closed of the user
     * @param dealsClosed Integer value for the number of deals closed
     */
    public void setDealsClosed(int dealsClosed) {
        this.dealsClosed = dealsClosed;
    }

    /**
     * Method use to check if the credentials passed as arguments belong
     * to the account
     * @param username The username
     * @param password The password
     * @return True if the user has the same username and password, false if not.
     */
    public boolean forLogin(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Method to add a message to the personal message list of the user.
     * @param message The message to be added.
     */
    public void addMessageOrRequest(Message message){
        this.messagesAndRequests.add(0,message);
    }

    /**
     * Method toString overridden to display the details of an user.
     * @return The details of the user in a String format.
     */
    @Override
    public String toString(){
        return username+", "+firstName+" "+lastName;
    }

    /**
     * Method to return a new user that has the same exactly data as the user object which called the method.
     * @return A new copy of the user
     */
    public User copy() {
        return new User(username, password,firstName,middleName,lastName,phone,role);
    }
}
