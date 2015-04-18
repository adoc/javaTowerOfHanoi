package io.nicklong.tower_of_hanoi.model;

import io.nicklong.utils.StringUtils;

/**
 * A Disc of the tower, generally contained in a Position.
 */
public class Disc {
    private int size;
    private char repChar = '=';

    /**
     * Get the size of the Disc.
     * @return size of Disc
     */
    public int getSize() {
        return size;
    }

    /**
     * Represent the Disc textually.
     * @return repeated character
     */
    public String draw() {
        return StringUtils.repeatChar(repChar, (2 * size + 1) - 2);
    }

    /**
     * Construct Disc with a size.
     * @param size Size of disc
     */
    public Disc(int size) {
        assert size > 0;
        this.size = size;
    }

    /**
     * Construct a Disc with a size and repChar.
     * @param size Size of disc
     * @param repChar Char to be repeated
     */
    public Disc(int size, char repChar) {
        this(size);
        this.repChar = repChar;
    }
}