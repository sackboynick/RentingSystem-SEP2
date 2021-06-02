package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The class is used to store methods to initialise a controller and to set its environment to a FXML file.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public abstract class ViewCreator {
    private final Map<String, ViewController> controllers = new HashMap<>();

    /**
     * Zero-argument constructor
     */
    public ViewCreator() {

    }

    /**
     * The method returns the ViewController object corresponding to the provided ID from the controllers HashMap.
     * @param id The ID of the view that has to be set.
     * @return The ViewController object of the controller for the view.
     */
    public ViewController getViewController(String id) {
        ViewController viewController=controllers.get(id);
        if (viewController==null){
            try {
                viewController=loadFromFXML(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            controllers.put(id,viewController);
        }
        else
            viewController.reset();
        return viewController;
    }

    /**
     * The method returns the object of the ViewController once its connected to the FXML file corresponding to the provided ID.
     * @param id The ID of the view that has to be set.
     * @return The ViewController object of the controller of this view.
     * @throws IOException If there are issues during the connection to the server.
     */
    protected ViewController loadFromFXML(String id) throws IOException {
        String fxmlFile = switch (id) {
            case "Login" -> "loginView.fxml";
            case "signUp" -> "signUpView.fxml";
            case "homePage" -> "homePageView.fxml";
            case "userInterface" -> "clientUserView.fxml";
            case "offersList" -> "clientOffersListView.fxml";
            case "publishOffer" -> "publishOfferView.fxml";
            case "offerView" -> "clientOfferView.fxml";
            case "sendMessageView" -> "sendMessageView.fxml";
            case "rentingListView" -> "clientRentingListView.fxml";
            case "rentingView" -> "clientRentingView.fxml";
            case "messageView" -> "messageView.fxml";
            case "userOffersList" -> "userOffersList.fxml";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root=loader.load();
        ViewController viewController=loader.getController();
        initViewController(viewController, root);
        return viewController;
    }

    /**
     * The abstract method is used to initialize a controller.
     * @param controller ViewController object
     * @param root Root object
     */
    protected abstract void initViewController(ViewController controller, Region root);

}