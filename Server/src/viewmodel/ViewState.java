package viewmodel;

import model.Offer;
import model.Renting;
import model.User;

/**
 * The class represents the State of the views, storing the information needed to display the details of users, offers or
 * renting deals. The class follows the Singleton pattern.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ViewState {
    private static ViewState instance;
    private User user;
    private Offer offer;
    private User displayedUser;
    private Renting displayedRenting;

    /**
     * Zero-argument private constructor.
     */
    private ViewState(){}

    /**
     * Getter for the instance of the ViewState object.
     * @return The stored instance of the ViewState object.
     */
    public static ViewState getInstance(){
        if(instance==null)
            instance=new ViewState();
        return instance;
    }

    /**
     * Setter for the displayed renting in the state.
     * @param displayedRenting Renting object to set in the state.
     */
    public void setDisplayedRenting(Renting displayedRenting) {
        this.displayedRenting = displayedRenting;
    }

    /**
     * Getter for the displayed renting in the state.
     * @return Renting object to get from the state.
     */
    public Renting getDisplayedRenting() {
        return displayedRenting;
    }

    /**
     * Setter for the user in the state.
     * @param user User object to set in the state.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Setter for the offer in the state.
     * @param offer Offer object to set in the state.
     */
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    /**
     * Setter for the displayed user in the state.
     * @param displayedUser User object to set in the state.
     */
    public void setDisplayedUser(User displayedUser) {
        this.displayedUser = displayedUser;
    }


    /**
     * Getter for the user in the state.
     * @return User object to get from the state.
     */
    public User getUser() {
        return user;
    }

    /**
     * Getter for the offer in the state.
     * @return Offer object to get from the state.
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     * Getter for the displayed user in the state.
     * @return User object to get from the state.
     */
    public User getDisplayedUser() {
        return displayedUser;
    }


}
