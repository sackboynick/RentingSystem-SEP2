package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator {
    private final Map<String, ViewController> controllers = new HashMap<>();

    public ViewCreator() {

    }

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

    protected ViewController loadFromFXML(String id) throws IOException {
        String fxmlFile = switch (id) {
            case "mainView" -> "mainView.fxml";
            case "userInterface" -> "userView.fxml";
            case "offerView" -> "offerView.fxml";
            case "usersList" -> "usersListView.fxml";
            case "offersList" -> "offersListView.fxml";
            case "serverOverview" -> "serverOverview.fxml";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root=loader.load();
        ViewController viewController=loader.getController();
        initViewController(viewController, root);
        return viewController;
    }

    protected abstract void initViewController(ViewController controller, Region root);

}