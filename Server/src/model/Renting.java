package model;


import java.io.Serializable;
import java.util.Date;
/**
 * A class representing a deal between a tenant and a landlord.
 * @author Group 8-SEP2
 * @version 1.0.0 2021
 */

public class Renting  implements Serializable {
    private final User tenant;
    private final User landlord;
    private final Offer offer;
    private final Date date;

    private final String tenantFeedback;
    private final String landlordFeedback;


    /**
     * Three-argument constructor. Every user and the offer are set, the feedbacks are set empty
     * and the date is set as the current day when the object is created.
     *
     * @param tenant Contains the user object of the tenant.
     * @param landlord Contains the user object of the landlord.
     * @param offer Contains the offer object.
     */
    public Renting(User tenant,User landlord,Offer offer){
        this.tenant=tenant.copy();
        this.landlord=landlord.copy();
        this.offer=offer;
        this.tenantFeedback="";
        this.landlordFeedback="";
        this.date=new Date();
    }


    /**
     * Getter for the feedback of the landlord
     * @return String for the feedback of the landlord.
     */
    public String getLandlordFeedback() {
        return landlordFeedback;
    }

    /**
     * Getter for the feedback of the tenant
     * @return String for the feedback of the tenant
     */
    public String getTenantFeedback() {
        return tenantFeedback;
    }

    /**
     * Getter for the landlord in the deal
     * @return User object of the user which represents the landlord in the deal
     */
    public User getLandlord() {
        return landlord;
    }

    /**
     * Getter for the tenant in the deal
     * @return User object of the user which represents the tenant in the deal.
     */
    public User getTenant() {
        return tenant;
    }

    /**
     * Getter for the offer in the deal
     * @return Offer object of the offer in the deal.
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     * Getter for the date in which the deal was closed.
     * @return Date object of the date in which deal is closed.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Override toString method to display renting details.
     * @return Renting details in a string type.
     */
    @Override
    public String toString(){
        return "Offer: "+offer.getTitle()+" Landlord: "+getLandlord().getUsername()+" Tenant: "+tenant.getUsername();
    }
}
