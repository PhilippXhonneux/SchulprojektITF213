package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Philipp Xhonneux
 * @version 1.1.0
 */
public class Assignments extends Subject implements DataCollection<Assignment> {

	@SuppressWarnings("FieldMayBeFinal")
	private ArrayList<Assignment> assignments = new ArrayList<>();

	private String[] columns = {};

	/**
	 * Create an observable object
	 *
	 * @param c A collection that you want like an ArrayList
	 */
	public Assignments(Collection<Observer> c) {
		super(c);
	}

	/**
	 * Adds a {@link Datastructure} to the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param datastructure that should be added.
	 */
	@Override
	public void add(Assignment datastructure) {
		this.assignments.add(datastructure);
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
	public void add(Collection<Assignment> datastructures, String[] columns) {
		this.assignments.addAll(datastructures);
		this.columns = columns;
		notifyObservers();

	}

	/**
	 * Removes the given {@link Datastructure} from the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param datastructure that should be removed.
	 */
	@Override
	public void remove(Assignment datastructure) {
		this.assignments.remove(datastructure);
		notifyObservers();
	}

	/**
	 * Changes the {@link Datastructure} at the given index to the given {@link Datastructure} in the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param index         of the {@link Datastructure} that should be changed.
	 * @param datastructure to be changed to.
	 */
	@Override
	public void change(int index, Assignment datastructure) {
		this.assignments.set(index, datastructure);
		notifyObservers();
	}

	/**
	 * Gets the {@link Datastructure} at the given index
	 *
	 * @param index of the {@link Datastructure} to be returned
	 * @return {@link Datastructure}
	 */
	@Override
	public Assignment get(int index) {
		return this.assignments.get(index);
	}

	/**
	 * Gets an {@link String}[] of the values as {@link String} from the {@link Datastructure} of the given index.
	 *
	 * @param index of the {@link Datastructure}
	 * @return {@link String}[]
	 */
	@Override
	public String[] getArray(int index) {
		return this.assignments.get(index).ToCSVString(";").split(";");
	}

	/**
	 * Gets an Array of {@link String}[] wich contain the values from the {@link Datastructure}, of all {@link Datastructure}'s.
	 *
	 * @return {@link String}[][]
	 */
	@Override
	public String[][] getArrays() {
		String[][] assignments = new String[this.assignments.size()][];

		for (int i = 0; i< assignments.length; i++){
			assignments[i] = getArray(i);
		}
		return assignments;
	}

	/**
	 * Gets a {@link String}[] wich contains the names of columns for this {@link Datastructure}
	 *
	 * @return {@link String}[]
	 */
	@Override
	public String[] getColumns() {
		return this.columns;
	}

	/**
	 * Gives back the number of {@link Datastructure}'s.
	 *
	 * @return {@link int}
	 */
	@Override
	public int size() {
		return this.assignments.size();
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<Assignment> iterator() {
		return this.assignments.iterator();
	}
}
