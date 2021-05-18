package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Offer;
import viewmodel.ViewState;

public class OffersListViewController extends ViewController{
    @FXML
    private ListView<Offer> offerListView;

    @Override
    protected void init() {
        this.offerListView.setItems(getViewModelFactory().getOffersListViewModel().getOffers());
    }

    public void reset(){

    }

    @FXML public void back(){
        getViewHandler().openView("homePage");
    }

    @FXML public void publishOffer(){
        getViewHandler().openView("publishOffer");
    }

    @FXML public void openOfferInterface(){
        Offer offer=this.offerListView.getSelectionModel().getSelectedItem();
        if(offer!=null){
            ViewState.getInstance().setOffer(offer);
            getViewModelFactory().getOfferViewViewModel().setInterface();
            getViewHandler().openView("offerView");
        }
    }
}
