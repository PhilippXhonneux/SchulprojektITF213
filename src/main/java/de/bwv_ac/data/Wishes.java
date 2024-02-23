package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Wish Handler
 *
 * @author Philipp Xhonneux
 * @version 1.0.0
 */
public class Wishes extends Subject {

	private ArrayList<Wish> wishes = new ArrayList<>();
	private String[] columns;


	/**
	 * Create an observable object
	 *
	 * @param c A collection that you want like an ArrayList
	 */
	public Wishes(Collection<Observer> c) {
		super(c);
	}

	/**
	 * Adds a Wish and calls {@link Subject}.notifyObservers().
	 * @param wish
	 */
	public void addWish(Wish wish)
	{
		this.wishes.add(wish);
		this.notifyObservers();
	}

	/**
	 * Adds a Wishes and sets the columns. Afterward calls {@link Subject}.notifyObservers().
	 * @param wishes
	 * @param columns
	 */
	public void addWishes(Collection<Wish> wishes, String[] columns)
	{
		this.wishes.addAll(wishes);
		this.columns = columns;
		this.notifyObservers();
	}

	/**
	 * Removes the wish and calls {@link Subject}.notifyObservers().
	 * @param wish
	 */
	public void removeWish(Wish wish)
	{
		this.wishes.remove(wish);
		this.notifyObservers();
	}

	/**
	 * Gets the wish on the given index
	 * @param index
	 * @return
	 *
	 */
	public Wish getWish(int index)
	{
		return this.wishes.get(index);
	}

	/**
	 * Gets the wish on the given index and returns the values of the wish as a {@link String}[]
	 * @param index
	 * @return
	 */
	public String[] getWishArray(int index)
	{
		Wish wish = this.wishes.get(index);
		return wish.ToCSVString(";").split(";");
	}

	/**
	 * Gets all wishes and returns the values of each as a {@link String}[]
	 * @return String[][]
	 */
	public String[][] getWishesArray()
	{
		String[][] wishes = new String[this.wishes.size()][];

		for (int i = 0; i< wishes.length; i++){
			wishes[i] = getWishesArray(i);
		}
		return wishes;
	}

	/**
	 * Gets the columns
	 * @return
	 */
	public String[] getColumns()
	{
		return this.columns;
	}


}
