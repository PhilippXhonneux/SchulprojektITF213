package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Wish Handler
 *
 * @author Philipp Xhonneux
 * @version 1.0.1
 */
public class Wishes extends Subject implements DataCollection<Wish> {

	@SuppressWarnings("FieldMayBeFinal")
	private ArrayList<Wish> wishes;

	private String[] columns = new String[]{"Klasse","Vorname","Name","Wahl1","Wahl2","Wahl3","Wahl4","Wahl5","Wahl6"};


	/**
	 * Create an observable object
	 *
	 * @param c A {@link Collection} that you want like an {@link ArrayList}
	 */
	public Wishes(Collection<Observer> c) {
		super(c);
		wishes  = new ArrayList<>();
	}

	/**
	 * Adds a Wish and calls {@link Subject}.notifyObservers().
	 * @param wish the {@link Wish} that should be added.
	 */
	public void add(Wish wish)
	{
		this.wishes.add(wish);
		this.notifyObservers();
	}

	/**
	 * Adds a Wishes and sets the columns. Afterward calls {@link Subject}.notifyObservers().
	 * @param wishes a {@link Collection} of wishes that should be added.
	 * @param columns {@link String}[] with the columns of the CSV
	 */
	public void add(Collection<Wish> wishes, String[] columns)
	{
		this.wishes.addAll(wishes);
		this.columns = columns;
		this.notifyObservers();
	}

	/**
	 * Removes the wish and calls {@link Subject}.notifyObservers().
	 * @param wish the {@link Wish} that should be removed.
	 */
	public void remove(Wish wish)
	{
		this.wishes.remove(wish);
		this.notifyObservers();
	}

	@Override
	public void change(int index, Wish wish) {
		this.wishes.set(index, wish);
		System.out.println(index);
		notifyObservers();
	}

	/**
	 * Gets the wish on the given index
	 * @param index of the {@link Wish} that should be returned.
	 * @return {@link Wish}
	 *
	 */
	public Wish get(int index)
	{
		return this.wishes.get(index);
	}

	/**
	 * Gets the wish on the given index and returns the values of the wish as a {@link String}[]
	 * @param index of the {@link Wish} that's values should be returned as a {@link String}[].
	 * @return {@link String}[] containing the values of the {@link Wish}.
	 */
	public String[] getArray(int index)
	{
		Wish wish = this.wishes.get(index);
		return wish.ToCSVString(";").split(";");
	}

	/**
	 * Gets all wishes and returns the values of each as a {@link String}[]
	 * @return {@link String}[][] Array of Array that contain the values of {@link Wish}es.
	 */
	public String[][] getArrays()
	{
		String[][] wishes = new String[this.wishes.size()][];

		for (int i = 0; i< wishes.length; i++){
			wishes[i] = getArray(i);
		}
		return wishes;
	}

	/**
	 * Gets the columns
	 * @return {@link String}[] columns
	 */
	public String[] getColumns()
	{
		return this.columns;
	}


}
