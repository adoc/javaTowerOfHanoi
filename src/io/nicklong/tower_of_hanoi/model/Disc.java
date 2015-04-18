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
     *
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**
     * Represent the Disc textually.
     *
     * @return String
     */
    public String draw() {
        return StringUtils.repeatChar(repChar, (2 * size + 1) - 2);
    }

    /**
     * Construct Disc with a size.
     *
     * @param size
     */
    public Disc(int size) {
        assert size > 0;
        this.size = size;
    }

    /**
     * Construct a Disc with a size and repChar.
     *
     * @param size
     * @param repChar
     */
    public Disc(int size, char repChar) {
        this(size);
        this.repChar = repChar;
    }
}