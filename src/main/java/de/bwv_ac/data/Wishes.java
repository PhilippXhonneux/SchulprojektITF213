package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Philipp Xhonneux
 */
public class Wishes extends Subject {

	private ArrayList<Wish> wishes = new ArrayList<>();


	/**
	 * Create an observable object
	 *
	 * @param c A collection that you want like an ArrayList
	 */
	public Wishes(Collection<Observer> c) {
		super(c);
	}
}
