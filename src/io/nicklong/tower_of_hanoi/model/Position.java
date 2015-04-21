package io.nicklong.tower_of_hanoi.model;

import java.util.ArrayList;

/**
 * Represents a Position on the Landscape.
 */
public class Position {
    private ArrayList<Disc> discs = new ArrayList<>();

    /**
     * Build a stack of n Discs.
     * @param n Number of Discs
     */
    private void buildStack(int n) {
        assert !hasDiscs();
        for (int i = 0; i < n; i++)
            discs.add(new Disc(n - i));
    }

    /**
     * Get the number of discs at this Position.
     * @return Number of Discs
     */
    public int getNumDiscs() {
        return discs.size();
    }

    /**
     * Return true if this Position has discs.
     * @return If has Discs
     */
    public boolean hasDiscs() {
        return getNumDiscs() > 0;
    }

    /**
     * Return true if we can stack the param Disc on top.
     * @param disc Disc to check if we can stack
     * @return If can stack Disc
     */
    public boolean canStack(Disc disc) {
        return !hasDiscs() || disc.getSize() < getTopDisc().getSize();
    }

    /**
     * Returns the getTopDisc of the stack.
     * @return Top Disc object
     */
    public Disc getTopDisc() {
        assert hasDiscs();
        return discs.get(getNumDiscs() - 1);
    }

    /**
     * Stack a disc on top if it's allowed. Return true if disc was stacked.
     * @param disc Stack this Disc.
     * @return Success boolean
     */
    public boolean stackDisc(Disc disc) {
        if (canStack(disc)) {
            discs.add(disc);
            return true;
        }
        return false;
    }

    /**
     * Remove a disc from the top of the stack and return it.
     * @return Disc that was removed
     */
    public Disc removeDisc() {
        Disc disc = getTopDisc();
        discs.remove(disc);
        return disc;
    }

    /**
     * Represent the disc stack of this Position. Bottom to top oriented list.
     * @return ArrayList of Strings representing the Positions Discs
     */
    public ArrayList<String> toList() {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < getNumDiscs(); i++)
            output.add(discs.get(getNumDiscs() - i - 1).draw());
        return output;
    }

    /**
     * Construct a Position that builds a tower of discs.
     * @param initNumDiscs Initial number of Discs.
     */
    public Position(int initNumDiscs) {
        buildStack(initNumDiscs);
    }

    /**
     * Construct an empty Position.
     */
    public Position() {
    }
}