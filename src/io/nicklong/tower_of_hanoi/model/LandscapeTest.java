package io.nicklong.tower_of_hanoi.model;

import io.nicklong.tower_of_hanoi.PuzzleRunner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LandscapeTest {

    private PuzzleRunner smallPuzzle;
    private PuzzleRunner puzzle;
    private PuzzleRunner bigPuzzle;

    private Landscape smallLandscape;
    private Landscape landscape;
    private Landscape bigLandscape;

    @Before
    public void setUp() {
        smallPuzzle = new PuzzleRunner(3, 5);
        puzzle = new PuzzleRunner(3, 5);
        bigPuzzle = new PuzzleRunner(3, 100);

        //smallLandscape = new Landscape(smallPuzzle);
        //landscape = new Landscape(puzzle);
        //bigLandscape = new Landscape(bigPuzzle);
    }

    @Test
    public void testGetRightmostPosition() {
        assertEquals(landscape.getRightmostPosition().getNumDiscs(), 0);
    }

    @Test
    public void testGetEligibleSources() {
        assertEquals(landscape.getEligibleSources().size(), 1);
    }

    @Test
    public void testGetEligibleTargets() {
        assert false;
    }
}
