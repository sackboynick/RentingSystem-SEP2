package model;


import java.io.Serializable;
import java.util.Date;

public class Renting  implements Serializable {
    private final User tenant;
    private final User landlord;
    private final Offer offer;
    private final Date date;

    private String tenantFeedback,landlordFeedback;


    public Renting(User tenant,User landlord,Offer offer){
        this.tenant=tenant.copy();
        this.landlord=landlord.copy();
        this.offer=offer;
        this.tenantFeedback="";
        this.landlordFeedback="";
        this.date=new Date();
    }


    public void setLandlordFeedback(String landlordFeedback) {
        this.landlordFeedback = landlord.getUsername()+" said that : "+landlordFeedback;
    }

    public void setTenantFeedback(String tenantFeedback){
        this.tenantFeedback=tenant.getUsername()+" said that : "+tenantFeedback;
    }

    public String getLandlordFeedback() {
        return landlordFeedback;
    }

    public String getTenantFeedback() {
        return tenantFeedback;
    }

    public User getLandlord() {
        return landlord;
    }

    public User getTenant() {
        return tenant;
    }

    public Offer getOffer() {
        return offer;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString(){
        return "Offer: "+offer.getTitle()+" Landlord: "+getLandlord().getUsername()+" Tenant: "+tenant.getUsername();
    }
}
