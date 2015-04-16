package io.nicklong.tower_puzzle;

public class Disc {
    /*
    Representing a disc section of the Tower. Has ``size`` and overridden ``toString``
        to show the disc in the console.
     */
    private int size;

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return Utils.repeatString("=", size);
    }

    public Disc(int size) {
        this.size = size;
    }

    public Disc() {
        /* For polymorphed nested subclasses in Landscape class */
    }
}