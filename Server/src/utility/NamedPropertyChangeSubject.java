package utility;

import java.beans.PropertyChangeListener;

/**
 * Interface for Property Subjects in the Observer pattern that adds and removes listeners to the properties.
 */

public interface NamedPropertyChangeSubject {
    /**
     * Method to add a listener to the PropertyChangeSupport object.
     *
     * @param propertyName String containing the name of the property the listener will listen to.
     * @param listener     PropertyChangeListener object which represents the listener to the property.
     */
    void addListener(String propertyName, PropertyChangeListener listener);
    /**
     * Method to remove a listener to the PropertyChangeSupport object.
     *
     * @param propertyName String containing the name of the property the listener will stop to listen to.
     * @param listener     PropertyChangeListener object which represents the listener to the property.
     */
    void removeListener(String propertyName, PropertyChangeListener listener);
}