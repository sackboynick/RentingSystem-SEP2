package model;

import java.util.Date;

public class Deal {
    private final Offer offer;
    private final User landlord,tenant;
    private final Date date;

    public Deal(Offer offer,User landlord,User tenant){
        this.landlord=landlord;
        this.tenant=tenant;
        this.offer=offer;
        this.date=new Date();
    }

    public Offer getOffer() {
        return offer;
    }

    public User getLandlord() {
        return landlord;
    }

    public User getTenant() {
        return tenant;
    }

    public Date getDate() {
        return date;
    }

    public String toString(){
        return offer.getTitle()+"; T: "+tenant.getUsername()+"; L: "+landlord.getUsername();
    }
}
