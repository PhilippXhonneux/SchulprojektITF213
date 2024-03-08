package de.bwv_ac.data;

import java.util.Collection;

/**
 * Interface for all {@link Collection} of {@link Datastructure} that extend {@link de.bwv_ac.util.Subject}
 * @param <T> extends {@link Datastructure}
 *
 * @author Philipp Xhonneux
 * @version 1.0.0
 */
public interface DataCollection<T extends Datastructure> extends Iterable<T> {

    /**
     * Adds a {@link Datastructure} to the inner {@link Collection}<{@link Datastructure}>
     * @param datastructure that should be added.
     */
    void add(T datastructure);

    /**
     * Adds alle elements of the given {@link Collection}<{@link Datastructure}> to the inner {@link Collection}<{@link Datastructure}>
     * and set the {@link String}[] for the columns names.
     * @param datastructures {@link Collection} of the {@link Datastructure} that should be added.
     * @param columns names of the columns.
     */
    void add(Collection<T> datastructures, String[] columns);

    /**
     * Removes the given {@link Datastructure} from the inner {@link Collection}<{@link Datastructure}>
     * @param datastructure that should be removed.
     */
    void remove(T datastructure);

    /**
     * Changes the {@link Datastructure} at the given index to the given {@link Datastructure} in the inner {@link Collection}<{@link Datastructure}>
     * @param index of the {@link Datastructure} that should be changed.
     * @param datastructure to be changed to.
     */
    void change(int index, T datastructure);

    /**
     * Gets the {@link Datastructure} at the given index
     * @param index of the {@link Datastructure} to be returned
     * @return {@link Datastructure}
     */
    T get(int index);

    /**
     * Gets an {@link String}[] of the values as {@link String} from the {@link Datastructure} of the given index.
     * @param index of the {@link Datastructure}
     * @return {@link String}[]
     */
    String[] getArray(int index);

    /**
     * Gets an Array of {@link String}[] wich contain the values from the {@link Datastructure}, of all {@link Datastructure}'s.
     * @return {@link String}[][]
     */
    String[][] getArrays();

    /**
     * Gets a {@link String}[] wich contains the names of columns for this {@link Datastructure}
     * @return {@link String}[]
     */
    String[] getColumns();

}
