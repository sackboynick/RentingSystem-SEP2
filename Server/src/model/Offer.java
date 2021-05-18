package model;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;


public class Offer implements Serializable {
    @Serial
    private static final long serialVersionUID = 652968509867757695L;
    private String ID, title, description, location,type;
    private static final String[] TYPE = {"Apartment", "House","Room"};
    private int roomsNumber, floor;
    private double pricePerMonth, deposit,area;
    private Date availableDate;
    private static final String[] FEATURES = {"Wi-Fi", "Pets", "Smoking", "Balcony"};
    private User landlord;

    private final ArrayList<String> featuresList;

    public Offer(String title, String description, double pricePerMonth, double deposit, String location, String type,double area, int floor, int numberOfRooms, User landlord) {
        Random rand = new Random();
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
        this.featuresList = new ArrayList<>();
    }


    private void getAvailableDate(Date date) {
        this.availableDate = date;
    }

    public void setID(String ID) {
        this.ID = ID;
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


    public String getDescription() {
        return description;
    }

    public String[] getAllTypes() {
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

    public ArrayList<String> getFeaturesList() {
        return featuresList;
    }


    public String addFeature(String feature) {
        if (!featuresList.contains(feature)) {
            featuresList.add(feature);
            return feature + " added!";
        }
        return "Problem while adding the feature";
    }

    public String removeFeature(String feature) {
        if (featuresList.contains(feature)) {
            featuresList.remove(feature);
            return feature + " removed.";
        }
        return "Problem while removing the feature";
    }

    @Override
    public String toString(){
        return getTitle()+", offered by "+landlord.getUsername()+" for "+getPricePerMonth()+"kr/month";
    }


}
