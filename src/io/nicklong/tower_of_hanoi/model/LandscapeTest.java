package io.nicklong.tower_of_hanoi.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LandscapeTest {

    private Landscape smallLandscape;
    private Landscape landscape;
    private Landscape bigLandscape;

    @Before
    public void setUp() {
        smallLandscape = new Landscape(2, 3);
        landscape = new Landscape(5, 3);
        bigLandscape = new Landscape(100, 4);
    }

    @Test
    public void testGetNumPositions() {
        assertEquals(smallLandscape.getNumPositions(), 3);
        assertEquals(landscape.getNumPositions(), 3);
        assertEquals(bigLandscape.getNumPositions(), 4);
    }

    @Test
    public void testGetNumDiscs() {
        assertEquals(smallLandscape.getNumDiscs(), 2);
        assertEquals(landscape.getNumDiscs(), 5);
        assertEquals(bigLandscape.getNumDiscs(), 100);
    }

    @Test
    public void testGetPositionOrdinal() {
        ArrayList<Position> positions1 = landscape.getEligibleSources();
        ArrayList<Position> positions2 = landscape.getEligibleTargets(positions1.get(0).getTopDisc());
        assertEquals(landscape.getPositionOrdinal(positions2.get(0)), 1);
        assertEquals(landscape.getPositionOrdinal(positions2.get(1)), 2);
    }

    @Test
    public void testGetRightmostPosition() {
        assertEquals(landscape.getRightmostPosition().getNumDiscs(), 0);
        assertEquals(landscape.getPositionOrdinal(landscape.getRightmostPosition()), 2);
    }

    @Test
    public void testGetEligibleSources() {
        assertEquals(landscape.getEligibleSources().size(), 1);
    }

    @Test
    public void testGetEligibleTargets() {
        ArrayList<Position> positions1 = landscape.getEligibleSources();
        ArrayList<Position> positions2 = landscape.getEligibleTargets(positions1.get(0).getTopDisc());
        assertEquals(landscape.getEligibleTargets(positions1.get(0).getTopDisc()).size(), 2);
    }

    @Test
    public void testIsDone() {
        ArrayList<Position> positions1 = smallLandscape.getEligibleSources();
        Position start = positions1.get(0);

        ArrayList<Position> positions2 = smallLandscape.getEligibleTargets(start.getTopDisc());
        positions2.get(0).stackDisc(start.removeDisc());
        positions2.get(1).stackDisc(start.removeDisc());
        positions2.get(1).stackDisc(positions2.get(0).removeDisc());

        assertTrue(smallLandscape.isDone());
    }

    @Test
    public void testToList() {
        ArrayList<String> inner1 = new ArrayList<>();
        ArrayList<String> inner2 = new ArrayList<>();
        ArrayList<String> inner3 = new ArrayList<>();
        inner1.add("=");
        inner1.add("===");
        inner1.add("=====");
        inner1.add("=======");
        inner1.add("=========");

        ArrayList<ArrayList<String>> outer = new ArrayList<>();

        outer.add(inner1);
        outer.add(inner2);
        outer.add(inner3);

        assertEquals(landscape.toList(), outer);
    }
}
