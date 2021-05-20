import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RmiClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException {
    RmiClient rmiClient=new RmiClient();
    Model model = new ModelManager(rmiClient);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);
  }
}
