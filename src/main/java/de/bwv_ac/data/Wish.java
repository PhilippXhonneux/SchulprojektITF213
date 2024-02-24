package de.bwv_ac.data;

import jdk.jshell.spi.ExecutionControl;

/**
 * Datastructure for Wishes BOT2
 *
 * @author Philipp Goebel, Philipp Xhonneux
 * @version 1.1.0
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
     * Contains the {@link Company#ID}.
     */
    private Integer selection1;
    /**
     * Second choice of company of the student.
     * Contains the {@link Company#ID}.
     */
    private Integer selection2;
    /**
     * Third choice of company of the student.
     * Contains the {@link Company#ID}.
     */
    private Integer selection3;
    /**
     * Fourth choice of company of the student.
     * Contains the {@link Company#ID}.
     */
    private Integer selection4;
    /**
     * Fifth choice of company of the student.
     * Contains the {@link Company#ID}.
     */
    private Integer selection5;
    /**
     * Sixth choice of company of the student.
     * Contains the {@link Company#ID}.
     */
    private Integer extraWish;

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
     * Gets the first choice of the student
     * @return {@link Company#ID}
     */
    public Integer getSelection1() {
        return selection1;
    }

    /**
     * Sets the first choice of the student
     * @param selection1 {@link Company#ID}
     */
    public void setSelection1(Integer selection1) {
        this.selection1 = selection1;
    }

    /**
     * Gets the second choice of the student
     * @return {@link Company#ID}
     */
    public Integer getSelection2() {
        return selection2;
    }

    /**
     * Sets the second choice of the student
     * @param selection2 {@link Company#ID}
     */
    public void setSelection2(Integer selection2) {
        this.selection2 = selection2;
    }

    /**
     * Gets the third choice of the student
     * @return {@link Company#ID}
     */
    public Integer getSelection3() {
        return selection3;
    }

    /**
     * Sets the third choice of the student
     * @param selection3 {@link Company#ID}
     */
    public void setSelection3(Integer selection3) {
        this.selection3 = selection3;
    }

    /**
     * Gets the fourth choice of the student
     * @return {@link Company#ID}
     */
    public Integer getSelection4() {
        return selection4;
    }

    /**
     * Sets the first choice of the student
     * @param selection4 {@link Company#ID}
     */
    public void setSelection4(Integer selection4) {
        this.selection4 = selection4;
    }

    /**
     * Gets the fifth choice of the student
     * @return {@link Company#ID}
     */
    public Integer getSelection5() {
        return selection5;
    }

    /**
     * Sets the fifth choice of the student
     * @param selection5 {@link Company#ID}
     */
    public void setSelection5(Integer selection5) {
        this.selection5 = selection5;
    }

    /**
     * Gets the sixth choice of the student
     * @return {@link Company#ID}
     */
    public Integer getExtraWish() {
        return extraWish;
    }

    /**
     * Sets the sixth choice of the student
     * @param extraWish {@link Company#ID}
     */
    public void setExtraWish(Integer extraWish) {
        this.extraWish = extraWish;
    }

    /**
     * Gets the selection based on the index.
     * @param index of the selection. Can only be 0-5
     * @return {@link Company#ID}
     * @throws IllegalArgumentException
     */
    public Integer getSelection(int index) throws IllegalArgumentException
    {
        return switch (index) {
            case 0 -> getSelection1();
            case 1 -> getSelection2();
            case 2 -> getSelection3();
            case 3 -> getSelection4();
            case 4 -> getSelection5();
            case 5 -> getExtraWish();
            default -> throw new IllegalArgumentException("The index given must be between 0 and 5.");
        };
    }
}
