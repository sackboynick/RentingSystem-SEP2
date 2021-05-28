package model;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Offer implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757695L;
    private String ID, title, description, location,type;
    private static final String[] TYPE = {"Apartment", "House","Room"};
    private int roomsNumber, floor;
    private double pricePerMonth, deposit,area;
    private Date availableDate;
    private boolean wiFi,pets,smoking,balcony;
    private String usernameOfOfferer;
    private static final String[] FEATURES = {"Wi-Fi", "Pets", "Smoking", "Balcony"};
    private User landlord;

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
        this.availableDate=new Date();
        this.usernameOfOfferer="";
    }


    private void getAvailableDate(Date date) {
        this.availableDate = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void makeOffer(String usernameOfOfferer) {
        this.usernameOfOfferer = usernameOfOfferer;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }
    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void setArea(double area){
        this.area=area;
    }

    public static String[] getTYPE() {
        return TYPE;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getUsernameOfOfferer() {
        return usernameOfOfferer;
    }

    public String getDescription() {
        return description;
    }

    public static String[] getAllTypes() {
        return TYPE;
    }

    public String getLocation() {
        return location;
    }

    public int getFloor() {
        return floor;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public User getLandlord() {
        return landlord;
    }

    public String getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }


    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public double getDeposit() {
        return deposit;
    }


    @Override
    public String toString(){
        return getTitle()+", offered by "+landlord.getUsername()+" for "+getPricePerMonth()+"kr/month";
    }


}
