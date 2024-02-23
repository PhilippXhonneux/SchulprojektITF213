package de.bwv_ac.data;

/**
 * Datastructur for Wishes BOT2
 *
 * @author Philipp Goebel
 * @version 1.0.0
 */
public class Wish extends Datastructure {
    private String className;
    private String surname;
    private String name;
    private Integer selection1;
    private Integer selection2;
    private Integer selection3;
    private Integer selection4;
    private Integer selection5;
    private Integer extraWish;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSelection1() {
        return selection1;
    }

    public void setSelection1(Integer selection1) {
        this.selection1 = selection1;
    }

    public Integer getSelection2() {
        return selection2;
    }

    public void setSelection2(Integer selection2) {
        this.selection2 = selection2;
    }

    public Integer getSelection3() {
        return selection3;
    }

    public void setSelection3(Integer selection3) {
        this.selection3 = selection3;
    }

    public Integer getSelection4() {
        return selection4;
    }

    public void setSelection4(Integer selection4) {
        this.selection4 = selection4;
    }

    public Integer getSelection5() {
        return selection5;
    }

    public void setSelection5(Integer selection5) {
        this.selection5 = selection5;
    }

    public Integer getExtraWish() {
        return extraWish;
    }

    public void setExtraWish(Integer extraWish) {
        this.extraWish = extraWish;
    }
}
