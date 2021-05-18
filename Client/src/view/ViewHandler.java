package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler extends ViewCreator
{
    private Stage primaryStage;
    private Scene currentScene;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openView("Login");
    }

    public void openView(String id) {
        Region root=getViewController(id).getRoot();
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    public void close(){
        this.primaryStage.close();
    }


    @Override
    protected void initViewController(ViewController controller, Region root) {
        controller.init(this,viewModelFactory,root);
    }
}