package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DealList {
    private final ArrayList<Deal> dealArray;

    public DealList(){
        this.dealArray=new ArrayList<>();
    }


    public ArrayList<Deal> getDealArray() {
        return dealArray;
    }

    public void addDeal(Deal deal){
        this.dealArray.add(deal);
    }


}
