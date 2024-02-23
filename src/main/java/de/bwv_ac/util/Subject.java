package de.bwv_ac.util;

import java.util.Collection;
/**
 * Observer Subject
 *
 * @author Robin Goerissen
 * @version 1.0.0
 */
@SuppressWarnings({"FieldMayBeFinal"})
public abstract class Subject {
    //	chosen collection
    private Collection<Observer> observers;

    /**
     * Create an observable object
     * @param c A collection that you want like an ArrayList
     */
    public Subject(Collection<Observer> c) {
        observers = c;
    }

    /**
     * Adds a listener
     * @param obs An Object which implements Observer
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * Remove a listener
     * @param obs An Object which implements Observer
     */
    public void deleteObserver(Observer obs) {
        observers.remove(obs);
    }

    /**
     * Inform all listeners
     */
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }
}
