package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Philipp Xhonneux
 *
 * @version 0.2.0
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

	public void addWish(Wish wish) throws ExecutionControl.NotImplementedException
	{
		throw new ExecutionControl.NotImplementedException("");
	}

	public void addWishes(Collection<Wishes> wishes, String[] columns) throws ExecutionControl.NotImplementedException
	{
		throw new ExecutionControl.NotImplementedException("");
	}

	public void removeWish(Wish wish) throws ExecutionControl.NotImplementedException
	{
		throw new ExecutionControl.NotImplementedException("");
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
