package io.nicklong.tower_of_hanoi.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiscTest {
    /*
    Unittests for the ``Disc`` Class.
     */

    @Test(expected = AssertionError.class)
    public void testAssertiveConstructor1() {
        new Disc(0);
    }

    @Test
    public void testGetSize() {
        Disc disc1 = new Disc(10);
        assertEquals(disc1.getSize(), 10);
    }

    @Test
    public void testToString() {
        Disc disc1 = new Disc(3);
        assertEquals(disc1.toString(), "===");
    }
}
