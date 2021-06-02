package model;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * A class representing an offer, containing all the information needed.
 * @author Group 8-SEP2
 * @version 1.0.0 2021
 */

public class Offer implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757695L;
    private String  title, description, location,type;
    private static final String[] TYPE = {"Apartment", "House","Room"};
    private int roomsNumber, floor;
    private double pricePerMonth, deposit,area;
    private String usernameOfOfferer;
    private User landlord;

    /**
     * Ten-argument constructor.
     * @param title The title of the offer
     * @param description The description of the offer
     * @param pricePerMonth The price per month of the property
     * @param deposit The deposit to pay to rent the property
     * @param location The address of the property
     * @param type The type of property is being rented out
     * @param area The area of the property
     * @param floor The floor of the property
     * @param numberOfRooms The number of rooms of the property
     * @param landlord The landlord profile (User object)
     */
    public Offer(String title, String description, double pricePerMonth, double deposit, String location, String type,double area, int floor, int numberOfRooms, User landlord) {
        setTitle(title);
        setDescription(description);
        setPricePerMonth(pricePerMonth);
        setDeposit(deposit);
        setLocation(location);
        setType(type);
        setArea(area);
        setFloor(floor);
        setRoomsNumber(numberOfRooms);
        setLandlord(landlord);
        this.usernameOfOfferer="";
    }


    /**
     * Setter for the title
     * @param title String containing the title of the offer.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for the description
     * @param description String containing the description of the offer.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for the type
     * @param type String containing the type of the offer.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Setter for the floor
     * @param floor Integer for the floor of the property.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }


    /**
     * Setter for the landlord
     * @param landlord User object for the landlord of the property.
     */
    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }


    /**
     * Setter for the location
     * @param location String containing the address of the property.
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Setter for the roomsNumber
     * @param roomsNumber Integer for the number of rooms in the property
     */
    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    /**
     * Setter for the price per month of the property
     * @param pricePerMonth Double value ( decimal ) for the price of the property per month
     */
    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    /**
     * Setter for the deposit of the rent
     * @param deposit Double value ( decimal ) for the price of the deposit of the property.
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    /**
     * Setter for the area
     * @param area Double value ( decimal ) for the area of the property.
     */
    public void setArea(double area){
        this.area=area;
    }

    /**
     * Getter for the array of String containing the type of properties.
     * @return Array of String with the type of properties.
     */
    public static String[] getTYPE() {
        return TYPE;
    }


    /**
     * Getter for the title
     * @return String for the title of the offer
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the username of the offerer in the offer
     * @return Strig for the username of the offerer.
     */
    public String getUsernameOfOfferer() {
        return usernameOfOfferer;
    }

    /**
     * Getter for the description of the offer.
     * @return String for the description of the offer.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the location of the property in the offer.
     * @return String for the address of the property.
     */

    public String getLocation() {
        return location;
    }

    /**
     * Getter for the floor of the property.
     * @return Integer for the floor of the property.
     */
    public int getFloor() {
        return floor;
    }


    /**
     * Getter for the landlord of the property.
     * @return User object for the user of the landlord of the property.
     */
    public User getLandlord() {
        return landlord;
    }

    /**
     * Getter for the type of the property.
     * @return String for the type of the property.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the area of the property.
     * @return Double value ( decimal ) for the area of the property.
     */
    public double getArea() {
        return area;
    }

    /**
     * Getter for the number of rooms in the property.
     * @return Integer for the number of rooms in the property.
     */
    public int getRoomsNumber() {
        return roomsNumber;
    }


    /**
     * Getter for the price for month of the property.
     * @return Double value ( decimal ) for the price of the property per month.
     */
    public double getPricePerMonth() {
        return pricePerMonth;
    }

    /**
     * Getter for the deposit for the property.
     * @return Double value ( decimal ) for the deposit of the property.
     */
    public double getDeposit() {
        return deposit;
    }


    /**
     * Method toString overridden.
     * @return The offer details summed up in a String type.
     */
    @Override
    public String toString(){
        return getTitle()+", offered by "+landlord.getUsername()+" for "+getPricePerMonth()+"kr/month";
    }


}
