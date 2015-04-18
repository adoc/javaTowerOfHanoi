package io.nicklong.tower_of_hanoi.model;

import java.util.ArrayList;

/**
 * The scene of our Tower construction.
 */
public class Landscape {
    private ArrayList<Position> positions = new ArrayList<>();
    private int numPositions;           // or Poles in the PuzzleRunner
    private int numDiscs;               // Number of discs at the starting position.

    /**
     * Gets the number of Positions in the Landscape.
     *
     * @return Number of Positions
     */
    public int getNumPositions() {
        return numPositions;
    }

    /**
     * Gets the number of Discs on the Landscape.
     *
     * @return Number of discs
     */
    public int getNumDiscs() {
        return numDiscs;
    }

    /**
     * Gets the ordinal of the Position on the Landscape.
     *
     * @param position Position to find ordinal
     * @return Ordinal of position
     */
    public int getPositionOrdinal(Position position) {
        return positions.indexOf(position);
    }

    /**
     * Gets the right-most Position on the Landscape.
     *
     * @return Rightmost Position object.
     */
    public Position getRightmostPosition() {
        return positions.get(positions.size() - 1);
    }

    /**
     * Gets an list of Positions that are eligible sources of discs.
     *
     * @return Eligible source Positions ArrayList
     */
    public ArrayList<Position> getEligibleSources() {
        ArrayList<Position> eligibleSources = new ArrayList<>();
        for (Position position : positions) {
            if (position.hasDiscs()) {
                eligibleSources.add(position);
            }
        }
        return eligibleSources;
    }

    /**
     * Given a Disc parameter, determine the eligible targets for the Disc.
     *
     * @param sourceDisc Disc to attempt to move
     * @return Eligible targets for this Disc to move
     */
    public ArrayList<Position> getEligibleTargets(Disc sourceDisc) {
        ArrayList<Position> eligibleTargets = new ArrayList<>();
        for (Position position : positions) {
            if (position.canStack(sourceDisc)) {
                eligibleTargets.add(position);
            }
        }
        return eligibleTargets;
    }

    /**
     * Returns true if the Landscape is completed.
     * @return Is the landscape done
     */
    public boolean isDone() {
        return getRightmostPosition().getNumDiscs() == getNumDiscs();
    }

    /**
     * Represent the Positions on the Landscape and their inner Discs.
     *
     * @return ArrayList of Positions and Discs
     */
    public ArrayList<ArrayList<String>> toList() {
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (Position position : positions)
            output.add(position.toList());
        return output;
    }

    /**
     * Build the vast game landscape.
     * @param numDiscs Number of Discs in Landscape
     * @param numPositions Number of Positions on Landscape
     */
    public Landscape(int numDiscs, int numPositions) {
        this.numDiscs = numDiscs;
        this.numPositions = numPositions;

        positions.add(new Position(getNumDiscs()));
        for (int i = 1; i < getNumPositions(); i++)
            positions.add(new Position());
    }
}