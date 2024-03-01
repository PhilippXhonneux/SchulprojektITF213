package de.bwv_ac.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * {@link Room} Handler
 * @author Philipp Xhonneux
 * @version 1.0.0
 */
public class Rooms implements DataCollection<Room>{

	private ArrayList<Room> rooms = new ArrayList<Room>();

	private String[] columns = {};

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
	}

	/**
	 * Removes the given {@link Datastructure} from the inner {@link Collection}<{@link Datastructure}>
	 *
	 * @param datastructure that should be removed.
	 */
	@Override
	public void remove(Room datastructure) {
		this.rooms.remove(datastructure);
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
}
