package de.bwv_ac.data;

/**
 * Datastructure for Wishes BOT2
 *
 * @author Philipp Goebel, Philipp Xhonneux
 * @version 2.0.0
 */
public class Wish extends Datastructure {
    /**
     * Name of the class the student is in.
     */
    private String className;
    /**
     * Surname of the student.
     */
    private String surname;
    /**
     * Name of the student.
     */
    private String name;
    /**
     * First choice of company of the student.
     * Contains the {@link Company#getID()}.
     */
    private int selection1;
    /**
     * Second choice of company of the student.
     * Contains the {@link Company#getID()}.
     */
    private int selection2;
    /**
     * Third choice of company of the student.
     * Contains the {@link Company#getID()}.
     */
    private int selection3;
    /**
     * Fourth choice of company of the student.
     * Contains the {@link Company#getID()}.
     */
    private int selection4;
    /**
     * Fifth choice of company of the student.
     * Contains the {@link Company#getID()}.
     */
    private int selection5;
    /**
     * Sixth choice of company of the student.
     * Contains the {@link Company#getID()}.
     */
    private int extraWish;

    /**
     * Get the name of the class the student is in.
     * @return classname
     */
    public String getClassName() {
        return className;
    }

    /**
     * Set the name of the class the student is in.
     * @param className name of the class
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets the surname of the Student
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname of the student.
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the name of the student.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the student.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the selection based on the index.
     * @param index of the selection. Can only be 0-5
     * @return {@link Company#getID()}
     * @throws IllegalArgumentException
     */
    public int getSelection(int index) throws IllegalArgumentException
    {
        return switch (index) {
            case 0 -> this.selection1;
            case 1 -> this.selection2;
            case 2 -> this.selection3;
            case 3 -> this.selection4;
            case 4 -> this.selection5;
            case 5 -> this.extraWish;
            default -> throw new IllegalArgumentException("The index given must be between 0 and 5.");
        };
    }

    /**
     * Gets the selection based on the {@link Selection}.
     * @param index of the selection.
     * @return {@link Company#getID()}
     * @throws IllegalArgumentException
     */
    public int getSelection(Selection selection) throws IllegalArgumentException
    {
        return getSelection(selection.ordinal());
    }

    /**
     * Sets the selection based on the index.
     * @param index of the selection. Can only be 0-5
     * @throws IllegalArgumentException
     */
    public void setSelection(int index, int wish) throws IllegalArgumentException
    {
        switch (index) {
            case 0 -> this.selection1 = wish;
            case 1 -> this.selection2 = wish;
            case 2 -> this.selection3 = wish;
            case 3 -> this.selection4 = wish;
            case 4 -> this.selection5 = wish;
            case 5 -> this.extraWish  = wish;
            default -> throw new IllegalArgumentException("The index given must be between 0 and 5.");
        };
    }

    /**
     * Sets the selection based on the {@link Selection}.
     * @param index of the selection.
     * @param wish {@link Company#getID()}
     * @throws IllegalArgumentException
     */
    public void setSelection(Selection selection, int wish) throws IllegalArgumentException
    {
        setSelection(selection.ordinal(), wish);
    }

    public enum Selection
    {
        Wish1 (0),
        Wish2 (1),
        Wish3(2),
        Wish4(3),
        Wish5(4),
        extraWish(5);
        public final int index;
        private Selection(int index)
        {
            this.index = index;
        }
    }
}
