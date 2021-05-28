package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Offer;

public class PublishOfferViewController extends ViewController{
    @FXML
    private TextField title, pricePerMonth, deposit, address, area, floor, numberOfRooms;
    @FXML private CheckBox pets, smoking, balcony, freeWiFi;
    @FXML private TextArea description;
    @FXML private ChoiceBox<String> type;

    @Override
    protected void init() {
        title.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getTitle());
        description.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getDescription());
        deposit.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getDeposit());
        pricePerMonth.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getPricePerMonth());
        address.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getAddress());
        area.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getArea());
        floor.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getFloor());
        numberOfRooms.textProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getNumberOfRooms());
        type.accessibleTextProperty().bindBidirectional(getViewModelFactory().getPublishOfferViewModel().getType());

        pets.selectedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                getViewModelFactory().getPublishOfferViewModel().isPets().set(aBoolean);
            }
        });
        freeWiFi.selectedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                getViewModelFactory().getPublishOfferViewModel().isFreeWifi().set(aBoolean);
            }
        });
        smoking.selectedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                getViewModelFactory().getPublishOfferViewModel().isSmoking().set(aBoolean);
            }
        });
        balcony.selectedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                getViewModelFactory().getPublishOfferViewModel().isBalcony().set(aBoolean);
            }
        });
        reset();
    }

    public void reset(){
        title.setText("");
        description.setText("");
        pricePerMonth.setText("");
        deposit.setText("");
        address.setText("");
        area.setText("");
        floor.setText("");
        numberOfRooms.setText("");
        pets.setSelected(false);
        smoking.setSelected(false);
        balcony.setSelected(false);
        freeWiFi.setSelected(false);
        type.setItems(FXCollections.observableArrayList(Offer.getTYPE()));
    }

    @FXML public void createOffer(){
        getViewModelFactory().getPublishOfferViewModel().publishOffer();
        getViewHandler().openView("offersList");
    }

    @FXML public void onBack(){

        getViewHandler().openView("offersList");
    }
}
