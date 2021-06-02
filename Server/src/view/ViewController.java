package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

/**
 * Abstract class for the controllers used for the common parts of them.
 */

public abstract class ViewController {
    private Region root;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    /**
     * Abstract method to initialise the controller.
     */
    protected abstract void init();

    /**
     * Three-argument constructor that initialises the controller. The method init() is also called.
     * @param viewHandler The Object to handle views and their environments.
     * @param viewModelFactory The Object to obtain the ViewModel objects.
     * @param root The Object needed to be set for the environment of the controller.
     */
    public void init(ViewHandler viewHandler,ViewModelFactory viewModelFactory,Region root){
        this.viewHandler=viewHandler;
        this.viewModelFactory=viewModelFactory;
        this.root=root;
        init();
    }

    /**
     * Method executed everytime the view and the controller are set.
     */
    public void reset(){

    }

    /**
     * Getter for the Root object.
     * @return The Root object of the controller.
     */
    public Region getRoot() {
        return root;
    }

    /**
     * Getter for the ViewHandler object.
     * @return The ViewHandler object of the controller.
     */
    public ViewHandler getViewHandler() {
        return viewHandler;
    }


    /**
     * Getter for the ViewModelFactory object.
     * @return The ViewModelFactory object of the controller.
     */
    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}