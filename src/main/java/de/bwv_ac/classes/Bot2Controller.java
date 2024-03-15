package de.bwv_ac.classes;

import de.bwv_ac.data.Wishes;
import de.bwv_ac.view.bot_2.WishList;

import java.awt.*;

public class Bot2Controller {

    private static final String delimiter = ";"; // TODO: get from other location

    private final Wishes wishes;

    /**
     * GUI Object
     */
    private final WishList wishList;

    public Bot2Controller(Wishes wishes){
        this.wishes = wishes;
        wishList = new WishList();
        wishes.addObserver(wishList);

        wishes.notifyObservers();
    }

    /**
     * Get the panel for bot 1
     * @return a JPanel
     */
    public Component getPanel() {
        return wishList;
    }
}
