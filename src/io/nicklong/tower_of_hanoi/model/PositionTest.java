package io.nicklong.tower_of_hanoi.model;

import io.nicklong.tower_of_hanoi.PuzzleRunner;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PositionTest {

    private PuzzleRunner puzzle;
    private PuzzleRunner bigPuzzle;
    private PuzzleRunner badPuzzle;
    private PuzzleRunner worsePuzzle;

    private Position position;
    private Position bigPosition;
    private Position emptyPosition1;
    private Position emptyPosition2;

    private class BadPuzzle extends PuzzleRunner {

    }

    @Before
    public void setUp() {
        puzzle = new PuzzleRunner(3, 5);
        bigPuzzle = new PuzzleRunner(3, 16);
        badPuzzle = new BadPuzzle();

/*        position = new Position(puzzle.getNumDiscs());
        bigPosition = new Position(bigPuzzle.getNumDiscs());
        emptyPosition1 = new Position(badPuzzle.getNumDiscs());
        emptyPosition2 = new Position();*/
    }

    @Test
    public void testGetNumDiscs() {
        assertEquals(position.getNumDiscs(), 5);
        assertEquals(bigPosition.getNumDiscs(), 16);
        assertEquals(emptyPosition1.getNumDiscs(), 0);
        assertEquals(emptyPosition2.getNumDiscs(), 0);
    }

    @Test
    public void testHasDiscs() {
        assertTrue(position.hasDiscs());
        assertTrue(position.hasDiscs());
        assertFalse(emptyPosition1.hasDiscs());
        assertFalse(emptyPosition2.hasDiscs());
    }

    @Test(expected = AssertionError.class)
    public void testAssertiveTopDisc() {
        emptyPosition2.topDisc();
    }

    @Test
    public void testTopDisc() {
        Disc disc = null;
        int numDiscs = position.getNumDiscs();
        for (int i = 0; i < numDiscs; i++) {
            assertNotEquals(position.topDisc(), disc);
            disc = position.topDisc();
            position.removeDisc();
        }

        assertFalse(position.hasDiscs());

        for (int i = 9; i >= 0; i--) {
            disc = new Disc(i + 1);
            assertTrue(position.stackDisc(disc));
            assertEquals(position.topDisc(), disc);
        }
    }

    @Test
    public void testCanStack() {
        assert false;
    }

    @Test
    public void testStackDisc() {
        Disc disc = null;
        for (int i = 9; i >= 0; i--) {
            disc = new Disc(i + 1);
            assertTrue(emptyPosition2.stackDisc(disc));
            assertEquals(emptyPosition2.getNumDiscs(), 10 - i);
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
        ArrayList<String> testList = new ArrayList<String>();
        testList.add("=====");
        testList.add("====");
        testList.add("===");
        testList.add("==");
        testList.add("=");
        assertEquals(position.toList(), testList);
    }
}
