package io.nicklong.tower_of_hanoi.model;


import java.util.ArrayList;

/**
 * Represents a Position on the Landscape.
 */
public class Position {
    private ArrayList<Disc> discs = new ArrayList<>();

    /**
     * Get the number of discs at this Position.
     *
     * @return int
     */
    public int getNumDiscs() {
        return discs.size();
    }

    /**
     * Return true if this Position has discs.
     *
     * @return boolean
     */
    public boolean hasDiscs() {
        return getNumDiscs() > 0;
    }

    /**
     * Return true if we can stack the param Disc on top.
     *
     * @param disc
     * @return boolean
     */
    public boolean canStack(Disc disc) {
        return !hasDiscs() || disc.getSize() < topDisc().getSize();
    }

    /**
     * Returns the topDisc of the stack.
     *
     * @return
     */
    public Disc topDisc() {
        assert hasDiscs();
        return discs.get(getNumDiscs() - 1);
    }

    /**
     * Stack a disc on top if it's allowed. Return true if disc was stacked.
     *
     * @param disc
     * @return boolean
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
     *
     * @return Disc
     */
    public Disc removeDisc() {
        Disc disc = topDisc();
        discs.remove(disc);
        return disc;
    }

    /**
     * Build a stack of n Discs.
     *
     * @param n
     */
    private void buildStack(int n) {
        assert !hasDiscs();
        for (int i = 0; i < n; i++)
            discs.add(new Disc(n - i));
    }

    /**
     * Represent the disc stack of this Position. Bottom to top oriented list.
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> toList() {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < getNumDiscs(); i++)
            output.add(discs.get(getNumDiscs() - i - 1).draw());
        return output;
    }

    /**
     * Construct a Position that builds a tower of discs.
     *
     * @param initNumDiscs
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