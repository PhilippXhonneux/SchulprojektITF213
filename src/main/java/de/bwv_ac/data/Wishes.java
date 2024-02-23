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
 * @version 0.5.0
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

	public Wish getWish(int index) throws ExecutionControl.NotImplementedException
	{
		throw new ExecutionControl.NotImplementedException("");
	}

	public String[] getWishArray(int index) throws ExecutionControl.NotImplementedException
	{
		throw new ExecutionControl.NotImplementedException("");
	}

	public String[][] getWishesArray() throws ExecutionControl.NotImplementedException
	{
		throw  new ExecutionControl.NotImplementedException("");
	}

	public String[] getColumns() throws ExecutionControl.NotImplementedException
	{
		throw new ExecutionControl.NotImplementedException("");
	}


}
