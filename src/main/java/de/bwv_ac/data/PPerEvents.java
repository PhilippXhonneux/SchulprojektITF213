package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;
import de.bwv_ac.view.bot_2.PPEvent.PPEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PPerEvents extends Subject implements DataCollection<PPerEvent> {

    private ArrayList<PPerEvent> pPerEvents;
    private String[] columns = new String[] {"ID", "Veranstaltung", "Anzahl Teilnehmer"};

    /**
     * Create an observable object
     *
     * @param c A collection that you want like an ArrayList
     */
    public PPerEvents(Collection<Observer> c) {
        super(c);
        pPerEvents = new ArrayList<>();
    }

    /**
     * Adds a {@link Datastructure} to the inner {@link Collection}<{@link Datastructure}>
     *
     * @param datastructure that should be added.
     */
    @Override
    public void add(PPerEvent datastructure) {
        pPerEvents.add(datastructure);
        notifyObservers();
    }

    /**
     * Adds alle elements of the given {@link Collection}<{@link Datastructure}> to the inner {@link Collection}<{@link Datastructure}>
     * and set the {@link String}[] for the columns names.
     *
     * @param datastructures {@link Collection} of the {@link Datastructure} that should be added.
     * @param columns        names of the columns.
     */
    @Override
    public void add(Collection<PPerEvent> datastructures, String[] columns) {
        for (PPerEvent pPerEvent : datastructures){
            add(pPerEvent);
        }
        this.columns = columns;
        notifyObservers();
    }

    /**
     * Removes the given {@link Datastructure} from the inner {@link Collection}<{@link Datastructure}>
     *
     * @param datastructure that should be removed.
     */
    @Override
    public void remove(PPerEvent datastructure) {
        pPerEvents.remove(datastructure);
        notifyObservers();
    }

    /**
     * Changes the {@link Datastructure} at the given index to the given {@link Datastructure} in the inner {@link Collection}<{@link Datastructure}>
     *
     * @param index         of the {@link Datastructure} that should be changed.
     * @param datastructure to be changed to.
     */
    @Override
    public void change(int index, PPerEvent datastructure) {
        pPerEvents.set(index, datastructure);
        notifyObservers();

    }

    /**
     * Gets the {@link Datastructure} at the given index
     *
     * @param index of the {@link Datastructure} to be returned
     * @return {@link Datastructure}
     */
    @Override
    public PPerEvent get(int index) {
        return pPerEvents.get(index);
    }

    /**
     * Gets an {@link String}[] of the values as {@link String} from the {@link Datastructure} of the given index.
     *
     * @param index of the {@link Datastructure}
     * @return {@link String}[]
     */
    @Override
    public String[] getArray(int index) {
        return pPerEvents.get(index).ToCSVString(";").split(";");
    }

    /**
     * Gets an Array of {@link String}[] wich contain the values from the {@link Datastructure}, of all {@link Datastructure}'s.
     *
     * @return {@link String}[][]
     */
    @Override
    public String[][] getArrays() {
        String[][] pPerEvents = new String[this.pPerEvents.size()][];

        for (int i = 0; i< pPerEvents.length; i++){
            pPerEvents[i] = getArray(i);
        }
        return pPerEvents;
    }

    /**
     * Gets a {@link String}[] wich contains the names of columns for this {@link Datastructure}
     *
     * @return {@link String}[]
     */
    @Override
    public String[] getColumns() {
        return columns;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<PPerEvent> iterator() {
        return pPerEvents.iterator();
    }

    public void update(Wishes wishes) {

        if(wishes == null)
            return;

        //TODO: algorithm

        PPerEvent test = new PPerEvent();
        test.setID(0);
        test.setEvent("Test");
        test.setCount(25);
        add(test);
        //System.out.println("Algorithm" + wishes.get(0).toString()); // work only if wishes is not null

        notifyObservers();
    }
}
