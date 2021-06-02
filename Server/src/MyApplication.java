import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RmiServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;


/**
 * The class represents the Application that will be launched, creating the objects needed to create the correct
 * environment for the system.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class MyApplication extends Application
{
  /**
   * The method starts and initializes all the objects needed to start the system.
   * @param primaryStage Stage object to set in the system.
   */
  public void start(Stage primaryStage) {
    Model model=new ModelManager();
    RmiServer rmiServer=new RmiServer(model);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

  }
}
