package io.nicklong.tower_of_hanoi.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PositionTest {

    private Position position;
    private Position bigPosition;
    private Position emptyPosition;

    @Before
    public void setUp() {
        position = new Position(5);
        bigPosition = new Position(16);
        emptyPosition = new Position();
    }

    @Test
    public void testGetNumDiscs() {
        assertEquals(position.getNumDiscs(), 5);
        assertEquals(bigPosition.getNumDiscs(), 16);
        assertEquals(emptyPosition.getNumDiscs(), 0);
    }

    @Test
    public void testHasDiscs() {
        assertTrue(position.hasDiscs());
        assertTrue(position.hasDiscs());
        assertFalse(emptyPosition.hasDiscs());
    }

    @Test
    public void testCanStack() {
        Disc disc = new Disc(1);
        assertTrue(emptyPosition.canStack(disc));
        assertFalse(position.canStack(disc));
        position.removeDisc();
        assertTrue(position.canStack(disc));
    }

    @Test(expected = AssertionError.class)
    public void testAssertiveTopDisc() {
        emptyPosition.getTopDisc();
    }

    @Test
    public void testGetTopDisc() {
        Disc disc = null;
        int numDiscs = position.getNumDiscs();
        for (int i = 0; i < numDiscs; i++) {
            assertNotEquals(position.getTopDisc(), disc);
            disc = position.getTopDisc();
            position.removeDisc();
        }

        assertFalse(position.hasDiscs());

        for (int i = 9; i >= 0; i--) {
            disc = new Disc(i + 1);
            assertTrue(position.stackDisc(disc));
            assertEquals(position.getTopDisc(), disc);
        }
    }

    @Test
    public void testStackDisc() {
        Disc disc;
        for (int i = 9; i >= 0; i--) {
            disc = new Disc(i + 1);
            assertTrue(emptyPosition.stackDisc(disc));
            assertEquals(emptyPosition.getNumDiscs(), 10 - i);
        }
    }

    @Test
    public void testRemoveDisc() {
        int numDiscs = position.getNumDiscs();
        for (int i = 0; i < numDiscs; i++) {
            position.removeDisc();
        }
        assertEquals(position.getNumDiscs(), 0);
    }

    @Test
    public void testToList() {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("=");
        testList.add("===");
        testList.add("=====");
        testList.add("=======");
        testList.add("=========");
        assertEquals(position.toList(), testList);
    }
}
