package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.ArrayList;
import java.util.Collection;


/**
 * {@link Timetable} Handler
 *
 * @author Philipp Xhonneux
 * @version 1.0.0
 */
public class Timetables extends Subject implements DataCollection<Timetable> {

	/**
	 * List of {@link Timetable} for {@link Company}
	 */
	@SuppressWarnings("FieldMayBeFinal")
	private ArrayList<Timetable> timetables;

	/**
	 * {@link String}[] containing the names of columns.
	 * Either the standard names or the names of the CSV-File.
	 * Names from the CSV-File will be set
	 * when using the {@link #add(Collection, String[])} method.
	 */
	private String[] columns = new String[]{"ID","Firma","Name","Veranstaltung1","Veranstaltung2","Veranstaltung3","Veranstaltung4","Veranstaltung5","Veranstaltung6"};

	/**
	 * Create an observable object
	 *
	 * @param c A collection that you want like an ArrayList
	 */
	public Timetables(Collection<Observer> c) {
		super(c);
	}


	/**
	 * Adds a {@link Timetable} to the inner {@link Collection}<{@link Timetable}>
	 *
	 * @param datastructure that should be added.
	 */
	@Override
	public void add(Timetable datastructure) {
		this.timetables.add(datastructure);
		notifyObservers();
	}

	/**
	 * Adds alle elements of the given {@link Collection}<{@link Timetable}> to the inner {@link Collection}<{@link Timetable}>
	 * and set the {@link String}[] for the columns names.
	 *
	 * @param datastructures {@link Collection} of the {@link Timetable} that should be added.
	 * @param columns        names of the columns.
	 */
	@Override
	public void add(Collection<Timetable> datastructures, String[] columns) {
		this.timetables.addAll(datastructures);
		this.columns = columns;
		notifyObservers();
	}

	/**
	 * Removes the given {@link Timetable} from the inner {@link Collection}<{@link Timetable}>
	 *
	 * @param datastructure that should be removed.
	 */
	@Override
	public void remove(Timetable datastructure) {
		this.timetables.remove(datastructure);
		notifyObservers();
	}

	/**
	 * Changes the {@link Timetable} at the given index to the given {@link Timetable} in the inner {@link Collection}<{@link Timetable}>
	 *
	 * @param index         of the {@link Timetable} that should be changed.
	 * @param datastructure to be changed to.
	 */
	@Override
	public void change(int index, Timetable datastructure) {
		this.timetables.set(index, datastructure);
		System.out.println(index);
		notifyObservers();
	}

	/**
	 * Gets the {@link Timetable} at the given index
	 *
	 * @param index of the {@link Timetable} to be returned
	 * @return {@link Timetable}
	 */
	@Override
	public Timetable get(int index) {
		return this.timetables.get(index);
	}

	/**
	 * Gets an {@link String}[] of the values as {@link String} from the {@link Timetable} of the given index.
	 *
	 * @param index of the {@link Timetable}
	 * @return {@link String}[]
	 */
	@Override
	public String[] getArray(int index) {
		Timetable tt = this.timetables.get(index);
		String[] re = new String[9];
		re[0] = tt.getID().toString();
		re[1] = tt.getCompany();

		try {
			re[2] = tt.getEvent(0)[1] + " " + tt.getEvent(0)[0];
		}catch(IllegalArgumentException e){
			re[2] = "";
		}
		try {
			re[3] = tt.getEvent(1)[1] + " " + tt.getEvent(1)[0];
		}catch(IllegalArgumentException e){
			re[3] = "";
		}
		try {
			re[4] = tt.getEvent(2)[1] + " " + tt.getEvent(2)[0];
		}catch(IllegalArgumentException e){
			re[4] = "";
		}
		try {
			re[5] = tt.getEvent(3)[1] + " " + tt.getEvent(3)[0];
		}catch(IllegalArgumentException e){
			re[5] = "";
		}
		try {
			re[6] = tt.getEvent(4)[1] + " " + tt.getEvent(4)[0];
		}catch(IllegalArgumentException e){
			re[6] = "";
		}
		try {
			re[7] = tt.getEvent(5)[1] + " " + tt.getEvent(5)[0];
		}catch(IllegalArgumentException e){
			re[7] = "";
		}
		try {
			re[8] = tt.getEvent(6)[1] + " " + tt.getEvent(6)[0];
		}catch(IllegalArgumentException e){
			re[8] = "";
		}

		return re;

	}

	/**
	 * Gets an Array of {@link String}[] wich contain the values from the {@link Timetable}, of all {@link Timetable}'s.
	 *
	 * @return {@link String}[][]
	 */
	@Override
	public String[][] getArrays() {
		String[][] timetables = new String[this.timetables.size()][];

		for (int i = 0; i< timetables.length; i++){
			timetables[i] = getArray(i);
		}
		return timetables;
	}

	/**
	 * Gets a {@link String}[] wich contains the names of columns for this {@link Timetable}
	 *
	 * @return {@link String}[]
	 */
	@Override
	public String[] getColumns() {
		return columns;
	}
}
