package de.bwv_ac.data;

public class PPerEvent extends Datastructure implements Comparable{

    /**
     * ID of the event
     */
    private int ID;

    /**
     * Name of the even
     */
    private String event;

    /**
     * Count of all participans of this event
     */
    private int count;

    /**
     * Get the id of the event
     * @return The id
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the id of the event
     * @param ID The id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get the name of the event
     * @return The name
     */
    public String getEvent() {
        return event;
    }

    /**
     * Set the name of the event
     * @param event The name
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Get the count of patricians in this event
     * @return The count
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the count of participants in the event
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof PPerEvent){
            PPerEvent pPerEvent = (PPerEvent) o;
            int id = pPerEvent.getID();
            return Integer.compare(this.getID(), id);
        }
        throw new NullPointerException();
    }
}
