package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * {@link Room} Handler
 * @author Philipp Xhonneux
 * @version 1.1.0
 */
@SuppressWarnings("ALL")
public class Rooms extends Subject implements DataCollection<Room>{

	/**
	 * List of {@link Room}
	 */
	@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
	private ArrayList<Room> rooms = new ArrayList<>();

	/**
	 * {@link String}[] containing the names of columns.
	 * Either the standard names or the names of the CSV-File.
	 * Names from the CSV-File will be set
	 * when using the {@link #add(Collection, String[])} method.
	 */
	private String[] columns = {"Raumbezeichnung", "Raumkapazität"};

	/**
	 * Create an observable object
	 *
	 * @param c A collection that you want like an ArrayList
	 */
	public Rooms(Collection<Observer> c) {
		super(c);
	}

	/**
	 * Adds a {@link Datastructure} to the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param datastructure that should be added.
	 */
	@Override
	public void add(Room datastructure) {
		this.rooms.add(datastructure);
	}

	/**
	 * Adds alle elements of the given {@link Collection}<{@link Datastructure}> to the inner {@link Collection}<{@link Datastructure}>
	 * and set the {@link String}[] for the columns names.
	 *
	 * @param datastructures {@link Collection} of the {@link Datastructure} that should be added.
	 * @param columns        names of the columns.
	 */
	@Override
	public void add(Collection<Room> datastructures, String[] columns) {
		this.rooms.addAll(datastructures);
		this.columns = columns;
		notifyObservers();
	}

	/**
	 * Removes the given {@link Datastructure} from the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param datastructure that should be removed.
	 */
	@Override
	public void remove(Room datastructure) {
		this.rooms.remove(datastructure);
		notifyObservers();
	}

	/**
	 * Changes the {@link Datastructure} at the given index to the given {@link Datastructure} in the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param index         of the {@link Datastructure} that should be changed.
	 * @param datastructure to be changed to.
	 */
	@Override
	public void change(int index, Room datastructure) {
		this.rooms.set(index, datastructure);
		notifyObservers();
	}

	/**
	 * Gets the {@link Datastructure} at the given index
	 *
	 * @param index of the {@link Datastructure} to be returned
	 * @return {@link Datastructure}
	 */
	@Override
	public Room get(int index) {
		return this.rooms.get(index);
	}

	/**
	 * Gets an {@link String}[] of the values as {@link String} from the {@link Datastructure} of the given index.
	 *
	 * @param index of the {@link Datastructure}
	 * @return {@link String}[]
	 */
	@Override
	public String[] getArray(int index) {
		String[] re = new String[2];
		Room ro = this.rooms.get(index);
		re[0] = ro.getRoomname();
		re[1] = ro.getCapacity().toString();

		return re;
	}

	/**
	 * Gets an Array of {@link String}[] wich contain the values from the {@link Datastructure}, of all {@link Datastructure}'s.
	 *
	 * @return {@link String}[][]
	 */
	@Override
	public String[][] getArrays() {
		String[][] re = new String[this.rooms.size()][];
		for(int i = 0; i < this.rooms.size(); i++)
			re[i] = getArray(i);

		return re;
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
		return this.rooms.size();
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<Room> iterator() {
		return this.rooms.iterator();
	}

	/**
	 * Performs the given action for each element of the {@code Iterable}
	 * until all elements have been processed or the action throws an
	 * exception.  Actions are performed in the order of iteration, if that
	 * order is specified.  Exceptions thrown by the action are relayed to the
	 * caller.
	 * <p>
	 * The behavior of this method is unspecified if the action performs
	 * side-effects that modify the underlying source of elements, unless an
	 * overriding class has specified a concurrent modification policy.
	 *
	 * @param action The action to be performed for each element
	 * @throws NullPointerException if the specified action is null
	 * @implSpec <p>The default implementation behaves as if:
	 * <pre>{@code
	 *     for (T t : this)
	 *         action.accept(t);
	 * }</pre>
	 * @since 1.8
	 */
	@Override
	public void forEach(Consumer<? super Room> action) {
		DataCollection.super.forEach(action);
	}

	/**
	 * Creates a {@link Spliterator} over the elements described by this
	 * {@code Iterable}.
	 *
	 * @return a {@code Spliterator} over the elements described by this
	 * {@code Iterable}.
	 * @implSpec The default implementation creates an
	 * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
	 * spliterator from the iterable's {@code Iterator}.  The spliterator
	 * inherits the <em>fail-fast</em> properties of the iterable's iterator.
	 * @implNote The default implementation should usually be overridden.  The
	 * spliterator returned by the default implementation has poor splitting
	 * capabilities, is unsized, and does not report any spliterator
	 * characteristics. Implementing classes can nearly always provide a
	 * better implementation.
	 * @since 1.8
	 */
	@Override
	public Spliterator<Room> spliterator() {
		return DataCollection.super.spliterator();
	}
}
