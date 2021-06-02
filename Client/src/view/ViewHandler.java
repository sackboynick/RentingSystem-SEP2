package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

/**
 * The class is used to set or initialize different views and controllers.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ViewHandler extends ViewCreator
{
    private Stage primaryStage;
    private Scene currentScene;
    private final ViewModelFactory viewModelFactory;

    /**
     * One-argument constructor.
     * @param viewModelFactory The ViewModelFactory object to be set in the ViewHandler.
     */
    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
    }

    /**
     * First method that is executed when the system is ran, it determines the first view in the system.
     * @param primaryStage Stage object for the stage to be set.
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openView("Login");
    }

    /**
     * The method is used to change the current view and system and to display a new one using an ID to identify the correct view.
     * @param id The ID of the view that has to be set.
     */
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

    /**
     * This method closes the views and interrupts the scene.
     */
    public void close(){
        this.primaryStage.close();
    }


    /**
     * This method is used to initialize the a ViewController with the Root object.
     * @param controller ViewController object
     * @param root Root object
     */
    @Override
    protected void initViewController(ViewController controller, Region root) {
        controller.init(this,viewModelFactory,root);
    }
}