package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.*;
import java.util.function.Consumer;

/**
 * {@link Company} handler
 * @author Robin Görissen, Philipp Xhonneux
 * @version 1.1.0
 */
@SuppressWarnings("ALL")
public class Companies extends Subject implements DataCollection<Company> {

    /**
     * {@link ArrayList} of {@link Company}
     */
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private ArrayList<Company> companies;
    /**
     * {@link String}[] containing the names of columns.
     * Either the standard names or the names of the CSV-File.
     * Names from the CSV-File will be set
     * when using the {@link #add(Collection, String[])} method.
     */
    private String[] columns = new String[]{"ID", "Unternehmen", "Veranstaltung", "Max. Teilnehmer", "Max. Veranstaltung", "Frühster Zeitpunkt"};

    /**
     * Create  an object of type {@link Companies}
     *
     * @param c A {@link Collection} of {@link Observer} that you want like an {@link ArrayList}
     */
    public Companies(Collection<Observer> c) {
        super(c);
        companies = new ArrayList<>();
    }

    /**
     * Adds a {@link Company} and calls {@link Subject#notifyObservers()}.
     * @param company the {@link Companies} that should be added.
     */
    public void add(Company company){
        companies.add(company);
        notifyObservers();
    }

    /**
     * Adds a {@link Company}'es and sets the {@link Companies#columns}. Afterward calls {@link Subject#notifyObservers()}.
     * @param companies a {@link Collection} of {@link Company} that should be added.
     * @param columns {@link String}[] with the columns of the CSV
     */
    public void add(Collection<Company> companies, String[] columns){
        for (Company company : companies){
            add(company);
        }
        this.columns = columns;
        notifyObservers();
    }

    /**
     * Removes the {@link Company} and calls {@link Subject#notifyObservers()}.
     * @param company the {@link Company} that should be removed.
     */
    public void remove(Company company){
        companies.remove(company);
        notifyObservers();
    }

    /**
     * Gets the {@link Company} on the given index
     * @param index of the {@link Company} that should be returned.
     * @return {@link Company}
     */
    public Company get(int index){
        return companies.get(index);
    }

    /**
     * Gets the {@link Company} on the given index and returns the values of the {@link Company} as a {@link String}[]
     * @param index of the {@link Company} that's values should be returned as a {@link String}[].
     * @return {@link String}[] containing the values of the {@link Company}.
     */
    public String[] getArray(int index){
        Company company = companies.get(index);
        return company.ToCSVString(";").split(";");
    }

    /**
     * Gets all {@link Company}'es and returns the values of each as a {@link String}[]
     * @return {@link String}[][] Array of Array that contain the values of {@link Company}'es.
     */
    public String[][] getArrays(){
        String[][] companies = new String[this.companies.size()][];

        for (int i = 0; i< companies.length; i++){
            companies[i] = getArray(i);
        }
        return companies;
    }

    /**
     * Gets {@link #columns}
     * @return {@link #columns}
     */
    public String[] getColumns(){
       return columns;
    }

    /**
     * Changes the {@link Company} at the given index to the given {@link Company} in the inner {@link ArrayList}<{@link Company}>
     * @param index of the {@link Company} that should be changed.
     * @param c {@link Company} to be changed to.
     */
    public void change(int index, Company c) {
        companies.set(index, c);
        System.out.println(index);
        notifyObservers();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Company> iterator() {
        return this.companies.iterator();
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
    public void forEach(Consumer<? super Company> action) {
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
    public Spliterator<Company> spliterator() {
        return DataCollection.super.spliterator();
    }

    public Company getByID(int id) {
        for (Company company : companies){
            if(company.getID() == id)
                return company;
        }
        return null;
    }

    public int size() {
        return companies.size();
    }

    public List<Company> toList() {
        return companies;
    }
}
