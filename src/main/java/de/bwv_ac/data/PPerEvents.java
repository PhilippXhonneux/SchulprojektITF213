package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.*;

/**
 * @author Robin Görißen , Philipp Xhonneux
 * @version 1.1.0
 */
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
     * Gives back the number of {@link Datastructure}'s.
     *
     * @return {@link int}
     */
    @Override
    public int size() {
        return this.pPerEvents.size();
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

    synchronized public void update(Wishes wishes, Companies companies) {

        if(wishes == null)
            return;

        pPerEvents.clear();

        // TODO Create commentary
        for (Wish wish : wishes){
            ArrayList<Integer> wishesA = new ArrayList<>();
            int wish1 = wish.getSelection(Wish.Selection.Wish1);
            int wish2 = wish.getSelection(Wish.Selection.Wish2);
            int wish3 = wish.getSelection(Wish.Selection.Wish3);
            int wish4 = wish.getSelection(Wish.Selection.Wish4);
            int wish5 = wish.getSelection(Wish.Selection.Wish5);
            int extraWish = wish.getSelection(Wish.Selection.extraWish);

            wishesA.add(wish1);
            wishesA.add(wish2);
            wishesA.add(wish3);
            wishesA.add(wish4);
            wishesA.add(wish5);
            wishesA.add(extraWish);
            for (int wishI : wishesA){
                if(wishI == 0)
                    continue;
                if(getById(wishI) != null){
                    getById(wishI).setCount(getById(wishI).getCount()+1);
                }else {
                    PPerEvent pPerEvent = new PPerEvent();
                    pPerEvent.setID(wishI);
                    pPerEvent.setEvent(companies.getByID(wishI).getName());
                    pPerEvent.setCount(1);
                    pPerEvents.add(pPerEvent);
                }
            }
        }


        PPerEvent[] sorted = new PPerEvent[pPerEvents.size()];
        int c = 0;
        for (PPerEvent pPerEvent : pPerEvents){
            sorted[c] = pPerEvent;
            c++;
        }
        Arrays.sort(sorted);

        pPerEvents.clear();
        for (PPerEvent pPerEvent : sorted){
            pPerEvents.add(pPerEvent);
        }

        notifyObservers();
    }

    public PPerEvent getById(int id) {
        for (PPerEvent pPerEvent : pPerEvents){
            if(pPerEvent.getID() == id)
                return pPerEvent;
        }
        return null;
    }

}
